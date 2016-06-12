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
		if (value.matches("[a-zA-Z]+"))
		{
			return null;
		}
		else
		{
			return name + " can only be letters";
		}
	}
}
