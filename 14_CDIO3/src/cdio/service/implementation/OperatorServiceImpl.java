package cdio.service.implementation;

import java.sql.SQLException;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdio.dal.Connector;
import cdio.dal.Constant;
import cdio.dal.dao.MySQLOperatoerDAO;
import cdio.dal.dto.UserDTO;
import cdio.dal.exception.DALException;
import cdio.service.OperatorService;

public class OperatorServiceImpl extends RemoteServiceServlet implements OperatorService
{	
	@Override
	public UserDTO getOperatoer(int oprId) throws DALException
	{
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		return conn.getOperatoer(oprId);
	}
	
	@Override
	public void createOperator(UserDTO opr) throws DALException
	{
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		conn.createOperatoer(opr);
	}
	
	@Override
	public void updateOperator(UserDTO opr) throws DALException
	{
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		conn.updateOperatoer(opr);
	}
	
	@Override
	public List<UserDTO> listOperator(int userID, String token) throws DALException
	{
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		return conn.getOperatoerList();
	}
	
}
