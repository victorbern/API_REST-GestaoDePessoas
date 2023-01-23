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

import com.attornatus.pessoascrud.dto.EnderecoDTO;
import com.attornatus.pessoascrud.models.Endereco;
import com.attornatus.pessoascrud.models.Pessoa;
import com.attornatus.pessoascrud.repositories.EnderecoRepositorio;
import com.attornatus.pessoascrud.repositories.PessoaRepositorio;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {
	
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	public EnderecoResource(EnderecoRepositorio enderecoRepositorio) {
		this.enderecoRepositorio = enderecoRepositorio;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EnderecoDTO> save(@RequestBody Endereco endereco){
		enderecoRepositorio.save(endereco);
		this.setEnderecoPrincipal(endereco);
		EnderecoDTO enderecoDTO = new EnderecoDTO(endereco);
		return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
	}
	
	@GetMapping
	@Transactional(readOnly = true)
	public ResponseEntity<List<EnderecoDTO>> getAll(){
		List<Endereco> enderecos = new ArrayList<>();
		enderecos = enderecoRepositorio.findAll();
		List<EnderecoDTO> enderecosDTO = new ArrayList<>();
		for (Endereco endereco : enderecos) {
			enderecosDTO.add(new EnderecoDTO(endereco));
		}
		return new ResponseEntity<>(enderecosDTO, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity<Optional<EnderecoDTO>> getById(@PathVariable Integer id){
		Optional<Endereco> endereco;
		Optional<EnderecoDTO> enderecoDTO;
		try {
			endereco = enderecoRepositorio.findById(id);
			enderecoDTO = Optional.of(new EnderecoDTO(endereco.get()));
			return new ResponseEntity<Optional<EnderecoDTO>>(enderecoDTO, HttpStatus.OK);
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<Optional<EnderecoDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/{id}")
	@Transactional
	public ResponseEntity<Optional<EnderecoDTO>> deleteById(@PathVariable Integer id){
		try {
			enderecoRepositorio.deleteById(id);
			return new ResponseEntity<Optional<EnderecoDTO>>(HttpStatus.OK);
		} catch(NoSuchElementException nsee) {
			return new ResponseEntity<Optional<EnderecoDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = "/{id}")
	@Transactional
	public ResponseEntity<EnderecoDTO> update(@PathVariable Integer id, @RequestBody Endereco novoEndereco){
		return enderecoRepositorio.findById(id).map(endereco -> {
			endereco.setLogradouro(novoEndereco.getLogradouro());
			endereco.setCep(novoEndereco.getCep());
			endereco.setNumero(novoEndereco.getNumero());
			endereco.setCidade(novoEndereco.getCidade());
			endereco.setPessoa(novoEndereco.getPessoa());
			Endereco enderecoAtualizado = enderecoRepositorio.save(endereco);
			EnderecoDTO enderecoDTO = new EnderecoDTO(enderecoAtualizado);
			return ResponseEntity.ok().body(enderecoDTO);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	public void setEnderecoPrincipal(Endereco endereco) {
		if (endereco.isPrincipal() == true) {
			this.updateEnderecos(endereco);
		}
	}
	
	public void updateEnderecos(Endereco endereco) {
		Pessoa pessoa = pessoaRepositorio.findById(endereco.getPessoa().getId()).get();
		pessoa.setEnderecoPrincipal(endereco);
		pessoaRepositorio.save(pessoa);
	}
	
}
