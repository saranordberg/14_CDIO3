package cdio.test.database;

import java.sql.SQLException;

import org.junit.Test;

import cdio.dal.Connector;

public class Database
{
	private Connector db;
	
	@Test
	public void testConnection()
	{
		try
		{
			db = Connector.getInstance();
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
