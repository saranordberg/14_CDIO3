package cdio.service.implementation;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdio.service.PrescriptionService;
import daoimpl01917.MySQLReceptDAO;
import daointerfaces01917.DALException;
import dto01917.ReceptDTO;

public class PrescriptionServiceImpl extends RemoteServiceServlet implements PrescriptionService
{
	@Override
	public ReceptDTO getRecept(int receptId, String token) throws DALException
	{
		MySQLReceptDAO conn = new MySQLReceptDAO();
		return conn.getRecept(receptId);
	}
	
	@Override
	public void createRecept(ReceptDTO recept, String token) throws DALException
	{
		MySQLReceptDAO conn = new MySQLReceptDAO();
		conn.createRecept(recept);
	}
	
	@Override
	public void updateRecept(ReceptDTO recept, String token) throws DALException
	{
		MySQLReceptDAO conn = new MySQLReceptDAO();
		conn.updateRecept(recept);
	}
	
	@Override
	public List<ReceptDTO> listRecept(String token) throws DALException
	{
		MySQLReceptDAO conn = new MySQLReceptDAO();
		return conn.getReceptList();
	}
}
