package example.cdio.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import example.cdio.client.Operator.OperatoerDTO;

public interface OperatorServiceAsync {
	void getOperatoer(int oprId, AsyncCallback callback);
	void createOperator(OperatoerDTO opr, AsyncCallback callback);
	void updateOperator(OperatoerDTO opr, AsyncCallback callback);
	void listOperator(AsyncCallback callback);
}
