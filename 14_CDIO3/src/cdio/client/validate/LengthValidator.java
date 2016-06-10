package cdio.client.validate;

public class LengthValidator implements IValidator
{
	
	public String validate(String value, Object... parameters )
	{
		if (value.length() == (int)parameters[0])
		{
			return null;
		}
		else
		{
			return "Enter valid CPR, 10 characters";
		}
	}
}