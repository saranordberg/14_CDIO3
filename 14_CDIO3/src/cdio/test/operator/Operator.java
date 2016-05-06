package cdio.test.operator;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import cdio.dal.Connector;
import cdio.dal.Constant;
import cdio.dal.dao.MySQLOperatoerDAO;
import cdio.dal.dto.OperatoerDTO;
import cdio.dal.exception.DALException;

public class Operator
{
	Connector db;
	
	@Before
	public void setup()
	{
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
	
	@Test
	public void GetOperator()
	{
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		
		try
		{
			OperatoerDTO op = conn.getOperatoer(1);
			assertNotNull(op);
		}
		catch (DALException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
