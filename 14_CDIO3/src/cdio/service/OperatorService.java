package cdio.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cdio.dal.*;
import daointerfaces01917.DALException;
import dto01917.UserDTO;

@RemoteServiceRelativePath("operatorService")
public interface OperatorService extends RemoteService
{
	UserDTO getOperatoer(int oprId) throws DALException;
	
	void createOperator(UserDTO opr) throws DALException;
	
	void updateOperator(UserDTO opr) throws DALException;
	
	List<UserDTO> listOperator(int userID, String token) throws DALException;
	
}