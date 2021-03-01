package com.ws.integration.entities.pedidoDB;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;

import com.ws.integration.entities.pedidoVO.ItemVO;
import com.ws.integration.entities.pedidoVO.PedidoVO;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 27 de fev de 2021
 ***************************************************************/
public class PedidoDB {

	private String cpfCnpjCliente;
	private String cpfCnpjRepresentante;
	private EnderecoDB enderecoEntrega;
	private List<ProdutoDB> produtos;
	private String numero;
	private Number valorDesconto;
	private Number valorFrete;
	private Number valorTotal;
	private Integer statusTransfer;
	
	
	public static PedidoDB parseDB(@NonNull PedidoVO pedidoVO) {
		PedidoDB pedidoDB = new PedidoDB();
		List<ProdutoDB> listProdutosDB = new ArrayList<ProdutoDB>();
		
		pedidoDB.setCpfCnpjCliente(pedidoVO.getCliente().getCpfCnpj());
		pedidoDB.setCpfCnpjRepresentante(pedidoVO.getRepresentante().getCpfCnpj());
		pedidoDB.setEnderecoEntrega(EnderecoDB.parseDB(pedidoVO.getCliente().getEndereco()));
		pedidoDB.setNumero(pedidoVO.getNumero());
		pedidoDB.setValorDesconto(pedidoVO.getValorDesconto());
		pedidoDB.setValorFrete(pedidoVO.getValorFrete());
		
		Number valorTotalPedido = 0;
		
		for(ItemVO itemVO : pedidoVO.getItens()) {
			valorTotalPedido = valorTotalPedido.doubleValue() + itemVO.getValorTotal().doubleValue();
			listProdutosDB.add(ProdutoDB.parseDB(itemVO));
		}
		
		pedidoDB.setProdutos(listProdutosDB);
		pedidoDB.setValorTotal(valorTotalPedido);
		
		return pedidoDB;
	}
	
	
	
	public Integer getStatusTransfer() {
		return statusTransfer;
	}



	public void setStatusTransfer(Integer statusTransfer) {
		this.statusTransfer = statusTransfer;
	}



	public String getCpfCnpjCliente() {
		return cpfCnpjCliente;
	}
	public String getCpfCnpjRepresentante() {
		return cpfCnpjRepresentante;
	}
	public EnderecoDB getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public String getNumero() {
		return numero;
	}
	public Number getValorDesconto() {
		return valorDesconto;
	}
	public Number getValorFrete() {
		return valorFrete;
	}
	public Number getValorTotal() {
		return valorTotal;
	}
	public void setCpfCnpjCliente(String cpfCnpjCliente) {
		this.cpfCnpjCliente = cpfCnpjCliente;
	}
	public void setCpfCnpjRepresentante(String cpfCnpjRepresentante) {
		this.cpfCnpjRepresentante = cpfCnpjRepresentante;
	}
	public void setEnderecoEntrega(EnderecoDB enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	public void setValorDesconto(Number valorDesconto) {
		this.valorDesconto = valorDesconto;
	}
	public void setValorFrete(Number valorFrete) {
		this.valorFrete = valorFrete;
	}
	public void setValorTotal(Number valorTotal) {
		this.valorTotal = valorTotal;
	}


	public List<ProdutoDB> getProdutos() {
		return produtos;
	}


	public void setProdutos(List<ProdutoDB> produtos) {
		this.produtos = produtos;
	}
	

	
	
}
