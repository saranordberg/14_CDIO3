package cdio.dal.exception;

import java.io.Serializable;

public class DALException extends Exception implements Serializable
{
	private static final long serialVersionUID = -5490114628089339322L;
	
	public DALException(String message)
	{
		super(message);
	}
	
	public DALException(Exception e)
	{
		super(e);
	}
	
	public DALException()
	{
	}
}
