package cdio.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdio.dal.connection.Connector;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dao.interfaces.ReceptDAO;
import cdio.dal.dto.ReceptDTO;
import cdio.dal.dto.ReceptKompDTO;

public class MySQLReceptDAO implements ReceptDAO
{
	
	@Override
	public ReceptDTO getRecept(int receptId) throws DALException
	{
		try
		{
			ResultSet rs = Connector.getInstance().doQuery(
					"SELECT R.recept_id, R.recept_navn, RK.raavare_id, RK.nom_netto, RK.tolerance FROM recept AS R"
							+ " LEFT OUTER JOIN receptkomponent AS RK ON R.recept_id = RK.recept_id"
							+ " WHERE R.recept_id = ?",
					receptId);
			
			ReceptDTO recept = null;
			ArrayList<ReceptKompDTO> receptKomps = new ArrayList<ReceptKompDTO>();
			
			if (!rs.first())
				throw new DALException("Recepten " + receptId + " findes ikke");
			else
			{
				recept = new ReceptDTO(receptId, rs.getString("recept_navn"));
				receptKomps.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"),
						rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
			
			while (rs.next())
			{
				receptKomps.add(new ReceptKompDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"),
						rs.getDouble("nom_netto"), rs.getDouble("tolerance")));
			}
			
			recept.receptKomps = new ReceptKompDTO[receptKomps.size()];
			recept.receptKomps = receptKomps.toArray(recept.receptKomps);
			
			return recept;
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
	public int createRecept(ReceptDTO recept) throws DALException
	{
		try
		{
			int id = Connector.getInstance().doUpdate("INSERT INTO recept (recept_id, recept_navn) VALUES (?,?)",
					recept.receptId, recept.receptNavn);
			int uniqueId;
			for(ReceptKompDTO receptkomponent : recept.receptKomps) {
				receptkomponent.receptId = id;
				
				Connector.getInstance().doUpdate(
						"INSERT INTO receptkomponent (recept_id, raavare_id, nom_netto, tolerance) VALUES (?, ?, ?, ?)",
						receptkomponent.receptId, receptkomponent.raavareId, receptkomponent.nomNetto,
						receptkomponent.tolerance);
			}
			
			return id;
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