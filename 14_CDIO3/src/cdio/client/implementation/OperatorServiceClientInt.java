package cdio.client.implementation;

import java.util.List;

import cdio.dal.dto.OperatoerDTO;
import cdio.dal.exception.DALException;

public interface OperatorServiceClientInt
{
	OperatoerDTO getOperatoer(int oprId) throws DALException;
	
	void createOperator(OperatoerDTO opr) throws DALException;
	
	void updateOperator(OperatoerDTO opr) throws DALException;
	
	List<OperatoerDTO> listOperator() throws DALException;
}
