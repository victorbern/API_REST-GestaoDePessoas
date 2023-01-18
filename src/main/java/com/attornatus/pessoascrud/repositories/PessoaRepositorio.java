package com.attornatus.pessoascrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.pessoascrud.models.Pessoa;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Integer>{
	
}
