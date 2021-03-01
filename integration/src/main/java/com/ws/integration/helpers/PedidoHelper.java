package com.ws.integration.helpers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.ws.integration.entities.controls.ControlPedidoTransfer;
import com.ws.integration.entities.enums.EStatusPedidoTransfer;
import com.ws.integration.entities.pedidoDB.PedidoDB;
import com.ws.integration.entities.pedidoVO.PedidoVO;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

/***************************************************************
 * @author : Gabriel Henrique Cataldo Nascimento Paes
 * @since : 28 de fev de 2021
 ***************************************************************/

public class PedidoHelper {
	
	public static class BuilderList{
		
		private List<PedidoVO> listPedidosVO;
		
		private BuilderControlTransfer builderControlTransfer;
		
		public BuilderList(@Nullable List<PedidoVO> listPedidos) {
			this.listPedidosVO = listPedidos;
			
			builderControlTransfer = new BuilderControlTransfer();
		}
		
		public boolean validationToSender() {
			return listPedidosVO != null && !listPedidosVO.isEmpty();
		}
		
		public List<PedidoDB> prepareToSender(){
			HashMap<String, List<ControlPedidoTransfer>> listAllPedidosTransf = builderControlTransfer.getAllPedidosTransf();
			
			if(validationToSender()) {

				//aqui passamos por todas as datas anteriores até hoje verificando se existe pedidos pendentes
				if (listAllPedidosTransf != null) {
					for (Map.Entry<String, List<ControlPedidoTransfer>> entry : listAllPedidosTransf.entrySet()) {
						
						List<ControlPedidoTransfer> listControlPedidoTransferValue = entry.getValue();
												
						//abrimos todos os pedidos transferidos esse dia 
						for(int position = 0; position < listControlPedidoTransferValue.size(); position++) {
							
							ControlPedidoTransfer controlPedidoTransfer = listControlPedidoTransferValue.get(position);
							
							for(int i = 0; i < listPedidosVO.size(); i++) {
								PedidoVO pedidoInList = listPedidosVO.get(i);
								
								if (!pedidoInList.getNumero().equals(controlPedidoTransfer.getPedido().getNumero()))  // se esse pedido no controle tiver dado erro e não existe ele na lista, adicionamos para tentar novamente enviar ele
									if (controlPedidoTransfer.getStatus() == EStatusPedidoTransfer.ERROR.ordinal()) 
										listPedidosVO.add(controlPedidoTransfer.getPedido());
								else if (controlPedidoTransfer.getStatus() == EStatusPedidoTransfer.OK.ordinal())  //se repetiu e ja foi enviado com sucesso, removemos para não duplicar
									listPedidosVO.remove(i);
							}	
							
						}
						
					}
				}
			}
			
			List<PedidoDB> listPedidosBD = new ArrayList<PedidoDB>();
			
			for(PedidoVO pedidoVO : listPedidosVO) {
				listPedidosBD.add(PedidoDB.parseDB(pedidoVO));
			}
			
			return listPedidosBD;
		}
		
		
		public void saveLocalPedidoSuccess() {
			builderControlTransfer.saveNewJSON(listPedidosVO);
		}
	}
	
	public static class BuilderControlTransfer{
		
		private final String TODOS_PEDIDOS_TRANSF_PATH = "C:\\control-pedidos\\todosPedidosTransf.json";
		
		public BuilderControlTransfer() {
		}
		
		public HashMap<String, List<ControlPedidoTransfer>> getAllPedidosTransf(){
			try {

				JsonReader reader = new JsonReader(new FileReader(getFileFromServer(TODOS_PEDIDOS_TRANSF_PATH)));
				Type type = new TypeToken<HashMap<String, List<ControlPedidoTransfer>>>(){}.getType();
			    return new Gson().fromJson(reader, type);
			    
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		public void saveNewJSON(@NonNull List<PedidoVO> listPedidosVO) {
			String currentData = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
			HashMap<String, List<ControlPedidoTransfer>> listControlPedidoTransfer = getAllPedidosTransf();
						
			if (listControlPedidoTransfer != null && !listControlPedidoTransfer.isEmpty()) {
				
				for (Map.Entry<String, List<ControlPedidoTransfer>> entry : listControlPedidoTransfer.entrySet()) {
					
					List<ControlPedidoTransfer> listControlPedidoTransferValue = entry.getValue();
						
					//abrimos todos os pedidos transferidos esse dia 
					for(int position = 0; position < listControlPedidoTransferValue.size(); position++) {
						
						ControlPedidoTransfer controlPedidoTransfer = listControlPedidoTransferValue.get(position);
	
						for(int i = 0; i < listPedidosVO.size(); i++) {
							//verifico se ele ja existe para só atualizar, pois se ele está é pq ele deu erro e tentou novamente..
							if (listPedidosVO.get(i).getNumero().equals(controlPedidoTransfer.getPedido().getNumero())) {
								controlPedidoTransfer.setDate(Calendar.getInstance().getTime());
								controlPedidoTransfer.setPedido(listPedidosVO.get(i));
								controlPedidoTransfer.setStatus(listPedidosVO.get(i).getStatusTransfer());
								listPedidosVO.remove(i); //removemos ja que ele ja foi atualizado e vai sobrar só os novos pedidos transferidos
							}
	
						}
						
					}
					
				}
				
				//aqui acrescentamos os pedidos de hoje
				if (listControlPedidoTransfer.containsKey(currentData)) {
					List<ControlPedidoTransfer> currentListControlToday = listControlPedidoTransfer.get(currentData);
					
					for (PedidoVO newPedidoVO : listPedidosVO) { 
						
						ControlPedidoTransfer controlPedidoTransfer = new ControlPedidoTransfer();
						controlPedidoTransfer.setDate(Calendar.getInstance().getTime());
						controlPedidoTransfer.setStatus(newPedidoVO.getStatusTransfer());
						controlPedidoTransfer.setPedido(newPedidoVO);
						
						currentListControlToday.add(controlPedidoTransfer);
					}
					
					listControlPedidoTransfer.put(currentData, currentListControlToday);

				}
				
				
			}else {
			
				listControlPedidoTransfer = new HashMap<String, List<ControlPedidoTransfer>>();
							
				List<ControlPedidoTransfer> listControlPedidoTransfers = new ArrayList<ControlPedidoTransfer>();
				
				for(PedidoVO pedidoVO : listPedidosVO) {
					
					ControlPedidoTransfer controlPedidoTransfer = new ControlPedidoTransfer();
					controlPedidoTransfer.setDate(Calendar.getInstance().getTime());
					controlPedidoTransfer.setStatus(pedidoVO.getStatusTransfer());
					controlPedidoTransfer.setPedido(pedidoVO);
					
					
					listControlPedidoTransfers.add(controlPedidoTransfer);
					
				}
				
				listControlPedidoTransfer.put(currentData, listControlPedidoTransfers);
			
			}
			
			FileWriter fileWrite = null;
			File file = null;
			try {
				
				file = getFileFromServer(TODOS_PEDIDOS_TRANSF_PATH);
				 				
				fileWrite = new FileWriter(file);
				fileWrite.write(new Gson().toJson(listControlPedidoTransfer));
				
			} catch (IOException e) { 
				//todo: send email erro file create
				e.printStackTrace();
			} finally {
				
				if (fileWrite != null) {
										
					try {
						fileWrite.flush();
						fileWrite.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			
			}
			
		}
		
	    private File getFileFromServer(String fileName){
	    	File file = new File(fileName);
			    	
			if (file != null && file.exists()) {
				return file;
			}
			
            return null;
	    }
	}
	
}
