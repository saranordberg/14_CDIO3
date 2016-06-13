package cdio.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdio.dal.dto.ReceptDTO;
import cdio.dal.dto.ReceptKompDTO;

public interface PrescriptionServiceAsync
{
	void getRecept(int receptId, String token, AsyncCallback callback);
	
	void createRecept(ReceptDTO recept, String token, AsyncCallback callback);
	
	void updateRecept(ReceptDTO recept, String token, AsyncCallback callback);
	
	void listRecept(String token, AsyncCallback callback);
}
