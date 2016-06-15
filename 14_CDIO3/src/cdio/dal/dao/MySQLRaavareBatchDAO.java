package cdio.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdio.dal.connection.Connector;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dao.interfaces.RaavareBatchDAO;
import cdio.dal.dto.RaavareBatchDTO;

public class MySQLRaavareBatchDAO implements RaavareBatchDAO
{
	
	@Override
	public RaavareBatchDTO getRaavareBatch(int rbId) throws DALException
	{
		try
		{
			ResultSet rs = Connector.getInstance()
					.doQuery("Select rb_id, maengde, raavare_id, raavare_navn FROM raavarebatch WHERE rb_id = ?", rbId);
			if (!rs.first())
				throw new DALException("Raavaretbatchen" + rbId + "findes ikke");
			
			return new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getDouble("maengde"), rs.getString("raavare_navn"));
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
	}
	
	@Override
	public List<RaavareBatchDTO> getRaavareBatchList() throws DALException
	{
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM raavarebatch");
			while (rs.next())
			{
				list.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getDouble("maengde"), rs.getString("raavare_navn")));
			}
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		return list;
	}
	
	@Override
	public List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException
	{
		List<RaavareBatchDTO> list = new ArrayList<RaavareBatchDTO>();
		
		try
		{
			ResultSet rs = Connector.getInstance()
					.doQuery("SELECT rb_id, maengde, raavare_id FROM raavareBatch WHERE raavare_id = ?", raavareId);
			while (rs.next())
			{
				list.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getDouble("maengde"), rs.getString("raavare_navn")));
			}
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		return list;
	}
	
	@Override
	public void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate("INSERT INTO raavarebatch(rb_id, maengde, raavare_id) VALUES (?,?,?)",
					raavarebatch.rbId, raavarebatch.maengde, raavarebatch.raavareId);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
		
	}
	
	@Override
	public void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate("UPDATE raavarebatch SET maengde = ?,  raavare_id = ? WHERE rb_id = ?",
					raavarebatch.maengde, raavarebatch.raavareId, raavarebatch.rbId);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
		
	}
	
}
