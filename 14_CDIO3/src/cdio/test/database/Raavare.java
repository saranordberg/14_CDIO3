package cdio.test.database;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import cdio.dal.dao.MySQLRaavareDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.RaavareDTO;

public class Raavare
{
	@Test
	public void GetRaavare() throws DALException
	{
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		RaavareDTO op = conn.getRaavare(1);
		
		assertNotNull(op);
	}
	
	@Test
	public void GetRaavareList() throws DALException
	{
		
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		List<RaavareDTO> op = conn.getRaavareList();
		assertNotNull(op);
	}
	
	@Test
	public void CreateRaavare() throws DALException
	{
		RaavareDTO x = new RaavareDTO(0, "Tomat", "Heinz");
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		conn.createRaavare(x);
	}
	
	@Test
	public void UpdateRaavare() throws DALException
	{
		RaavareDTO x = new RaavareDTO(1, "Tomat", "Heinz");
		MySQLRaavareDAO conn = new MySQLRaavareDAO();
		conn.updateRaavare(x);
	}
	
}
