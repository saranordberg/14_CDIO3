package cdio.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.UserDTO;

@RemoteServiceRelativePath("userService")
public interface UserService extends RemoteService
{
	UserDTO getUser(int oprId, String token) throws DALException;
	
	void createUser(UserDTO opr, String token) throws DALException;
	
	void updateUser(UserDTO opr, String token) throws DALException;
	
	List<UserDTO> listUser(int userID, String token) throws DALException;
	
	UserDTO login (UserDTO opr) throws DALException;
}