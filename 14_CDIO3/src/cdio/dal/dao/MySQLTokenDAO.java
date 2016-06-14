package cdio.dal.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import cdio.dal.connection.Connector;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dao.interfaces.TokenDAO;
import cdio.dal.dto.TokenDTO;
import cdio.service.tokenhandler.TokenValidator;

public class MySQLTokenDAO implements TokenDAO
{

	@Override
	public TokenDTO getToken(String token) throws DALException
	{
		try {
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM token WHERE token = ?", token);
			TokenDTO tokenDTO;
			
			if(!rs.first())
				throw new DALException("Token " + token + " findes ikke");
			else
				tokenDTO = new TokenDTO(rs.getInt("user_id"), rs.getString("token"), rs.getTimestamp("expiration"));
			
			return tokenDTO; 
		} catch(Exception e) {
			throw new DALException(e);
		}
	}
	
	@Override
	public void deleteToken(int userId, String token) throws DALException
	{
		try {
			Connector.getInstance().doUpdate("DELETE FROM token WHERE token = ? AND user_id = ?", token, userId);
		} catch(Exception e) {
			throw new DALException(e);
		}
	}

	@Override
	public String createToken(TokenDTO token) throws DALException
	{
		boolean tokenValid;
		TokenDTO tokenDTO = null;
		try {
			tokenDTO = getToken(token.user_id);
			
			tokenValid = TokenValidator.validateToken(tokenDTO.user_id, tokenDTO.token);
			if(tokenValid)
				return tokenDTO.token;
				
		} catch(DALException e)
		{
			deleteToken(tokenDTO.user_id, tokenDTO.token);
		}
		
		try
		{
			int rs = Connector.getInstance().doUpdate(//"SET @uuid = UUID();"
					"INSERT INTO token (user_id, token, expiration) VALUES(?,UUID(), ?)", token.user_id, token.expiration);
			
			return getToken(token.user_id).token;
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			throw new DALException(e);
		}
	}
	
	public TokenDTO getToken(int userID) throws DALException
	{
		try {
			ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM token WHERE user_id = ?", userID);
			TokenDTO tokenDTO;
			
			if(!rs.first())
				throw new DALException("Brugeren " + userID + " har ingen token");
			else
				tokenDTO = new TokenDTO(rs.getInt("user_id"), rs.getString("token"), rs.getTimestamp("expiration"));
			
			return tokenDTO; 
		} catch(Exception e) {
			throw new DALException(e);
		}
	}
	
}
