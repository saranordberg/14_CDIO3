package cdio.dal.dto;

import java.util.List;

import cdio.dal.OperatoerDTO;
import cdio.dal.exception.DALException;



public interface OperatoerDAO {
	OperatoerDTO getOperatoer(int oprId) throws DALException;
	List<OperatoerDTO> getOperatoerList() throws DALException;
	void createOperatoer(OperatoerDTO opr) throws DALException;
	void updateOperatoer(OperatoerDTO opr) throws DALException;
}
