package cdio.service.implementation;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdio.dal.dao.MySQLRaavareDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.RaavareDTO;
import cdio.service.RawMaterialService;
import cdio.service.tokenhandler.TokenValidator;

public class RawMaterialServiceImpl extends RemoteServiceServlet implements RawMaterialService
{
	@Override
	public RaavareDTO getRaavare(int raavareId, String token) throws DALException
	{
		TokenValidator.validateToken(token);
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		return conn.getRaavare(raavareId);
	}
	
	@Override
	public void createRaavare(RaavareDTO raavare, String token) throws DALException
	{
		TokenValidator.validateToken(token);
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		conn.createRaavare(raavare);
	}
	
	@Override
	public void updateRaavare(RaavareDTO raavare, String token) throws DALException
	{
		TokenValidator.validateToken(token);
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		conn.updateRaavare(raavare);
	}
	
	@Override
	public List<RaavareDTO> listRaavare(String token) throws DALException
	{
		TokenValidator.validateToken(token);
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		return conn.getRaavareList();
	}
	
}
