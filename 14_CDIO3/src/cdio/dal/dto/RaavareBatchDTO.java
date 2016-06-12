package cdio.dal.dto;

import java.io.Serializable;

public class RaavareBatchDTO implements Serializable
{
	public int rbId; // i omraadet 1-99999999
	public int raavareId; // i omraadet 1-99999999
	public double maengde; // kan vaere negativ
	
	public RaavareBatchDTO()
	{
	}
	
	public RaavareBatchDTO(int rbId, int raavareId, double maengde)
	{
		this.rbId = rbId;
		this.raavareId = raavareId;
		this.maengde = maengde;
	}
	
	public String toString()
	{
		return rbId + "\t" + raavareId + "\t" + maengde;
	}
}
