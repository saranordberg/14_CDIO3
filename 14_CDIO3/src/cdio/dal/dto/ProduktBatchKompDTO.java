package cdio.dal.dto;

import java.io.Serializable;

public class ProduktBatchKompDTO implements Serializable 
{
	public int pbId; 	  // produktbatchets id
	public int rbId;        // i omraadet 1-99999999
	public double tara;
	public double netto;
	public int user_id;					// user-nummer

	public ProduktBatchKompDTO() { }
	public ProduktBatchKompDTO(int pbId, int rbId, double tara, double netto, int user_id)
	{
		this.pbId = pbId;
		this.rbId = rbId;
		this.tara = tara;
		this.netto = netto;
		this.user_id = user_id;
	}
	
	public String toString() { 
		return pbId + "\t" + rbId +"\t" + tara +"\t" + netto + "\t" + user_id ; 
	}
}
