package cdio.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

import dto01917.RaavareBatchDTO;
import dto01917.RaavareDTO;

public interface RawMaterialsBatchServiceAsync
{
	void getRaavareBatch(int rbId, String token, AsyncCallback callback);
	
	void createRaavareBatch(RaavareBatchDTO rb, String token, AsyncCallback callback);
	
	void updateRaavareBatch(RaavareBatchDTO rb, String token, AsyncCallback callback);
	
	void listRaavareBatch(String token, AsyncCallback callback);

}
