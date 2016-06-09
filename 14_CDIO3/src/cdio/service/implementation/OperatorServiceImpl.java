package cdio.service.implementation;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdio.service.OperatorService;
import daoimpl01917.MySQLUserDAO;
import daointerfaces01917.DALException;
import dto01917.UserDTO;

public class OperatorServiceImpl extends RemoteServiceServlet implements OperatorService
{	
	@Override
	public UserDTO getOperatoer(int oprId) throws DALException
	{
		MySQLUserDAO conn = new MySQLUserDAO();
		return conn.getUser(oprId);
	}
	
	@Override
	public void createOperator(UserDTO opr) throws DALException
	{
		MySQLUserDAO conn = new MySQLUserDAO();
		conn.createUser(opr);
	}
	
	@Override
	public void updateOperator(UserDTO opr) throws DALException
	{
		MySQLUserDAO conn = new MySQLUserDAO();
		conn.updateUser(opr);
	}
	
	@Override
	public List<UserDTO> listOperator(int userID, String token) throws DALException
	{
		MySQLUserDAO conn = new MySQLUserDAO();
		return conn.getUserList();
	}
	
}
