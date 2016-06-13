package cdio.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdio.dal.connection.Connector;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dao.interfaces.UserDAO;
import cdio.dal.dto.UserDTO;

//
public class MySQLUserDAO implements UserDAO
{
	public UserDTO getUser(int userId) throws DALException
	{
		
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM user WHERE user_id = ?", userId);
			if (!rs.first())
				throw new DALException("User " + userId + " findes ikke");
			
			return new UserDTO(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"),
					rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getInt("user_level"));
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		
	}
	
	public void createUser(UserDTO user) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate(
					"INSERT INTO user(user_id, first_name, last_name, ini, cpr, password, user_level) VALUES (?, ?, ?, ?, ?, ?, ?)",
					user.userId, user.firstName, user.lastName, user.ini, user.cpr, user.password, user.level);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
	}
	
	public void updateUser(UserDTO user) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate(
					"UPDATE user SET  first_name = ?, last_name = ?, ini =  ?, cpr = ?, password = ?, user_level = ? WHERE user_id = ?",
					user.firstName, user.lastName, user.ini, user.cpr, user.password, user.level, user.userId);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
	}
	
	/*
	 * TODO: Modify to use view
	 * 
	 * @see daointerfaces01917.UserDAO#getUserList()
	 */
	public List<UserDTO> getUserList() throws DALException
	{
		List<UserDTO> list = new ArrayList<UserDTO>();
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM user");
			while (rs.next())
			{
				list.add(new UserDTO(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getInt("user_level")));
			}
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		return list;
	}
	
	public UserDTO Login(UserDTO user) throws DALException
	{
		try
		{
			UserDTO loginUser = getUser(user.userId);
			
			if (user.password.equals(loginUser.password)){
				return loginUser;
			}
			else {
				throw new DALException("User " + user.userId + " findes ikke");
			}
			
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		
	}
	
}
