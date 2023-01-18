package com.attornatus.pessoascrud.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Pessoa {
	
	@GeneratedValue
	@Id
	private int id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "dataNascimento")
	private Date dataNascimento;
	
	@Column(name = "logradouro")
	private String logradouro;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "cidade")
	private String cidade;
	
	public Pessoa() {
		
	}
	
	public Pessoa(String nome, Date dataNascimento, String logradouro, String cep, String numero, String cidade) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	
	public String toString() {
		return "";
	}
}
