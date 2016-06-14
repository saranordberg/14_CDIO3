package cdio.service.tokenhandler;

import java.sql.Timestamp;
import java.util.Date;

import cdio.dal.dao.MySQLTokenDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.TokenDTO;

public class TokenValidator
{
	public static boolean validateToken(int userID, String token) throws DALException {
		if(token == null || userID == 0)
			throw InvalidToken();
		
		try
		{
			MySQLTokenDAO conn = new MySQLTokenDAO();
			TokenDTO tokenDTO = conn.getToken(token);
			
			if(tokenDTO.user_id == userID && tokenDTO.expiration.after(new Timestamp(new Date().getTime())))
				return true;
			else
				throw InvalidToken();
		}
		catch (DALException e)
		{
			throw InvalidToken();
		}
	}
	
	public static boolean validateToken(String token) throws DALException {
		if(token == null)
			throw InvalidToken();
		
		try
		{
			MySQLTokenDAO conn = new MySQLTokenDAO();
			TokenDTO tokenDTO = conn.getToken(token);
			
			if(tokenDTO.expiration.after(new Timestamp(new Date().getTime())))
				return true;
			else
				throw InvalidToken();
		}
		catch (DALException e)
		{
			throw InvalidToken();
		}
	}
	
	private static DALException InvalidToken() {
		return new DALException("Invalid Token");
	}
}
