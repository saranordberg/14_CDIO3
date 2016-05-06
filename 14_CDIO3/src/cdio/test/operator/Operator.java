package cdio.test.operator;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cdio.dal.Connector;
import cdio.dal.Constant;
import cdio.dal.dao.MySQLOperatoerDAO;
import cdio.dal.dto.OperatoerDTO;
import cdio.dal.exception.DALException;

public class Operator {
	Connector db;
	
	@Before
	public void setup() {
		try {
			db = new Connector(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void GetOperator() {
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();

		try {
			OperatoerDTO op = conn.getOperatoer(1);
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
			List<OperatoerDTO> op = conn.getOperatoerList();
			assertNotNull(op);

		} catch (DALException e) {

			e.printStackTrace();
		}

	}

	@Test
	public void CreateOprator() {
		OperatoerDTO x = new OperatoerDTO(1234, "bob", "b", "2123213", "000000");
		MySQLOperatoerDAO conn = new MySQLOperatoerDAO();
		try {
			conn.createOperatoer(x);
			assertNotNull(conn.getOperatoer(1234));
		} catch (DALException e) {

			e.printStackTrace();
		}

	}

}
