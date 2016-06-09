//package cdio.test.operator;
//
//import static org.junit.Assert.assertNotNull;
//
//import java.util.List;
//
//import org.junit.Test;
//
//import cdio.dal.Connector;
//import daoimpl01917.MySQLUserDAO;
//import daointerfaces01917.DALException;
//import dto01917.UserDTO;
//
//public class Operator {
//	Connector db;
//
//	@Test
//	public void GetOperator() {
//		MySQLUserDAO conn = new MySQLUserDAO();
//
//		try {
//			UserDTO op = conn.getUser(1);
//			assertNotNull(op);
//		} catch (DALException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Test
//	public void GetOperatorList() {
//
//		MySQLUserDAO conn = new MySQLUserDAO();
//		try {
//			List<UserDTO> op = conn.getUserList();
//			assertNotNull(op);
//
//		} catch (DALException e) {
//
//			e.printStackTrace();
//		}
//
//	}
//
////	@Test
////	public void CreateOprator() {
////		UserDTO x = new UserDTO(1234, "bob", "b", "2123213", "000000");
////		MySQLUserDAO conn = new MySQLUserDAO();
////		try {
////			conn.createUser(x);
////			assertNotNull(conn.getUser(1234));
////		} catch (DALException e) {
////
////			e.printStackTrace();
////		}
////
////	}
//
//}
