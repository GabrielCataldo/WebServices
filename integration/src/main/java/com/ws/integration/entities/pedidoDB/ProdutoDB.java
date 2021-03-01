package com.ws.integration.entities.pedidoDB;

import org.springframework.lang.NonNull;

import com.ws.integration.entities.pedidoVO.ItemVO;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 28 de fev de 2021
 ***************************************************************/

public class ProdutoDB {
	
	private Number precoUnitario;
	private Long quantidade;
	private String sku;
	private Number valorTotal;
	
	public static ProdutoDB parseDB(@NonNull ItemVO itemVO) {
		ProdutoDB produtoDB = new ProdutoDB();
		produtoDB.setPrecoUnitario(itemVO.getPreco());
		produtoDB.setQuantidade(itemVO.getQuantidade());
		produtoDB.setSku(itemVO.getCodigo());
		produtoDB.setValorTotal(itemVO.getValorTotal());
		return produtoDB;
	}
	
	public Number getPrecoUnitario() {
		return precoUnitario;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public String getSku() {
		return sku;
	}
	public Number getValorTotal() {
		return valorTotal;
	}
	public void setPrecoUnitario(Number precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public void setValorTotal(Number valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
}
