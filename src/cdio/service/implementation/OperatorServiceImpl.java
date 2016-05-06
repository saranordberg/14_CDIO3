package cdio.service.implementation;

import java.sql.SQLException;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdio.dal.Connector;
import cdio.dal.Constant;
import cdio.dal.dao.MySQLOperatoerDAO;
import cdio.dal.dto.OperatoerDTO;
import cdio.dal.exception.DALException;
import cdio.service.OperatorService;

public class OperatorServiceImpl extends RemoteServiceServlet implements OperatorService 
{
	

	@Override
	public OperatoerDTO getOperatoer(int oprId)
	{
		try
		{
			Connector db = new Connector(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		try
		{
			
			return conn.getOperatoer(oprId);
		}
		catch (DALException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void createOperator(OperatoerDTO opr) throws DALException
	{
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		conn.createOperatoer(opr);
	}

	@Override
	public void updateOperator(OperatoerDTO opr) throws DALException
	{
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		conn.updateOperatoer(opr);
	}

	@Override
	public List<OperatoerDTO> listOperator() throws DALException
	{
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		return conn.getOperatoerList();
	}
	
}
