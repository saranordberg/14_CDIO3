package cdio.dal.dao;

import java.util.List;

import cdio.dal.dto.UserDTO;
import cdio.dal.exception.DALException;

public interface OperatoerDAO
{
	UserDTO getOperatoer(int oprId) throws DALException;
	
	List<UserDTO> getOperatoerList() throws DALException;
	
	void createOperatoer(UserDTO opr) throws DALException;
	
	void updateOperatoer(UserDTO opr) throws DALException;
}
