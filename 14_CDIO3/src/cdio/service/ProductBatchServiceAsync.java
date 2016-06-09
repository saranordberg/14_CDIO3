package cdio.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdio.dal.dto.ProduktBatchDTO;
import cdio.dal.dto.RaavareDTO;

public interface ProductBatchServiceAsync
{
	void getProduktBatch(int pbId, String token, AsyncCallback callback);
	
	void createProduktBatch(ProduktBatchDTO pb, String token, AsyncCallback callback);
	
	void updateProduktBatch(ProduktBatchDTO pb, String token, AsyncCallback callback);
	
	void listProduktBatch(String token, AsyncCallback callback);
}
