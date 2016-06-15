package cdio.test.database;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import cdio.dal.dao.MySQLRaavareBatchDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.RaavareBatchDTO;

public class Raavarebatch
{
	@Test
	public void GetRaavare() throws DALException
	{
		MySQLRaavareBatchDAO conn = new MySQLRaavareBatchDAO();
		
		RaavareBatchDTO op = conn.getRaavareBatch(1);
		assertNotNull(op);
	}
	
	@Test
	public void GetRaavareBatchList() throws DALException
	{
		MySQLRaavareBatchDAO conn = new MySQLRaavareBatchDAO();
		List<RaavareBatchDTO> op = conn.getRaavareBatchList();
		assertNotNull(op);
	}
	
	@Test
	public void UpdateRaavareBatch() throws DALException
	{
		RaavareBatchDTO x = new RaavareBatchDTO(23, 233, 3, "Test");
		MySQLRaavareBatchDAO conn = new MySQLRaavareBatchDAO();
		conn.updateRaavareBatch(x);
		
	}
	
	@Test
	public void CreateRaavarebatch() throws DALException
	{
		RaavareBatchDTO x = new RaavareBatchDTO(0, 1, 94, "Test2");
		MySQLRaavareBatchDAO conn = new MySQLRaavareBatchDAO();
		conn.createRaavareBatch(x);
		
	}
}