package cdio.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdio.dal.dto.UserDTO;

public interface UserServiceAsync
{
	void getUser(int userId, String token, AsyncCallback callback);
	
	void createUser(UserDTO user, String token, AsyncCallback callback);
	
	void updateUser(UserDTO user, String token, AsyncCallback callback);
	
	void listUser(String token, AsyncCallback callback);
	
	void login(UserDTO user, AsyncCallback callback);
}
