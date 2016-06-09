package cdio.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

import dto01917.RaavareDTO;

public interface RawMaterialsServiceAsync
{
	void getRaavare(int raavareId, String token, AsyncCallback callback);
	
	void createRaavare(RaavareDTO raavare, String token, AsyncCallback callback);
	
	void updateRaavare(RaavareDTO raavare, String token, AsyncCallback callback);
	
	void listRaavare(String token, AsyncCallback callback);
}
