package com.attornatus.pessoascrud.resources;



import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.attornatus.pessoascrud.ApplicationConfigTest;
import com.attornatus.pessoascrud.repositories.PessoaRepositorio;

@DisplayName("PessoaResourceTest")
public class PessoaResourceTest extends ApplicationConfigTest{
	
	@MockBean
	private PessoaRepositorio pessoaRepositorio;
	
	@Autowired
	private PessoaResource pessoaResource;
	
	
	
}
