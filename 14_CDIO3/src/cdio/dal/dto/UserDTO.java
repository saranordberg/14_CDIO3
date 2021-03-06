package cdio.dal.dto;

import java.io.Serializable;

/**
 * Operatoer Data Access Objekt
 * 
 * @author mn/tb
 * @version 1.2
 */

public class UserDTO implements Serializable
{
	/**
	 * Operatoer-identifikationsnummer (opr_id) i omraadet 1-99999999. Vaelges
	 * af brugerne
	 */
	public int oprId;
	/** Operatoernavn (opr_navn) min. 2 max. 20 karakterer */
	public String oprNavn;
	/** Operatoer-initialer min. 2 max. 3 karakterer */
	public String ini;
	/** Operatoer cpr-nr 10 karakterer */
	public String cpr;
	/** Operatoer password min. 7 max. 8 karakterer */
	public int userLevel;
	public String password;
	
	public UserDTO()
	{
	}
	
	public UserDTO(int oprId, String oprNavn, String ini, String cpr, String password)
	{
		this.oprId = oprId;
		this.oprNavn = oprNavn;
		this.ini = ini;
		this.cpr = cpr;
		this.password = password;
	}
	
	public UserDTO(UserDTO opr)
	{
		this.oprId = opr.oprId;
		this.oprNavn = opr.oprNavn;
		this.ini = opr.ini;
		this.cpr = opr.cpr;
		this.password = opr.password;
	}
	
	public String toString()
	{
		return oprId + "\t" + oprNavn + "\t" + ini + "\t" + cpr + "\t" + password;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public int getId()
	{
		return this.oprId;
	}
}
