//package cdio.dal.dto;
//
//import java.io.Serializable;
//
///**
// * Operatoer Data Access Objekt
// * 
// * @author mn/tb
// * @version 1.2
// */
//
//public class UserDTO implements Serializable
//{
//	public int userId;
//	public String firstName;
//	public String lastName;
//	public String ini;
//	public String cpr;
//	public String password;
//	public int userLevel;
//	
//	public UserDTO()
//	{
//	}
//	
//	public UserDTO(int userId, String firstName, String lastName, String ini, String cpr, String password, int userLevel)
//	{
//		this.userId = userId;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.ini = ini;
//		this.cpr = cpr;
//		this.password = password;
//		this.userLevel = userLevel;
//	}
//	
//	public UserDTO(UserDTO user)
//	{
//		this.userId = user.userId;
//		this.firstName = user.firstName;
//		this.lastName = user.lastName;
//		this.ini = user.ini;
//		this.cpr = user.cpr;
//		this.password = user.password;
//		this.userLevel = user.userLevel;
//	}
//	
//	public String toString()
//	{
//		return userId + "\t" + firstName + "\t" + ini + "\t" + cpr + "\t" + password;
//	}
//	
//	public String getFullName()
//	{
//		return firstName + " " + lastName;
//	}
//}
