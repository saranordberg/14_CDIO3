package cdio.service.implementation;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdio.dal.dao.MySQLReceptDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.ReceptDTO;
import cdio.service.PrescriptionService;
import cdio.service.tokenhandler.TokenValidator;

public class PrescriptionServiceImpl extends RemoteServiceServlet implements PrescriptionService
{
	@Override
	public ReceptDTO getRecept(int receptId, String token) throws DALException
	{
		TokenValidator.validateToken(token);
		MySQLReceptDAO conn = new MySQLReceptDAO();
		return conn.getRecept(receptId);
	}
	
	@Override
	public void createRecept(ReceptDTO recept, String token) throws DALException
	{
		TokenValidator.validateToken(token);
		MySQLReceptDAO conn = new MySQLReceptDAO();
		conn.createRecept(recept);
	}
	
	@Override
	public void updateRecept(ReceptDTO recept, String token) throws DALException
	{
		TokenValidator.validateToken(token);
		MySQLReceptDAO conn = new MySQLReceptDAO();
		conn.updateRecept(recept);
	}
	
	@Override
	public List<ReceptDTO> listRecept(String token) throws DALException
	{
		TokenValidator.validateToken(token);
		MySQLReceptDAO conn = new MySQLReceptDAO();
		return conn.getReceptList();
	}
}
