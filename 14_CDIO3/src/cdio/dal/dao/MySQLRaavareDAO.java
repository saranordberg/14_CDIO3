package cdio.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdio.dal.connection.Connector;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dao.interfaces.RaavareDAO;
import cdio.dal.dto.RaavareDTO;

public class MySQLRaavareDAO implements RaavareDAO
{
	
	@Override
	public RaavareDTO getRaavare(int raavareId) throws DALException
	{
		
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM raavare WHERE raavare_id = ?", raavareId);
			if (!rs.first())
				throw new DALException("Raavaren " + raavareId + " findes ikke");
			
			return new RaavareDTO(raavareId, rs.getString("raavare_navn"), rs.getString("leverandoer"));
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
	}
	
	@Override
	public List<RaavareDTO> getRaavareList() throws DALException
	{
		List<RaavareDTO> list = new ArrayList<RaavareDTO>();
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM raavare");
			while (rs.next())
			{
				list.add(new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"),
						rs.getString("leverandoer")));
			}
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		return list;
	}
	
	@Override
	public void createRaavare(RaavareDTO raavare) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate("INSERT INTO raavare(raavare_id, raavare_navn,leverandoer) VALUES (?, ?, ?)",
					raavare.raavareId, raavare.raavareNavn, raavare.leverandoer);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void updateRaavare(RaavareDTO raavare) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate("UPDATE raavare SET raavare_navn =  ?, leverandoer = ? WHERE raavare_id = ?",
					raavare.raavareNavn, raavare.leverandoer, raavare.raavareId);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
