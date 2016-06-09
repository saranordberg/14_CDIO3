package cdio.service;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cdio.dal.*;
import daointerfaces01917.DALException;
import dto01917.ReceptDTO;
import dto01917.UserDTO;

@RemoteServiceRelativePath("operatorService")
public interface PrescriptionService extends RemoteService
{
	ReceptDTO getRecept(int receptId, String token) throws DALException;
	
	void createRecept(ReceptDTO recept, String token) throws DALException;
	
	void updateRecept(ReceptDTO recept, String token) throws DALException;
	
	List<ReceptDTO> listRecept (String token) throws DALException;
}
