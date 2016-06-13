package cdio.client.validate;

public class LeverandoerValidator extends Validator
{
	public LeverandoerValidator(Object[] parameters)
	{
		super(parameters);
		// TODO Auto-generated constructor stub
	}
	
	public String validate(String value, String name)
	{
		if (value.matches("[^0-9\\!@#$%}{=:;*§<>,_]+$"))
			
		{
			return null;
		}
		else
		{
			return name + " kan kun indeholde bogstaver og følgende specialtegn (-+/&)";
		}
	}
}
