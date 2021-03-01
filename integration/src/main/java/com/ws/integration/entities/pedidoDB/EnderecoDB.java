package com.ws.integration.entities.pedidoDB;

import org.springframework.lang.NonNull;

import com.ws.integration.entities.pedidoVO.EnderecoVO;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 27 de fev de 2021
 ***************************************************************/
public class EnderecoDB {
	
	private String bairro;
	private String cidade;
	private String complemento;
	private String numero;
	private String pais;
	private String logradouro;
	private String uf;
	
	public static EnderecoDB parseDB(@NonNull EnderecoVO enderecoVO) {
		EnderecoDB enderecoDB = new EnderecoDB();
		
		enderecoDB.setBairro(enderecoVO.getBairro());
		enderecoDB.setCidade(enderecoVO.getCidade());
		enderecoDB.setComplemento(enderecoVO.getComplemento());
		enderecoDB.setNumero(enderecoVO.getNumero());
		enderecoDB.setPais(enderecoVO.getPais());
		enderecoDB.setLogradouro(enderecoVO.getRua());
		enderecoDB.setUf(enderecoVO.getUf());
		
		return enderecoDB;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getNumero() {
		return numero;
	}

	public String getPais() {
		return pais;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getUf() {
		return uf;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	
	
	
	
}
