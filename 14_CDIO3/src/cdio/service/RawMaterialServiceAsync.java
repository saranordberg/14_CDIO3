package cdio.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cdio.dal.dto.RaavareDTO;

public interface RawMaterialServiceAsync
{
	void getRaavare(int raavareId, String token, AsyncCallback callback);
	
	void createRaavare(RaavareDTO raavare, String token, AsyncCallback callback);
	
	void updateRaavare(RaavareDTO raavare, String token, AsyncCallback callback);
	
	void listRaavare(String token, AsyncCallback callback);
}
