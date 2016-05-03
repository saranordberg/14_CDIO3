package example.cdio.client.Operator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


//
public class MySQLOperatoerDAO implements OperatoerDAO {
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer WHERE opr_id = ?", oprId);
	    try {
	    	if (!rs.first())
	    		throw new DALException("Operatoeren " + oprId + " findes ikke");
	    	
	    	return new OperatoerDTO (rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"));
	    }
	    catch (SQLException e)
	    {
	    	throw new DALException(e);
	    }
		
	}
	
	public void createOperatoer(OperatoerDTO opr) throws DALException {		
		Connector.doQuery
		(
			"INSERT INTO operatoer(opr_id, opr_navn, ini, cpr, password) VALUES (?, ?, ?, ?, ?)",
			opr.oprId, opr.oprNavn, opr.ini, opr.cpr, opr.password
		);
	}
	
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		Connector.doQuery(
			"UPDATE operatoer SET  opr_navn = ?, ini =  ?, cpr = ?, password = ? WHERE opr_id = ?",
			opr.oprNavn, opr.ini, opr.cpr, opr.password, opr.oprId
		);
	}
	
	/*
	 * TODO: Modify to use view
	 * @see daointerfaces01917.OperatoerDAO#getOperatoerList()
	 */
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> list = new ArrayList<OperatoerDTO>();
		ResultSet rs = Connector.doQuery("SELECT * FROM operatoer");
		try
		{
			while (rs.next()) 
			{
				list.add(new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password")));
			}
		}
		catch (SQLException e) { throw new DALException(e); }
		return list;
	}
		
		
}
	
