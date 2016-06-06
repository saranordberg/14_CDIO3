package cdio.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.gwt.core.client.GWT;

import cdio.dal.Connector;
import cdio.dal.dto.UserDTO;
import cdio.dal.exception.DALException;

import java.util.ArrayList;

//
public class MySQLOperatoerDAO implements OperatoerDAO
{
	public UserDTO getOperatoer(int oprId) throws DALException
	{
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM operatoer WHERE opr_id = ?", oprId);
			
			if (!rs.first())
				throw new DALException("Operatoeren " + oprId + " findes ikke");
			
			return new UserDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"),
					rs.getString("cpr"), rs.getString("password"));
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		
	}
	
	public void createOperatoer(UserDTO opr) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate("INSERT INTO operatoer(opr_navn, ini, cpr, password) VALUES (?, ?, ?, ?)",
					opr.oprNavn, opr.ini, opr.cpr, opr.password);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateOperatoer(UserDTO opr) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate("UPDATE operatoer SET  opr_navn = ?, ini =  ?, cpr = ?, password = ? WHERE opr_id = ?",
					opr.oprNavn, opr.ini, opr.cpr, opr.password, opr.oprId);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * TODO: Modify to use view
	 * 
	 * @see daointerfaces01917.OperatoerDAO#getOperatoerList()
	 */
	public List<UserDTO> getOperatoerList() throws DALException
	{
		List<UserDTO> list = new ArrayList<UserDTO>();
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM user");
			while (rs.next())
			{
				list.add(new UserDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"),
						rs.getString("cpr"), rs.getString("password")));
			}
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		return list;
	}
	
}
