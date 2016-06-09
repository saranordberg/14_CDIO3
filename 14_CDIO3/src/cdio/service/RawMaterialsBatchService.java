package cdio.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import daointerfaces01917.DALException;
import dto01917.RaavareBatchDTO;
import dto01917.RaavareDTO;

@RemoteServiceRelativePath("RawMaterialsBatchService")
public interface RawMaterialsBatchService extends RemoteService
{
	 RaavareBatchDTO getRaavareBatch (int rbID, String token) throws DALException;
	 
	 void createRaavareBatch (RaavareBatchDTO rb, String token) throws DALException;
	 
	 void updateRaavareBatch (RaavareBatchDTO rb, String token) throws DALException;
	 
	 List<RaavareBatchDTO> listRaavareBatch(String token) throws DALException;
}
