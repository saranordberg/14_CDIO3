package cdio.dal.dao.interfaces;

import cdio.dal.dto.TokenDTO;

public interface TokenDAO
{
	TokenDTO getToken(String token) throws DALException;
	String createToken(TokenDTO token) throws DALException;
	void deleteToken(int userId, String token) throws DALException;
	
}
