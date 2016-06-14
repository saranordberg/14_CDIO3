package cdio.dal.dao.interfaces;

import java.util.List;

import cdio.dal.dto.ASEDTO;
import cdio.dal.dto.ProduktBatchDTO;

public interface ProduktBatchDAO
{
	ProduktBatchDTO getProduktBatch(int pbId) throws DALException;
	
	List<ProduktBatchDTO> getProduktBatchList() throws DALException;
	
	void createProduktBatch(ProduktBatchDTO produktbatch) throws DALException;
	
	void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException;

	ASEDTO getstuff(int pbId, int receptId, int status,int netto, int user_id, int maengde)throws DALException;
}