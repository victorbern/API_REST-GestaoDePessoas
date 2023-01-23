package com.attornatus.pessoascrud.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Pessoa {
	
	@GeneratedValue
	@Id
	@Column(name = "pessoa_id")
	private int id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "dataNascimento")
	private Date dataNascimento;
	
	@OneToMany(mappedBy="pessoa")
	@JsonIgnore
	private List<Endereco> enderecos;
	
	public Pessoa() {
		this.enderecos = new ArrayList<>();
	}
	
	public Pessoa(String nome, Date dataNascimento) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.enderecos = new ArrayList<>();
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
	
	public Endereco getEnderecoPrincipal() {
		for(Endereco end : this.enderecos) {
			if (end.isPrincipal()) {
				return end;
			}
		}
		return null;
	}
	
	public void setEnderecoPrincipal(Endereco endereco) {
		for (Endereco end : this.enderecos) {
			end.setIsPrincipal(false);
		}
		endereco.setIsPrincipal(true);
	}
	
	public List<Endereco> getEnderecos(){
		return this.enderecos;
	}
	
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public void addEndereco(Endereco endereco) {
		if (endereco.getPessoa() != this) {
			endereco.setPessoa(this);			
		}
		this.enderecos.add(endereco);
		if (endereco.isPrincipal() == true) {
			this.setEnderecoPrincipal(endereco);
		}
	}
	
	public void removeEndereco(Endereco endereco) {
		endereco.setPessoa(null);
		this.enderecos.remove(endereco);
	}
	
	public String toString() {
		return "";
	}
}
