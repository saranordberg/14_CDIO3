package cdio.client.implementation;

import java.util.List;

import cdio.dal.dto.UserDTO;
import cdio.dal.exception.DALException;

public interface OperatorServiceClientInt
{
	UserDTO getOperatoer(int oprId) throws DALException;
	
	void createOperator(UserDTO opr) throws DALException;
	
	void updateOperator(UserDTO opr) throws DALException;
	
	List<UserDTO> listOperator() throws DALException;
}
