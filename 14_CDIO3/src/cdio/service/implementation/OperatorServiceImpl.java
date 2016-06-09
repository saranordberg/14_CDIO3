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
	public UserDTO getOperatoer(int userId, String token) throws DALException
	{
		MySQLUserDAO conn = new MySQLUserDAO();
		return conn.getUser(userId);
	}
	
	@Override
	public void createOperator(UserDTO user, String token) throws DALException
	{
		MySQLUserDAO conn = new MySQLUserDAO();
		conn.createUser(user);
	}
	
	@Override
	public void updateOperator(UserDTO user, String token) throws DALException
	{
		MySQLUserDAO conn = new MySQLUserDAO();
		conn.updateUser(user);
	}
	
	@Override
	public List<UserDTO> listOperator(int userID, String token) throws DALException
	{
		MySQLUserDAO conn = new MySQLUserDAO();
		return conn.getUserList();
	}
	
}
