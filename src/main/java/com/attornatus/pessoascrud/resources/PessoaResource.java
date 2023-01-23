package com.attornatus.pessoascrud.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.pessoascrud.dto.PessoaDTO;
import com.attornatus.pessoascrud.models.Pessoa;
import com.attornatus.pessoascrud.repositories.PessoaRepositorio;

@RestController
@RequestMapping(path = "/pessoas")
public class PessoaResource {
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	public PessoaResource(PessoaRepositorio pessoaRepositorio) {
		super();
		this.pessoaRepositorio = pessoaRepositorio;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PessoaDTO> save(@RequestBody Pessoa pessoa){
		pessoaRepositorio.save(pessoa);
		PessoaDTO pessoaDTO = new PessoaDTO(pessoa);
		return new ResponseEntity<>(pessoaDTO, HttpStatus.OK);
	}
	
	@GetMapping
	@Transactional(readOnly = true)
	public ResponseEntity<List<PessoaDTO>> getAll(){
		List<Pessoa> pessoas = new ArrayList<>();
		pessoas = pessoaRepositorio.findAll();
		List<PessoaDTO> pessoasDTO = new ArrayList<>();
		for (Pessoa pessoa : pessoas) {
			pessoasDTO.add(new PessoaDTO(pessoa));
		}
		return new ResponseEntity<>(pessoasDTO, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<Optional<PessoaDTO>> getById(@PathVariable Integer id){
		Optional<Pessoa> pessoa;
		Optional<PessoaDTO> pessoaDTO;
		try {
			pessoa = pessoaRepositorio.findById(id);
			pessoaDTO = Optional.of(new PessoaDTO(pessoa.get()));
			return new ResponseEntity<Optional<PessoaDTO>>(pessoaDTO, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<PessoaDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	@Transactional
	public ResponseEntity<Optional<PessoaDTO>> deleteById(@PathVariable Integer id){
		try {
			pessoaRepositorio.deleteById(id);
			return new ResponseEntity<Optional<PessoaDTO>>(HttpStatus.OK);
		} catch(NoSuchElementException nsee) {
			return new ResponseEntity<Optional<PessoaDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<PessoaDTO> update(@PathVariable Integer id, @RequestBody Pessoa novaPessoa){
		return pessoaRepositorio.findById(id).map(pessoa -> {
			pessoa.setNome(novaPessoa.getNome());
			pessoa.setDataNascimento(novaPessoa.getDataNascimento());
			Pessoa pessoaAtualizada = pessoaRepositorio.save(pessoa);
			PessoaDTO pessoaDTO = new PessoaDTO(pessoaAtualizada);
			return ResponseEntity.ok().body(pessoaDTO);
		}).orElse(ResponseEntity.notFound().build());
	}
	
}
