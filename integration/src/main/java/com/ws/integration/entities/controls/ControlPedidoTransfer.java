package com.ws.integration.entities.controls;

import java.util.Date;

import com.ws.integration.entities.pedidoVO.PedidoVO;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 28 de fev de 2021
 ***************************************************************/
public class ControlPedidoTransfer {
	
	private Date date;
	private Integer status;
	private PedidoVO pedido;
	
	
	public Date getDate() {
		return date;
	}
	public Integer getStatus() {
		return status;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public PedidoVO getPedido() {
		return pedido;
	}
	public void setPedido(PedidoVO pedido) {
		this.pedido = pedido;
	}

	

}
