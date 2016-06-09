package cdio.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import dto01917.UserDTO;

public interface OperatorServiceAsync
{
	void getOperatoer(int userId, String token, AsyncCallback callback);
	
	void createOperator(UserDTO user, String token, AsyncCallback callback);
	
	void updateOperator(UserDTO user, String token, AsyncCallback callback);
	
	void listOperator(int userID, String token, AsyncCallback callback);
}
