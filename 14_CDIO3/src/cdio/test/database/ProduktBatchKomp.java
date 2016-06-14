package cdio.test.database;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import cdio.dal.dao.MySQLProduktBatchKompDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.ProduktBatchKompDTO;

public class ProduktBatchKomp
{
	@Test
	public void GetProduktBatchKomp() throws DALException
	{
		MySQLProduktBatchKompDAO conn = new MySQLProduktBatchKompDAO();
		ProduktBatchKompDTO op = conn.getProduktBatchKomp(1, 1);
		assertNotNull(op);
	}
	
	@Test
	public void GetProduktBatchKompList() throws DALException
	{
		MySQLProduktBatchKompDAO conn = new MySQLProduktBatchKompDAO();
		List<ProduktBatchKompDTO> op = conn.getProduktBatchKompList();
		assertNotNull(op);
	}
	
	@Test
	public void UpdateProduktBatchKomp() throws DALException
	{
		ProduktBatchKompDTO x = new ProduktBatchKompDTO(1, 2, 3.4, 4.3, 5);
		MySQLProduktBatchKompDAO conn = new MySQLProduktBatchKompDAO();
		conn.updateProduktBatchKomp(x);
	}
	
	@Test
	public void CreateProduktBatchKomp() throws DALException
	{
		ProduktBatchKompDTO x = new ProduktBatchKompDTO(1, 3, 3.4, 4.3, 5);
		MySQLProduktBatchKompDAO conn = new MySQLProduktBatchKompDAO();
		conn.createProduktBatchKomp(x);
	}
}
