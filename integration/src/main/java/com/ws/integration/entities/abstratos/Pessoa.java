package com.ws.integration.entities.abstratos;

import com.ws.integration.entities.pedidoVO.EnderecoVO;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 27 de fev de 2021
 ***************************************************************/

public class Pessoa {
	
	private String cpfCnpj;
	private String email;
	private EnderecoVO endereco;
	private String nome;
	private String telefone;
	
	
	
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public String getEmail() {
		return email;
	}
	public EnderecoVO getEndereco() {
		return endereco;
	}
	public String getNome() {
		return nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEndereco(EnderecoVO endereco) {
		this.endereco = endereco;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


}
