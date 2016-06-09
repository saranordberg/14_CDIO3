package cdio.client.implementation;

import java.util.List;

import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.UserDTO;

public interface UserServiceClientInt
{
	UserDTO getUser(int oprId) throws DALException;
	
	void createUser(UserDTO opr) throws DALException;
	
	void updateUser(UserDTO opr) throws DALException;
	
	List<UserDTO> listUser() throws DALException;
}
