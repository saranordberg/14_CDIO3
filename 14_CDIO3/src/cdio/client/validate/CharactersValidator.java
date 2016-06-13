package cdio.client.validate;

public class CharactersValidator extends Validator
{
	public CharactersValidator(Object[] parameters)
	{
		super(parameters);
		// TODO Auto-generated constructor stub
	}
	
	public String validate(String value, String name)
	{
		if (value.matches("[^0-9\\!@#£¤$%&}{=+:;*§½<>,_/]+$"))
			
		{
			return null;
		}
		else
		{
			return name + " kan kun indeholde bogstaver";
		}
	}
}
