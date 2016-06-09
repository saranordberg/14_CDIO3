package cdio.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdio.dal.dto.ReceptDTO;

public interface PrescriptionServiceAsync
{
	void getRecept(int receptId, String token, AsyncCallback callback);
	
	void createRecept(ReceptDTO recept, String token, AsyncCallback callback);
	
	void updateRecept(ReceptDTO recept, String token, AsyncCallback callback);
	
	void listRecept(String token, AsyncCallback callback);
}
