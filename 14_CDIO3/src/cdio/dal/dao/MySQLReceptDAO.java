package cdio.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdio.dal.connection.Connector;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dao.interfaces.ReceptDAO;
import cdio.dal.dto.ReceptDTO;

public class MySQLReceptDAO implements ReceptDAO
{
	
	@Override
	public ReceptDTO getRecept(int receptId) throws DALException
	{
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT recept_navn FROM recept WHERE recept_id = ?",
					receptId);
			if (!rs.first())
				throw new DALException("Recepten " + receptId + " findes ikke");
			
			return new ReceptDTO(receptId, rs.getString("recept_navn"));
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		
	}
	
	@Override
	public List<ReceptDTO> getReceptList() throws DALException
	{
		List<ReceptDTO> list = new ArrayList<ReceptDTO>();
		
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM grp14.recept");
			while (rs.next())
			{
				list.add(new ReceptDTO(rs.getInt("recept_id"), rs.getString("recept_navn")));
			}
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		return list;
	}
	
	@Override
	public void createRecept(ReceptDTO recept) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate("INSERT INTO recept (recept_id, recept_navn) VALUES (?,?)",
					recept.receptId, recept.receptNavn);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
	}
	
	@Override
	public void updateRecept(ReceptDTO recept) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate("UPDATE recept SET  recept_navn = '" + recept.receptNavn
					+ "' WHERE recept_id = " + recept.receptId);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
	}
	
}