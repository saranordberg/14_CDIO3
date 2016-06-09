package cdio.service.implementation;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdio.service.OperatorService;
import cdio.service.RawMaterialsService;
import daoimpl01917.MySQLRaavareDAO;
import daoimpl01917.MySQLUserDAO;
import daointerfaces01917.DALException;
import dto01917.RaavareDTO;
import dto01917.UserDTO;


public class RawMaterialsServiceImpl extends RemoteServiceServlet implements RawMaterialsService
{
	@Override
	public RaavareDTO getRaavare(int raavareId, String token) throws DALException
	{
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		return conn.getRaavare(raavareId);
	}
	
	@Override
	public void createRaavare(RaavareDTO raavare, String token) throws DALException
	{
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		conn.createRaavare(raavare);
	}
	
	@Override
	public void updateRaavare(RaavareDTO raavare, String token) throws DALException
	{
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		conn.updateRaavare(raavare);
	}
	
	@Override
	public List<RaavareDTO> listRaavare(String token) throws DALException
	{
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		return conn.getRaavareList();
	}
	

}
