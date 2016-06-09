package cdio.service.implementation;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdio.dal.dao.MySQLRaavareBatchDAO;
import cdio.dal.dao.MySQLRaavareDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.RaavareBatchDTO;
import cdio.dal.dto.RaavareDTO;
import cdio.service.RawMaterialsBatchService;
import cdio.service.RawMaterialsService;

public class RawMaterialBatchServiceImpl extends RemoteServiceServlet implements RawMaterialService
{
	@Override
	public RaavareBatchDTO getRaavareBatch(int rbId, String token) throws DALException
	{
		MySQLRaavareBatchDAO conn = new MySQLRaavareBatchDAO();
		return conn.getRaavareBatch(rbId);
	}
	
	@Override
	public void createRaavareBatch(RaavareBatchDTO rb, String token) throws DALException
	{
		MySQLRaavareBatchDAO conn = new MySQLRaavareBatchDAO();
		conn.createRaavareBatch(rb);
	}
	
	@Override
	public void updateRaavareBatch(RaavarebatchDTO rb, String token) throws DALException
	{
		MySQLRaavareBatchDAO conn = new MySQLRaavareBatchDAO();
		conn.updateRaavareBatch(rb);
	}
	
	@Override
	public List<RaavareBatchDTO> listRaavareBatch(String token) throws DALException
	{
		MySQLRaavareBatchDAO conn = new MySQLRaavareBatchDAO();
		return conn.getRaavareBatchList();
	}
}
