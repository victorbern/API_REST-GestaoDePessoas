package com.attornatus.pessoascrud.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Endereco {

	@GeneratedValue
	@Id
	private int id;
	
	@Column(name = "logradouro")
	private String logradouro;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "isPrincipal")
	private boolean isPrincipal;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	public Endereco() {
		
	}
	
	public Endereco(String logradouro, String cep, String numero, String cidade, Pessoa pessoa) {
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.pessoa = pessoa;
	}
	
	public int getId() {
		return id;
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
		if (!this.pessoa.getEnderecos().contains(this)) {
			pessoa.addEndereco(this);
		}
	}

	public boolean isPrincipal() {
		return this.isPrincipal;
	}
	
	public void setIsPrincipal(boolean valor) {
		this.isPrincipal = valor;
	}
	
}
