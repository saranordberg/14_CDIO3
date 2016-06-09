package cdio.dal.dto;

import java.io.Serializable;

/**
 * Recept Data Objekt
 * 
 * @author mn/tb
 * @version 1.2
 */

public class ReceptDTO  implements Serializable
{
	/** Recept nr i omraadet 1-99999999 */
	public int receptId;
	/** Receptnavn min. 2 max. 20 karakterer */
	public String receptNavn;
	/** liste af kompenenter i recepten */
	
    
	public ReceptDTO() { }
	public ReceptDTO(int receptId, String receptNavn)
	{
        this.receptId = receptId;
        this.receptNavn = receptNavn;
    }

	public String toString() { 
		return receptId + "\t" + receptNavn; 
	}
}
