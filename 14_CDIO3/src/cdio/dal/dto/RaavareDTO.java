
package cdio.dal.dto;

import java.io.Serializable;

/**
 * Raavare Data Objekt
 * 
 * @author mn/sh/tb
 * @version 1.2
 */

public class RaavareDTO implements Serializable
{
	/** i omraadet 1-99999999 vaelges af brugerne */
	public int raavareId;
	/** min. 2 max. 20 karakterer */
	public String raavareNavn;
	/** min. 2 max. 20 karakterer */
	public String leverandoer;
	
	public RaavareDTO()
	{
	}
	
	public RaavareDTO(int raavareId, String raavareNavn, String leverandoer)
	{
		this.raavareId = raavareId;
		this.raavareNavn = raavareNavn;
		this.leverandoer = leverandoer;
	}
	
	public String toString()
	{
		return raavareId + "\t" + raavareNavn + "\t" + leverandoer;
	}
}
