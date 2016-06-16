package cdio.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdio.dal.connection.Connector;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dao.interfaces.ProduktBatchDAO;
import cdio.dal.dto.ASEDTO;
import cdio.dal.dto.ProduktBatchDTO;

public class MySQLProduktBatchDAO implements ProduktBatchDAO
{
	
	@Override
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException
	{
		try
		{
			ResultSet rs = Connector.getInstance()
					.doQuery("Select pb_id, status, recept_id FROM produktbatch WHERE pb_id = ?", pbId);
			
			if (!rs.first())
				throw new DALException("Produktbatchen" + pbId + "findes ikke");
			
			return new ProduktBatchDTO(rs.getInt("pb_id"), rs.getInt("recept_id"), rs.getString("status"));
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
	}
	public ArrayList<ASEDTO> getASEDTOList(int pbId) throws DALException
	{
		ArrayList<ASEDTO> list = new ArrayList<ASEDTO>();
		try
		{
			ResultSet rs = Connector.getInstance()
					.doQuery("SELECT * FROM produktbatch AS PB INNER JOIN receptkomponent AS RK ON PB.recept_id = RK.recept_id INNER JOIN raavare AS RV ON RK.raavare_id = RV.raavare_id WHERE PB.pb_id = ? ", pbId);
			
			while (rs.next()){
				list.add(new ASEDTO(rs.getInt("pb_id"), rs.getNString("raavare_navn"), rs.getDouble("nom_netto"), rs.getDouble("tolerance"),  rs.getInt("raavare_id")));
			}
			return list;
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
	}
	
	@Override
	public List<ProduktBatchDTO> getProduktBatchList() throws DALException
	{
		List<ProduktBatchDTO> list = new ArrayList<ProduktBatchDTO>();
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM get_all_produktbatch");
			while (rs.next())
			{
				list.add(new ProduktBatchDTO(rs.getInt("pb_id"), rs.getInt("recept_id"), rs.getString("status")));
			}
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		return list;
	}
	
	@Override
	public void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate("INSERT INTO produktbatch(pb_id, recept_id, status) VALUES (?,?,?)",
					produktbatch.pbId, produktbatch.receptId, produktbatch.status);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
	}
	
	@Override
	public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate("UPDATE produktbatch SET status = ?,  recept_id = ? WHERE pb_id = ?",
					produktbatch.status, produktbatch.receptId, produktbatch.pbId);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
	}
	@Override
	public ASEDTO getstuff(int pbId, int receptId, int status, int netto, int user_id, int maengde)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
