package cdio.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.RaavareBatchDTO;

@RemoteServiceRelativePath("rawMaterialBatchService")
public interface RawMaterialBatchService extends RemoteService
{
	RaavareBatchDTO getRaavareBatch(int rbID, String token) throws DALException;
	
	void createRaavareBatch(RaavareBatchDTO rb, String token) throws DALException;
	
	void updateRaavareBatch(RaavareBatchDTO rb, String token) throws DALException;
	
	List<RaavareBatchDTO> listRaavareBatch(String token) throws DALException;
}
