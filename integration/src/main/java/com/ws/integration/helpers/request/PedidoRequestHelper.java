package com.ws.integration.helpers.request;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.ws.integration.entities.pedidoDB.PedidoDB;
import com.ws.integration.entities.pedidoVO.PedidoVO;
import com.ws.integration.helpers.RetrofitHelper;
import com.ws.integration.interfaces.ICallbackAPI;
import com.ws.integration.interfaces.IRequestsIntegration;

import retrofit2.Call;
import retrofit2.Retrofit;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 27 de fev de 2021
 ***************************************************************/

public class PedidoRequestHelper {
	
	public static class BuilderBuscar extends BaseCallBackAPI<List<PedidoVO>>{
		
		private final Retrofit retrofit;
		
		public BuilderBuscar(@NonNull String baseURL, boolean sendAlertToEmail) {
			super(sendAlertToEmail);
			
			retrofit = RetrofitHelper.BuilderDefault(baseURL);
		}
		
		public void pedidos(@NonNull Date dataInicial, @NonNull Date dataFinal, @Nullable ICallbackAPI<List<PedidoVO>> iCallbackAPI) {
			this.iCallbackAPI = iCallbackAPI;

			if (retrofit == null) {
				//todo: enviar email exception
				return;
			}
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			
			String dataIncialFormat = simpleDateFormat.format(dataInicial);
			String dataFinalFormat = simpleDateFormat.format(dataFinal);
			
            IRequestsIntegration iRequestsIntegration = retrofit.create(IRequestsIntegration.class);
            Call<List<PedidoVO>> call = iRequestsIntegration.buscarPedidos(dataIncialFormat, dataFinalFormat);
            call.enqueue(this);
		}

	}
	
	
	public static class BuilderSend extends BaseCallBackAPI<Void>{
		
		private final Retrofit retrofit;
		
		public BuilderSend(@NonNull String baseURL, boolean sendAlertToEmail) {
			super(sendAlertToEmail);
			
			retrofit = RetrofitHelper.BuilderDefault(baseURL);
		}
		
		public void pedidos(@NonNull PedidoDB pedidoDB, @Nullable ICallbackAPI<Void> iCallbackAPI) {
			this.iCallbackAPI = iCallbackAPI;

			if (retrofit == null) {
				//todo: enviar email exception
				return;
			}		
			
            IRequestsIntegration iRequestsIntegration = retrofit.create(IRequestsIntegration.class);
            Call<Void> call = iRequestsIntegration.salvarPedidos(pedidoDB);
            call.enqueue(this);
            
		}

	}

}
