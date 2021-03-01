package com.ws.integration.entities.pedidoVO;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 27 de fev de 2021
 ***************************************************************/

public class ItemVO {
	
	private String codigo;
	private Number preco;
	private Long quantidade;
	private Number valorTotal;
	
	
	public String getCodigo() {
		return codigo;
	}
	public Number getPreco() {
		return preco;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public Number getValorTotal() {
		return valorTotal;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public void setPreco(Number preco) {
		this.preco = preco;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public void setValorTotal(Number valorTotal) {
		this.valorTotal = valorTotal;
	}


}
