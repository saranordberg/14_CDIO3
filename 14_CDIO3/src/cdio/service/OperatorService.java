package cdio.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cdio.dal.*;
import cdio.dal.dto.OperatoerDTO;


@RemoteServiceRelativePath("")
public interface OperatorService extends RemoteService {
	OperatoerDTO getOperatoer(int oprId);
	void createOperator(OperatoerDTO opr);
	void updateOperator(OperatoerDTO opr);
	List<OperatoerDTO> listOperator();
	
	
}