package cdio.dal.dto;

import java.io.Serializable;

/**
 * User Data Access Objekt
 * 
 * @author mn/tb
 * @version 1.2
 */

public class UserDTO implements Serializable
{
	/**
	 * User-identifikationsnummer (user_id) i omraadet 1-99999999. Vaelges af
	 * brugerne
	 */
	public int userId;
	/** User navn (first_name) */
	public String firstName;
	/** User navn (last_name) */
	public String lastName;
	/** User-initialer min. 2 max. 3 karakterer */
	public String ini;
	/** USer cpr-nr 10 karakterer */
	public String cpr;
	/** User password min. 7 max. 8 karakterer */
	public String password;
	/** User level max 100 */
	public int level;
	
	public UserDTO()
	{
	}
	
	public UserDTO(int userId, String firstName, String lastName, String ini, String cpr, String password, int level)
	{
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ini = ini;
		this.cpr = cpr;
		this.password = password;
		this.level = level;
	}
	
	public UserDTO(UserDTO user)
	{
		this.userId = user.userId;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.ini = user.ini;
		this.cpr = user.cpr;
		this.password = user.password;
		this.level = user.level;
	}
	
	public String getFullName()
	{
		return firstName + " " + lastName;
	}
	
	public String toString()
	{
		return userId + "\t" + firstName + "\t" + lastName + "\t" + ini + "\t" + cpr + "\t" + password + "\t" + level;
	}
}
