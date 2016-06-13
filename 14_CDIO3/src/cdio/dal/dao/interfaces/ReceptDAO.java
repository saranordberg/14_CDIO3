package cdio.dal.dao.interfaces;

import java.util.List;

import cdio.dal.dto.ReceptDTO;

public interface ReceptDAO
{
	ReceptDTO getRecept(int receptId) throws DALException;
	
	List<ReceptDTO> getReceptList() throws DALException;
	
	int createRecept(ReceptDTO recept) throws DALException;
	
	void updateRecept(ReceptDTO recept) throws DALException;
}
