package com.attornatus.pessoascrud.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.attornatus.pessoascrud.models.Endereco;
import com.attornatus.pessoascrud.models.Pessoa;

public class PessoaDTO {
	
	private int id;
	private String nome;
	private Date dataNascimento;
	private List<EnderecoDTO> enderecos;
	
	public PessoaDTO() {
		
	}
	
	public PessoaDTO(int id, String nome, Date dataNascimento, List<Endereco> enderecos) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.enderecos = this.convertEnderecos(enderecos);
	}
	
	public PessoaDTO(Pessoa pessoa) {
		id = pessoa.getId();
		nome = pessoa.getNome();
		dataNascimento = pessoa.getDataNascimento();
		enderecos = this.convertEnderecos(pessoa.getEnderecos());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public List<EnderecoDTO> convertEnderecos(List<Endereco> enderecos) {
		List<EnderecoDTO> enderecosDTO = new ArrayList<>();
		for (Endereco endereco : enderecos) {
			enderecosDTO.add(new EnderecoDTO(endereco));
		}
		return enderecosDTO;
	}
	
	public EnderecoDTO getEnderecoPrincipal() {
		for(EnderecoDTO end : this.enderecos) {
			if (end.isPrincipal()) {
				return end;
			}
		}
		return null;
	}

	public List<EnderecoDTO> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoDTO> enderecos) {
		this.enderecos = enderecos;
	}
}
