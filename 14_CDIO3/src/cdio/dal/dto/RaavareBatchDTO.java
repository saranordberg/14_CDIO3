package cdio.dal.dto;

import java.io.Serializable;

public class RaavareBatchDTO implements Serializable
{
	public int rbId; // i omraadet 1-99999999
	public int raavareId; // i omraadet 1-99999999
	public double maengde; // kan vaere negativ
	public String raavareNavn;
	
	public RaavareBatchDTO()
	{
	}
	
	public RaavareBatchDTO(int rbId, int raavareId, double maengde, String raavareNavn)
	{
		this.rbId = rbId;
		this.raavareId = raavareId;
		this.maengde = maengde;
		this.raavareNavn = raavareNavn;
	}
	
	public String toString()
	{
		return rbId + "\t" + raavareId + "\t" + maengde + "\t" + raavareNavn;
	}
}
