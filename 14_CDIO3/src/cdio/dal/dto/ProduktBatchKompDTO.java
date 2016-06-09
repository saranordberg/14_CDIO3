package cdio.dal.dto;

import java.io.Serializable;

public class ProduktBatchKompDTO implements Serializable 
{
	public int pbId; 	  // produktbatchets id
	public int rbId;        // i omraadet 1-99999999
	public double tara;
	public double netto;
	public int oprId;					// operatoer-nummer

	public ProduktBatchKompDTO() { }
	public ProduktBatchKompDTO(int pbId, int rbId, double tara, double netto, int oprId)
	{
		this.pbId = pbId;
		this.rbId = rbId;
		this.tara = tara;
		this.netto = netto;
		this.oprId = oprId;
	}
	
	public String toString() { 
		return pbId + "\t" + rbId +"\t" + tara +"\t" + netto + "\t" + oprId ; 
	}
}
