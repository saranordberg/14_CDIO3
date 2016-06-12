package cdio.test.database;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cdio.dal.connection.Connector;
import cdio.dal.connection.Constant;
import cdio.dal.dao.MySQLReceptKompDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.ReceptKompDTO;

public class ReceptKomp
{
	@Test
	public void GetReceptKomp() throws DALException
	{
		MySQLReceptKompDAO conn = new MySQLReceptKompDAO();
		ReceptKompDTO op = conn.getReceptKomp(1, 1);
		
		assertNotNull(op);
	}
	
	@Test
	public void GetReceptKompList() throws DALException
	{
		
		MySQLReceptKompDAO conn = new MySQLReceptKompDAO();
		List<ReceptKompDTO> op = conn.getReceptKompList();
		assertNotNull(op);
	}
	
	@Test
	public void CreateReceptKomp() throws DALException
	{
		ReceptKompDTO x = new ReceptKompDTO(1, 3, 1, 1);
		MySQLReceptKompDAO conn = new MySQLReceptKompDAO();
		conn.createReceptKomp(x);
	}
	
	@Test
	public void UpdateReceptKomp() throws DALException
	{
		ReceptKompDTO x = new ReceptKompDTO(1, 1, 1, 1);
		MySQLReceptKompDAO conn = new MySQLReceptKompDAO();
		conn.updateReceptKomp(x);
	}
}
