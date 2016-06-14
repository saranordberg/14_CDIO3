package cdio.test.database;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import cdio.dal.dao.MySQLReceptDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.ReceptDTO;

public class Recept
{
	@Test
	public void GetRecept() throws DALException
	{
		MySQLReceptDAO conn = new MySQLReceptDAO();
		
		ReceptDTO op = conn.getRecept(1);
		assertNotNull(op);
	}
	
	@Test
	public void GetReceptList() throws DALException
	{
		
		MySQLReceptDAO conn = new MySQLReceptDAO();
		List<ReceptDTO> op = conn.getReceptList();
		assertNotNull(op);
	}
	
	@Test
	public void UpdateRecept() throws DALException
	{
		ReceptDTO x = new ReceptDTO(1, "Testing");
		MySQLReceptDAO conn = new MySQLReceptDAO();
		conn.updateRecept(x);
	}
	
	@Test
	public void CreateRecept() throws DALException
	{
		ReceptDTO x = new ReceptDTO(0, "Recept for test");
		MySQLReceptDAO conn = new MySQLReceptDAO();
		conn.createRecept(x);
	}
	
}
