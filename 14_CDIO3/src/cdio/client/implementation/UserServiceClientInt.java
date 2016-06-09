package cdio.client.implementation;

import java.util.List;

import daointerfaces01917.DALException;
import dto01917.UserDTO;

public interface UserServiceClientInt
{
	UserDTO getUser(int oprId) throws DALException;
	
	void createUser(UserDTO opr) throws DALException;
	
	void updateUser(UserDTO opr) throws DALException;
	
	List<UserDTO> listUser() throws DALException;
}
