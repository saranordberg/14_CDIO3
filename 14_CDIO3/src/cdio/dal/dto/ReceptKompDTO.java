package cdio.dal.dto;

import java.io.Serializable;

public class ReceptKompDTO implements Serializable
{
	public int receptId; // auto genereres fra 1..n
	public int raavareId; // i omraadet 1-99999999
	public double nomNetto; // skal vaere positiv og passende stor
	public double tolerance; // skal vaere positiv og passende stor
	
	public ReceptKompDTO()
	{
	}
	
	public ReceptKompDTO(int receptId, int raavareId, double nomNetto, double tolerance)
	{
		this.receptId = receptId;
		this.raavareId = raavareId;
		this.nomNetto = nomNetto;
		this.tolerance = tolerance;
	}
	
	public String toString()
	{
		return receptId + "\t" + raavareId + "\t" + nomNetto + "\t" + tolerance;
	}
}
