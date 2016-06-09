package cdio.test.operator;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import cdio.dal.Connector;
import cdio.dal.dao.MySQLOperatoerDAO;
import cdio.dal.dto.UserDTO;
import cdio.dal.exception.DALException;

public class Operator {
	Connector db;

	@Test
	public void GetOperator() {
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();

		try {
			UserDTO op = conn.getOperatoer(1);
			assertNotNull(op);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void GetOperatorList() {

		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		try {
			List<UserDTO> op = conn.getOperatoerList();
			assertNotNull(op);

		} catch (DALException e) {

			e.printStackTrace();
		}

	}

//	@Test
//	public void CreateOprator() {
//		UserDTO x = new UserDTO(1234, "bob", "b", "2123213", "000000");
//		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
//		try {
//			conn.createOperatoer(x);
//			assertNotNull(conn.getOperatoer(1234));
//		} catch (DALException e) {
//
//			e.printStackTrace();
//		}
//
//	}

}
