package com.ws.integration.entities.pedidoVO;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 27 de fev de 2021
 ***************************************************************/
public class EnderecoVO {
	
	private String bairro;
	private String cep;
	private String cidade;
	private String codigoIbge;
	private String complemento;
	private Long latitude;
	private Long longitude;
	private String numero;
	private String pais;
	private String rua;
	private String uf;
	
	
	public String getBairro() {
		return bairro;
	}
	public String getCep() {
		return cep;
	}
	public String getCidade() {
		return cidade;
	}
	public String getCodigoIbge() {
		return codigoIbge;
	}
	public String getComplemento() {
		return complemento;
	}
	public Long getLatitude() {
		return latitude;
	}
	public Long getLongitude() {
		return longitude;
	}
	public String getNumero() {
		return numero;
	}
	public String getPais() {
		return pais;
	}
	public String getRua() {
		return rua;
	}
	public String getUf() {
		return uf;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public void setCodigoIbge(String codigoIbge) {
		this.codigoIbge = codigoIbge;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
}
