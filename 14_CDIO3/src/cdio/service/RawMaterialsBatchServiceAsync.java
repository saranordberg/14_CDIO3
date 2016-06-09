package cdio.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdio.dal.dto.RaavareBatchDTO;

public interface RawMaterialsBatchServiceAsync
{
	void getRaavareBatch(int rbId, String token, AsyncCallback callback);
	
	void createRaavareBatch(RaavareBatchDTO rb, String token, AsyncCallback callback);
	
	void updateRaavareBatch(RaavareBatchDTO rb, String token, AsyncCallback callback);
	
	void listRaavareBatch(String token, AsyncCallback callback);

}
