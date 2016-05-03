package example.cdio.client.service;

import java.util.List;
import example.cdio.client.Operator.*;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("")
public interface OperatorService extends RemoteService {
	OperatoerDTO getOperatoer(int oprId);
	void createOperator(OperatoerDTO opr);
	void updateOperator(OperatoerDTO opr);
	List<OperatoerDTO> listOperator();
	
	
}