package cdio.test.database;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import cdio.dal.dao.MySQLUserDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.UserDTO;
public class User {
	@Test
	public void GetUser() throws DALException {
		MySQLUserDAO conn = new MySQLUserDAO();

		UserDTO op = conn.getUser(1);
		assertNotNull(op);
	}

	@Test
	public void GetUserList() throws DALException {
		MySQLUserDAO conn = new MySQLUserDAO();
		List<UserDTO> op = conn.getUserList();
		assertNotNull(op);
	}
	
	@Test
	public void UpdateOperator() throws DALException {
		UserDTO x = new UserDTO(5, "updatefirst", "updatelast", "uu", "1234567891", "hallo", 37);
		MySQLUserDAO conn = new MySQLUserDAO();
		conn.updateUser(x);
	}

	@Test
	public void CreateUser() throws DALException {
		UserDTO x = new UserDTO(0, "testfirst", "testlast", "tt", "1234567890", "000000", 100);
		MySQLUserDAO conn = new MySQLUserDAO();
		conn.createUser(x);
	}

}
