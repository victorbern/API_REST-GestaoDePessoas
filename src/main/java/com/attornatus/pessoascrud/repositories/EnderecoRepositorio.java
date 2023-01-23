package com.attornatus.pessoascrud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.pessoascrud.models.Endereco;
import com.attornatus.pessoascrud.models.Pessoa;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco, Integer>{
	
	List<Endereco> findByPessoa(Pessoa pessoa);
	
}
