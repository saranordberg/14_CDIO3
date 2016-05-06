package cdio.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdio.dal.dto.OperatoerDTO;

public interface OperatorServiceAsync
{
	void getOperatoer(int oprId, AsyncCallback callback);
	
	void createOperator(OperatoerDTO opr, AsyncCallback callback);
	
	void updateOperator(OperatoerDTO opr, AsyncCallback callback);
	
	void listOperator(AsyncCallback callback);
}
