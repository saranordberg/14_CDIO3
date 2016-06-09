package cdio.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import daointerfaces01917.DALException;
import dto01917.RaavareDTO;


@RemoteServiceRelativePath("RawMaterialService")
public interface RawMaterialsService extends RemoteService
{
	 RaavareDTO getRaavare (int raavareID, String token) throws DALException;
	 
	 void createRaavare (RaavareDTO raavare, String token) throws DALException;
	 
	 void updateRaavare (RaavareDTO raavare, String token) throws DALException;
	 
	 List<RaavareDTO> listRaavare(String token) throws DALException;
}