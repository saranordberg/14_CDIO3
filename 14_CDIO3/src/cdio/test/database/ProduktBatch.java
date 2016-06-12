package cdio.test.database;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cdio.dal.connection.Connector;
import cdio.dal.connection.Constant;
import cdio.dal.dao.MySQLProduktBatchDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.ProduktBatchDTO;

public class ProduktBatch
{
	Connector db;
	
	@Test
	public void GetProduktBatch() throws DALException
	{
		MySQLProduktBatchDAO conn = new MySQLProduktBatchDAO();
		ProduktBatchDTO op = conn.getProduktBatch(1);
		assertNotNull(op);
	}
	
	@Test
	public void GetProduktBatchKList() throws DALException
	{
		MySQLProduktBatchDAO conn = new MySQLProduktBatchDAO();
		List<ProduktBatchDTO> op = conn.getProduktBatchList();
		assertNotNull(op);
	}
	
	@Test
	public void UpdateProduktBatch() throws DALException
	{
		ProduktBatchDTO x = new ProduktBatchDTO(1, 2, 1);
		MySQLProduktBatchDAO conn = new MySQLProduktBatchDAO();
		conn.updateProduktBatch(x);
	}
	
	@Test
	public void createProduktBatchKomp() throws DALException
	{
		ProduktBatchDTO x = new ProduktBatchDTO(0, 3, 3);
		MySQLProduktBatchDAO conn = new MySQLProduktBatchDAO();
		conn.createProduktBatch(x);
	}
}
