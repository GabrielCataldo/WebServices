package com.ws.integration.scheduledServices;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ws.integration.entities.enums.EStatusPedidoTransfer;
import com.ws.integration.entities.pedidoDB.PedidoDB;
import com.ws.integration.entities.pedidoVO.PedidoVO;
import com.ws.integration.helpers.EmailClientHelper;
import com.ws.integration.helpers.PedidoHelper;
import com.ws.integration.helpers.PedidoHelper.BuilderList;
import com.ws.integration.helpers.request.PedidoRequestHelper;
import com.ws.integration.interfaces.ICallbackAPI;

import retrofit2.Call;
import retrofit2.Response;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 25 de fev de 2021
 ***************************************************************/
 
@Component
@EnableScheduling
public class IntegrationWSJob implements ICallbackAPI<List<PedidoVO>>{
	
	@Value("${ORIGEM_URL}")
	private String ORIGEM_URL;
	@Value("${DESTINO_URL}")
	private String DESTINO_URL;
	@Value("${SEND_EMAIL_ALERT_ERROR}")
	private Boolean SEND_EMAIL_ALERT_ERROR;
	
	private PedidoRequestHelper.BuilderBuscar pedidoRequestBuilderBuscar;
	private PedidoRequestHelper.BuilderSend pedidoRequestBuilderSend;
	private PedidoHelper.BuilderList pedidoHelperBuilderList;
	
	private int codeErrorSend = 0;
	
    @Scheduled(fixedDelay = 60000, zone = "America/Sao_Paulo") 
	public void trasferRequests() throws Exception {
    	System.out.println("LOG-PROJECT: ------ Transferindo (" + new SimpleDateFormat("dd/MM/yyyy hh:ss").format(Calendar.getInstance().getTime()) + ") ------");
    	
    	setupHelpers();
    	
    	Calendar calendarInicial = Calendar.getInstance();
    	calendarInicial.set(Calendar.HOUR_OF_DAY, 0);
    	calendarInicial.set(Calendar.MINUTE, 0);
    	
    	Calendar calendarFinal= Calendar.getInstance();
    	calendarFinal.set(Calendar.HOUR_OF_DAY, 23);
    	calendarFinal.set(Calendar.MINUTE, 59);
    	
    	System.out.println("LOG-PROJECT: GET -> "+ ORIGEM_URL + "WS/Pedido");
    	pedidoRequestBuilderBuscar.pedidos(calendarInicial.getTime(), calendarFinal.getTime(), this);
    	
	}
    
    private void setupHelpers() {
    	pedidoRequestBuilderBuscar = new PedidoRequestHelper.BuilderBuscar(ORIGEM_URL, SEND_EMAIL_ALERT_ERROR);
    	pedidoRequestBuilderSend = new PedidoRequestHelper.BuilderSend(DESTINO_URL, false);
    }
    
    private void dataTransfer(@Nullable List<PedidoVO> listPedidosVO) {
    	pedidoHelperBuilderList = new PedidoHelper.BuilderList(listPedidosVO);
    	
    	if (!pedidoHelperBuilderList.validationToSender()) return;
   
    	List<PedidoDB> listPedidosDB = pedidoHelperBuilderList.prepareToSender();
    	
    	System.out.println("LOG-PROJECT: POST -> "+ DESTINO_URL + "v1/pedido");
    	
    	for (PedidoDB pedidoDB : listPedidosDB) {
    		
    		pedidoRequestBuilderSend.pedidos(pedidoDB, new ICallbackAPI<Void>() {
				@Override
				public void onSuccessResponse(Response<Void> response) {
					System.out.println("LOG-PROJECT: POST response -> "+ response.code());
					
					for (PedidoVO pedidoVO : listPedidosVO) {
						if(pedidoVO.getNumero().equals(pedidoDB.getNumero()))
							pedidoVO.setStatusTransfer(EStatusPedidoTransfer.OK.ordinal());
					}
					
					pedidoHelperBuilderList = new BuilderList(listPedidosVO);
			    	pedidoHelperBuilderList.saveLocalPedidoSuccess();
					
				}
				
				@Override
				public void onFailureResponse(Call<Void> call, Throwable t) {
					if(t != null) {
						t.printStackTrace();
						System.out.println("LOG-PROJECT: POST response error -> "+ t.getMessage());
					}
										
					for (PedidoVO pedidoVO : listPedidosVO) {
						if(pedidoVO.getNumero().equals(pedidoDB.getNumero()))
							pedidoVO.setStatusTransfer(EStatusPedidoTransfer.ERROR.ordinal());
					}
					
					//todo no onfailure colocamos o response para obter o codigo de resposta e colocar na variavel 
					pedidoHelperBuilderList = new BuilderList(listPedidosVO);
			    	pedidoHelperBuilderList.saveLocalPedidoSuccess();

				}
			});
    	
    	}
    	
    	if (codeErrorSend != 0) 
			//todo aqui enviamos os erros que estão acontecendo
    		new EmailClientHelper.Builder().sendEmailException(new Exception("POST Error code: " + codeErrorSend));
    	    	
    }
    
	@Override
	public void onSuccessResponse(Response<List<PedidoVO>> response) {
		dataTransfer(response.body());
	}

	@Override
	public void onFailureResponse(Call<List<PedidoVO>> call, Throwable t) {
		if(t != null) {
			t.printStackTrace();
		}
	}
}
