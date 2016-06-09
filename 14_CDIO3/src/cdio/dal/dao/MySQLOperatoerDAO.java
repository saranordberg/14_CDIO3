// package cdio.dal.dao;
//
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.List;
//
// import com.google.gwt.core.client.GWT;
//
// import cdio.dal.Connector;
// import cdio.dal.dto.UserDTO;
// import cdio.dal.exception.DALException;
//
// import java.util.ArrayList;
//
////
// public class MySQLOperatoerDAO implements OperatoerDAO
// {
// public UserDTO getOperatoer(int userId) throws DALException
// {
// try
// {
// ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM user WHERE
// user_id = ?", userId);
//
// if (!rs.first())
// throw new DALException("Brugeren " + userId + " findes ikke");
//
// return new UserDTO(rs.getInt("user_id"), rs.getString("first_name"),
// rs.getString("last_name"), rs.getString("ini"),
// rs.getString("cpr"), rs.getString("password"), rs.getInt("user_level"));
// }
// catch (Exception e)
// {
// throw new DALException(e);
// }
//
// }
//
// public void createOperatoer(UserDTO user) throws DALException
// {
// try
// {
// Connector.getInstance().doUpdate("INSERT INTO user (first_name, last_name,
// ini, cpr, password, user_level) VALUES (?, ?, ?, ?, ?, ?)",
// user.firstName, user.lastName, user.ini, user.cpr, user.password,
// user.userLevel);
// }
// catch (InstantiationException | IllegalAccessException |
// ClassNotFoundException | SQLException e)
// {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
//
// public void updateOperatoer(UserDTO user) throws DALException
// {
// try
// {
// Connector.getInstance().doUpdate("UPDATE user SET first_name = ?, last_name =
// ?, ini = ?, cpr = ?, password = ?, user_level = ? WHERE user_id = ?",
// user.firstName, user.lastName, user.ini, user.cpr, user.password,
// user.userLevel, user.userId);
// }
// catch (InstantiationException | IllegalAccessException |
// ClassNotFoundException | SQLException e)
// {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
//
// /*
// * TODO: Modify to use view
// *
// * @see daointerfaces01917.OperatoerDAO#getOperatoerList()
// */
// public List<UserDTO> getOperatoerList() throws DALException
// {
// List<UserDTO> list = new ArrayList<UserDTO>();
// try
// {
// ResultSet rs = Connector.getInstance().doQuery("SELECT * FROM user");
// while (rs.next())
// {
// list.add(new UserDTO(rs.getInt("user_id"), rs.getString("first_name"),
// rs.getString("last_name"), rs.getString("ini"),
// rs.getString("cpr"), rs.getString("password"), rs.getInt("user_level")));
// }
// }
// catch (Exception e)
// {
// throw new DALException(e);
// }
// return list;
// }
//
// }
