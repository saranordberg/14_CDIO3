package cdio.service.implementation;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdio.dal.dao.MySQLUserDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.UserDTO;
import cdio.service.UserService;

public class UserServiceImpl extends RemoteServiceServlet implements UserService
{
	@Override
	public UserDTO getUser(int userId, String token) throws DALException
	{
		MySQLUserDAO conn = new MySQLUserDAO();
		return conn.getUser(userId);
	}
	
	@Override
	public void createUser(UserDTO user, String token) throws DALException
	{
		MySQLUserDAO conn = new MySQLUserDAO();
		conn.createUser(user);
	}
	
	@Override
	public void updateUser(UserDTO user, String token) throws DALException
	{
		MySQLUserDAO conn = new MySQLUserDAO();
		conn.updateUser(user);
	}
	
	@Override
	public List<UserDTO> listUser(int userID, String token) throws DALException
	{
		MySQLUserDAO conn = new MySQLUserDAO();
		return conn.getUserList();
	}

	@Override
	public UserDTO login(UserDTO user) throws DALException
	{
		MySQLUserDAO conn = new MySQLUserDAO();
		return conn.Login(user);
	}
	
}
