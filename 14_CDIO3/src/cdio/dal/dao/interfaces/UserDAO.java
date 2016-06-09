package cdio.dal.dao.interfaces;

import java.util.List;

import cdio.dal.dto.UserDTO;

public interface UserDAO {
	UserDTO getUser(int userId) throws DALException;
	List<UserDTO> getUserList() throws DALException;
	void createUser(UserDTO user) throws DALException;
	void updateUser(UserDTO user) throws DALException;
}
