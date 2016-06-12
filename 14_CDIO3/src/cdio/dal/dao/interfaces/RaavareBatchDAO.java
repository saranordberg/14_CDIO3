package cdio.dal.dao.interfaces;

import java.util.List;

import cdio.dal.dto.RaavareBatchDTO;

public interface RaavareBatchDAO
{
	RaavareBatchDTO getRaavareBatch(int rbId) throws DALException;
	
	List<RaavareBatchDTO> getRaavareBatchList() throws DALException;
	
	List<RaavareBatchDTO> getRaavareBatchList(int raavareId) throws DALException;
	
	void createRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;
	
	void updateRaavareBatch(RaavareBatchDTO raavarebatch) throws DALException;
}
