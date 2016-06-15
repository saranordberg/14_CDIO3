package cdio.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdio.dal.connection.Connector;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dao.interfaces.ReceptKompDAO;
import cdio.dal.dto.ReceptKompDTO;

public class MySQLReceptKompDAO implements ReceptKompDAO
{
	
	@Override
	public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException
	{
		try
		{
			ResultSet rs = Connector.getInstance().doQuery(
					"SELECT recept_id, raavare_id, nom_netto, tolerance fROM receptkomponent WHERE recept_id = ? AND raavare_id = ?",
					receptId, raavareId);
			if (!rs.first())
				throw new DALException("Receptkomponenten" + receptId + ", " + raavareId + " findes ikke");
			
			return new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"),
					rs.getDouble("tolerance"));
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
	}
	
	@Override
	public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException
	{
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		
		try
		{
			ResultSet rs = Connector.getInstance().doQuery(
					"SELECT recept_id, raavare_id, nom_netto, tolerance FROM grp14.receptkomponent WHERE recept_id = ?",
					receptId);
			while (rs.next())
				list.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nomNetto"),
						rs.getDouble("tolerance")));
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		
		return list;
	}
	
	@Override
	public List<ReceptKompDTO> getReceptKompList() throws DALException
	{
		List<ReceptKompDTO> list = new ArrayList<ReceptKompDTO>();
		
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM get_all_receptkomponent");
			while (rs.next())
				list.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"), rs.getDouble("nom_netto"),
						rs.getDouble("tolerance")));
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		
		return list;
	}
	
	@Override
	public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate(
					"INSERT INTO receptkomponent (recept_id, raavare_id, nom_netto, tolerance) VALUES (?, ?, ?, ?)",
					receptkomponent.receptId, receptkomponent.raavareId, receptkomponent.nomNetto,
					receptkomponent.tolerance);
			
			return;
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
		
	}
	
	@Override
	public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate(
					"UPDATE receptkomponent SET raavare_id = ?, recept_id = ?, nom_netto = ?, tolerance = ? WHERE recept_id = ? AND raavare_id = ?",
					receptkomponent.raavareId, receptkomponent.receptId, receptkomponent.nomNetto,
					receptkomponent.tolerance, receptkomponent.receptId, receptkomponent.raavareId);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
		
	}
}
