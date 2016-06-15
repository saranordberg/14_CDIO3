package cdio.dal.dto;

import java.io.Serializable;

public class ProduktBatchDTO implements Serializable
{
	public int pbId; // i omraadet 1-99999999
	public String status; // 0: ikke paabegyndt, 1: under produktion, 2: afsluttet
	public int receptId;
	public String recept_navn;
	
	public ProduktBatchDTO()
	{
	}
	
	public ProduktBatchDTO(int pbId, int receptId, String status)
	{
		this.pbId = pbId;
		this.status = status;
		this.receptId = receptId;
	}
	
	public ProduktBatchDTO(int pbId, int receptId, String status, String recept_navn)
	{
		this.pbId = pbId;
		this.status = status;
		this.receptId = receptId;
		this.recept_navn = recept_navn;
	}
	
	public String toString()
	{
		return pbId + "\t" + status + "\t" + receptId;
	}
}
