package com.attornatus.pessoascrud.dto;

import com.attornatus.pessoascrud.models.Endereco;

public class EnderecoDTO {
	
	private String logradouro;
	private String cep;
	private String numero;
	private String cidade;
	private boolean isPrincipal = false;
	private Integer pessoa_id;
	
	public EnderecoDTO() {
		
	}
	
	public EnderecoDTO(String logradouro, String cep, String numero, String cidade, boolean isPrincipal, Integer pessoa_id) {
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.isPrincipal = isPrincipal;
		this.pessoa_id = pessoa_id;
	}
	
	public EnderecoDTO(Endereco endereco) {
		logradouro = endereco.getLogradouro();
		cep = endereco.getCep();
		numero = endereco.getNumero();
		cidade = endereco.getCidade();
		isPrincipal = endereco.isPrincipal();
		pessoa_id = endereco.getPessoa().getId();
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public boolean isPrincipal() {
		return isPrincipal;
	}

	public void setPrincipal(boolean isPrincipal) {
		this.isPrincipal = isPrincipal;
	}

	public Integer getPessoa_id() {
		return pessoa_id;
	}

	public void setPessoa_id(Integer pessoa_id) {
		this.pessoa_id = pessoa_id;
	}
	
	
}
