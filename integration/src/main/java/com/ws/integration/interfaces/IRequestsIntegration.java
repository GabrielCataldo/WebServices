package com.ws.integration.interfaces;

import java.util.List;

import com.ws.integration.entities.pedidoDB.PedidoDB;
import com.ws.integration.entities.pedidoVO.PedidoVO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 27 de fev de 2021
 ***************************************************************/

public interface IRequestsIntegration {
	
	@GET("WS/Pedido")
	Call<List<PedidoVO>> buscarPedidos(@Query("data_inicial") String dataInicial, @Query("data_final") String dataFinal);
	
	@POST("v1/pedido")
	Call<Void> salvarPedidos(@Body PedidoDB pedido);
}
