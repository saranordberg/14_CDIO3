package cdio.dal.dto;

import java.io.Serializable;

public class ASEDTO implements Serializable
{
	public int pbId; // i omraadet 1-99999999
	public int status; // 0: ikke paabegyndt, 1: under produktion, 2: afsluttet
	public int netto;
	public int user_id;
	public String raavare_navn;
	public int raavare_id;
	public ASEDTO()
	{
	}
	
	public ASEDTO(int pbId, String raavare_navn, int netto, int raavare_id)
	{
		this.pbId = pbId;
		this.status = status;
		this.netto = netto;
		this.user_id = user_id;
		this.raavare_navn = raavare_navn;
		this.raavare_id = raavare_id;
	}
	
	public String toString()
	{
		return pbId + "\t" + status + "\t" ;
	}
}
