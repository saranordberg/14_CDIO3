package cdio.client.validate;

public class NumberValidator extends Validator
{
	
	public NumberValidator(Object[] parameters)
	{
		super(parameters);
		// TODO Auto-generated constructor stub
	}
	
	public String validate(String value, String name)
	{
		if (value.matches("[0-9]+"))
		{
			return null;
		}
		else
		{
			return name + " kan kun indeholde tal";
		}
	}
}
