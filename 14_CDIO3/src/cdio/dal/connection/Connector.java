package cdio.dal.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import cdio.dal.dao.interfaces.DALException;

public class Connector
{
	/**
	 * To connect to a MySQL-server
	 * 
	 * @param url
	 *            must have the form "jdbc:mysql://<server>/<database>" for
	 *            default port (3306) OR
	 *            "jdbc:mysql://<server>:<port>/<database>" for specific port
	 *            more formally "jdbc:subprotocol:subname"
	 * 
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SQLException
	 */
	
	private static Connector instance;
	private static Connection conn;
	private static Statement stm;
	
	public static Connector getInstance()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		
		if(instance == null)
			instance = new Connector();
		
		return instance;
	}
	
	//
	private Connection connectToDatabase(String url, String username, String password)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		// call the driver class' no argument constructor
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		// get Connection-object via DriverManager
		return (Connection) DriverManager.getConnection(url, username, password);
	}
	
	private Connector() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		conn = connectToDatabase("jdbc:mysql://" + Constant.server + ":" + Constant.port + "/" + Constant.database,
				Constant.username, Constant.password);
		stm = conn.createStatement();
	}
	
	public ResultSet doQuery(String query, Object... parameters) throws DALException
	{
		try
		{
			PreparedStatement statement = conn.prepareStatement(query);
			
			int i = 1;
			for (Object parameter : parameters)
			{
				if (parameter instanceof Timestamp)
					statement.setTimestamp(i, (Timestamp)parameter);
				else if (parameter instanceof Integer)
					statement.setInt(i, (int) parameter);
				else if (parameter instanceof Double)
					statement.setDouble(i, (double) parameter);
				else if (parameter instanceof Date)
					statement.setDate(i, new java.sql.Date(((Date) parameter).getTime()));
				else if (parameter instanceof Float)
					statement.setFloat(i, (float) parameter);
				else if (parameter instanceof String)
					statement.setString(i, (String) parameter);
				
				i++;
			}
			
			return statement.executeQuery();
		}
		catch (SQLException e)
		{
			throw new DALException(e);
		}
	}
	
	public int doUpdate(String query, Object... parameters) throws DALException
	{
		try
		{
			
			PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			for (Object parameter : parameters)
			{
				if (parameter instanceof Timestamp)
					statement.setTimestamp(i, (Timestamp)parameter);
				else if (parameter instanceof Integer)
					statement.setInt(i, (int) parameter);
				else if (parameter instanceof Double)
					statement.setDouble(i, (double) parameter);
				else if (parameter instanceof Date)
					statement.setDate(i, new java.sql.Date(((Date) parameter).getTime()));
				else if (parameter instanceof Float)
					statement.setFloat(i, (float) parameter);
				else if (parameter instanceof String)
					statement.setString(i, (String) parameter);
				
				i++;
			}
			
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			
			if(rs.next())
				return rs.getInt(1);
			
			return 0;
		}
		catch (SQLException e)
		{
			throw new DALException(e);
		}
	}
	
}