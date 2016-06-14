package cdio.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdio.dal.dto.ProduktBatchDTO;

public interface ProductBatchServiceAsync
{
	void getProduktBatch(int pbId, String token, AsyncCallback callback);
	
	void createProduktBatch(ProduktBatchDTO pb, String token, AsyncCallback callback);
	
	void updateProduktBatch(ProduktBatchDTO pb, String token, AsyncCallback callback);
	
	void listProduktBatch(String token, AsyncCallback callback);
}
