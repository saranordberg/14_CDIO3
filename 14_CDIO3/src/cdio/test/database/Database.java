package cdio.test.database;
import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.*;

import org.junit.Test;

import cdio.dal.Connector;
import cdio.dal.Constant;

public class Database
{
	private Connector db;
	
	@Test
	public void testConnection() {
		try
		{
			db = new Connector(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
