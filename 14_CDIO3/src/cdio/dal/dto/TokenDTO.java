package cdio.dal.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class TokenDTO implements Serializable
{
	public int user_id;
	public String token;
	public Timestamp expiration;
	
	public TokenDTO()
	{
	}
	
	public TokenDTO(int user_id, String token, Timestamp expiration)
	{
		this.user_id = user_id;
		this.token = token;
		this.expiration = expiration;
	}
	
	public String toString()
	{
		return user_id + "\t" + token + "\t" + expiration;
	}
}
