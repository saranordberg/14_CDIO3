package cdio.service.implementation;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cdio.dal.dto.OperatoerDTO;
import cdio.service.OperatorService;

public class OperatorServiceImpl extends RemoteServiceServlet implements OperatorService 
{

	@Override
	public OperatoerDTO getOperatoer(int oprId)
	{
		// TODO Auto-generated method stub
		return new OperatoerDTO(oprId, "Hej1", "Hej2","Hej3","Hej4");
	}

	@Override
	public void createOperator(OperatoerDTO opr)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOperator(OperatoerDTO opr)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OperatoerDTO> listOperator()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
