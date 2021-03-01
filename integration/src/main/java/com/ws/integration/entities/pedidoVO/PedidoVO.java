package com.ws.integration.entities.pedidoVO;

import java.util.List;

import com.ws.integration.entities.abstratos.Pessoa;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 27 de fev de 2021
 ***************************************************************/
public class PedidoVO {

	private Pessoa cliente;
	private String data;
	private String hora;
	private List<ItemVO> itens;
	private String numero;
	private Pessoa representante;
	private Number valorDesconto;
	private Number valorFrete;
	private Integer statusTransfer;
	

	public Integer getStatusTransfer() {
		return statusTransfer;
	}
	public void setStatusTransfer(Integer statusTransfer) {
		this.statusTransfer = statusTransfer;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Pessoa getCliente() {
		return cliente;
	}
	public String getData() {
		return data;
	}
	public List<ItemVO> getItens() {
		return itens;
	}
	public String getNumero() {
		return numero;
	}
	public Pessoa getRepresentante() {
		return representante;
	}
	public Number getValorDesconto() {
		return valorDesconto;
	}
	public Number getValorFrete() {
		return valorFrete;
	}
	public void setCliente(Pessoa cliente) {
		this.cliente = cliente;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setItens(List<ItemVO> itens) {
		this.itens = itens;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setRepresentante(Pessoa representante) {
		this.representante = representante;
	}
	public void setValorDesconto(Number valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	public void setValorFrete(Number valorFrete) {
		this.valorFrete = valorFrete;
	}
	
}
