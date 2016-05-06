package cdio.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cdio.dal.*;
import cdio.dal.dto.OperatoerDTO;
import cdio.dal.exception.DALException;


@RemoteServiceRelativePath("operatorService")
public interface OperatorService extends RemoteService {
	OperatoerDTO getOperatoer(int oprId) throws DALException;
	void createOperator(OperatoerDTO opr) throws DALException;
	void updateOperator(OperatoerDTO opr) throws DALException;
	List<OperatoerDTO> listOperator() throws DALException;
	
	
}