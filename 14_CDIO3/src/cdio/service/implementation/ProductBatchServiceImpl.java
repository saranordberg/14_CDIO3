package cdio.service.implementation;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdio.dal.dao.MySQLProduktBatchDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.ProduktBatchDTO;
import cdio.service.ProductBatchService;
import cdio.service.tokenhandler.TokenValidator;

public class ProductBatchServiceImpl extends RemoteServiceServlet implements ProductBatchService
{
	@Override
	public ProduktBatchDTO getProduktBatch(int pbId, String token) throws DALException
	{
		TokenValidator.validateToken(token);
		MySQLProduktBatchDAO conn = new MySQLProduktBatchDAO();
		return conn.getProduktBatch(pbId);
	}
	
	@Override
	public void createProduktBatch(ProduktBatchDTO pb, String token) throws DALException
	{
		TokenValidator.validateToken(token);
		MySQLProduktBatchDAO conn = new MySQLProduktBatchDAO();
		conn.createProduktBatch(pb);
	}
	
	@Override
	public void updateProduktBatch(ProduktBatchDTO pb, String token) throws DALException
	{
		TokenValidator.validateToken(token);
		MySQLProduktBatchDAO conn = new MySQLProduktBatchDAO();
		conn.updateProduktBatch(pb);
	}
	
	@Override
	public List<ProduktBatchDTO> listProduktBatch(String token) throws DALException
	{
		TokenValidator.validateToken(token);
		MySQLProduktBatchDAO conn = new MySQLProduktBatchDAO();
		return conn.getProduktBatchList();
	}
}
