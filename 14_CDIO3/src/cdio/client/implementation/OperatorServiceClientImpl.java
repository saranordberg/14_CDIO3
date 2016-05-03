package cdio.client.implementation;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import cdio.dal.dto.OperatoerDTO;
import cdio.service.OperatorService;
import cdio.service.OperatorServiceAsync;

public class OperatorServiceClientImpl {
	private OperatorServiceAsync service;
	
	public OperatorServiceClientImpl(String url) {
		System.out.println(url);
		this.service = GWT.create(OperatorService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);	
	}
	
	public void getOperatoer(int oprId) {
		service.getOperatoer(1, new GetOperatorCallback());
	}
	private class GetOperatorCallback implements AsyncCallback {

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error has accoured");
			
		}

		@Override
		public void onSuccess(Object result) {
//			if(result instanceof String) {
//				String greeting = (String) result;
//				//maingui.updateLabel(greeting);
//			}
//			else if(result instanceof Integer) {
//				int sum = (Integer) result;
//				//maingui.updateSumLabel(sum);
//			}
			
			GWT.log(((OperatoerDTO)result).password);
			
		}
		
	}
}
