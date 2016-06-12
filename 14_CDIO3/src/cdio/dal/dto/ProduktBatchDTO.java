package cdio.dal.dto;

import java.io.Serializable;

public class ProduktBatchDTO implements Serializable
{
	public int pbId; // i omraadet 1-99999999
	public int status; // 0: ikke paabegyndt, 1: under produktion, 2: afsluttet
	public int receptId;
	
	public ProduktBatchDTO()
	{
	}
	
	public ProduktBatchDTO(int pbId, int receptId, int status)
	{
		this.pbId = pbId;
		this.status = status;
		this.receptId = receptId;
	}
	
	public String toString()
	{
		return pbId + "\t" + status + "\t" + receptId;
	}
}
