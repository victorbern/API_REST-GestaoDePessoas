package com.attornatus.pessoascrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.pessoascrud.models.Endereco;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco, Integer>{
	
}
