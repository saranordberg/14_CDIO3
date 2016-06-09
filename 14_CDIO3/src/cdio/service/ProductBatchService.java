package cdio.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.ProduktBatchDTO;
import cdio.dal.dto.ReceptDTO;

@RemoteServiceRelativePath("productBatchService")
public interface ProductBatchService extends RemoteService
{
	ProduktBatchDTO getProduktBatch(int pbId, String token) throws DALException;
	
	void createProduktBatch(ProduktBatchDTO pb, String token) throws DALException;
	
	void updateProduktBatch(ProduktBatchDTO pb, String token) throws DALException;
	
	List<ProduktBatchDTO> listProduktBatch (String token) throws DALException;
}
