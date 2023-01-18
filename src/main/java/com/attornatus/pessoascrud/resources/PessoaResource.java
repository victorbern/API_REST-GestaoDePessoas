package com.attornatus.pessoascrud.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.pessoascrud.models.Pessoa;
import com.attornatus.pessoascrud.repositories.PessoaRepositorio;

@RestController
@RequestMapping(path = "/pessoas")
public class PessoaResource {
	
	private PessoaRepositorio pessoaRepositorio;
	
	public PessoaResource(PessoaRepositorio pessoaRepositorio) {
		super();
		this.pessoaRepositorio = pessoaRepositorio;
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
		pessoaRepositorio.save(pessoa);
		return new ResponseEntity<>(pessoa, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> getAll(){
		List<Pessoa> pessoas = new ArrayList<>();
		pessoas = pessoaRepositorio.findAll();
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Optional<Pessoa>> getById(@PathVariable Integer id){
		Optional<Pessoa> pessoa;
		try {
			pessoa = pessoaRepositorio.findById(id);
			return new ResponseEntity<Optional<Pessoa>>(pessoa, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Pessoa>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Optional<Pessoa>> deleteById(@PathVariable Integer id){
		try {
			pessoaRepositorio.deleteById(id);
			return new ResponseEntity<Optional<Pessoa>>(HttpStatus.OK);
		} catch(NoSuchElementException nsee) {
			return new ResponseEntity<Optional<Pessoa>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Pessoa> update(@PathVariable Integer id, @RequestBody Pessoa novaPessoa){
		return pessoaRepositorio.findById(id).map(pessoa -> {
			pessoa.setNome(novaPessoa.getNome());
			pessoa.setDataNascimento(novaPessoa.getDataNascimento());
			pessoa.setLogradouro(novaPessoa.getLogradouro());
			pessoa.setCep(novaPessoa.getCep());
			pessoa.setNumero(novaPessoa.getNumero());
			pessoa.setCidade(novaPessoa.getCidade());
			Pessoa pessoaAtualizada = pessoaRepositorio.save(pessoa);
			return ResponseEntity.ok().body(pessoaAtualizada);
		}).orElse(ResponseEntity.notFound().build());
	}
	
}
