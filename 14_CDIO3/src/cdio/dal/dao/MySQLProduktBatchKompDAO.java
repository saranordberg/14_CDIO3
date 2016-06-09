package cdio.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cdio.dal.connection.Connector;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dao.interfaces.ProduktBatchKompDAO;
import cdio.dal.dto.ProduktBatchKompDTO;

public class MySQLProduktBatchKompDAO implements ProduktBatchKompDAO
{
	
	@Override
	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException
	{
		try
		{
			ResultSet rs = Connector.getInstance()
					.doQuery("Select * FROM produktbatchkomponent WHERE pb_id = ? AND rb_id = ?", pbId, rbId);
			if (!rs.first())
				throw new DALException("Produktbatchkomp " + pbId + " findes ikke");
			return new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getInt("tara"),
					rs.getInt("netto"), rs.getInt("user_id"));
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
	}
	
	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException
	{
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		try
		{
			ResultSet rs = Connector.getInstance()
					.doQuery("Select pb_id, rb_id, tara, netto FROM ProduktBatchKomponent WHERE pb_id = ?", pbId);
			while (rs.next())
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"),
						rs.getDouble("netto"), rs.getInt("user_id")));
			}
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		return list;
	}
	
	@Override
	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException
	{
		List<ProduktBatchKompDTO> list = new ArrayList<ProduktBatchKompDTO>();
		try
		{
			ResultSet rs = Connector.getInstance().doQuery("Select * FROM produktbatchkomponent");
			while (rs.next())
			{
				list.add(new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("tara"),
						rs.getDouble("netto"), rs.getInt("user_id")));
			}
		}
		catch (Exception e)
		{
			throw new DALException(e);
		}
		return list;
	}
	
	@Override
	public void createProduktBatchKomp(ProduktBatchKompDTO pbKomp) throws DALException
	{
		// Connector.doUpdate(
		// "INSERT INTO produktbatchkomp(pbId, rbId, tara, netto, opr_id) VALUES
		// " +
		// "(" + pbKomp.getPbId() +", '" + pbKomp.getRbId() + "', '" +
		// pbKomp.getTara() + "', '" +
		// pbKomp.getNetto() + "', '" + pbKomp.getOprId() + "')"
		// );
		
		try
		{
			Connector.getInstance().doUpdate(
					"INSERT INTO produktbatchkomponent (pb_id, rb_id, tara, netto, user_id) VALUES (?,?,?,?,?)",
					pbKomp.pbId, pbKomp.rbId, pbKomp.tara, pbKomp.netto, pbKomp.oprId);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
	}
	
	@Override
	public void updateProduktBatchKomp(ProduktBatchKompDTO pbKomp) throws DALException
	{
		try
		{
			Connector.getInstance().doUpdate(
					"UPDATE produktbatchkomponent set tara = ?, netto = ?, user_id = ? WHERE pb_id = ? AND rb_id = ?",
					pbKomp.tara, pbKomp.netto, pbKomp.oprId, pbKomp.pbId, pbKomp.rbId);
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
	}
}
