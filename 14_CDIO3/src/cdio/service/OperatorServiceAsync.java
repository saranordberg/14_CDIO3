package cdio.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdio.dal.dto.UserDTO;

public interface OperatorServiceAsync
{
	void getOperatoer(int oprId, AsyncCallback callback);
	
	void createOperator(UserDTO opr, AsyncCallback callback);
	
	void updateOperator(UserDTO opr, AsyncCallback callback);
	
	void listOperator(int userID, String token, AsyncCallback callback);
}
