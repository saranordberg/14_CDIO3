package cdio.client.validate;

public class DoubleNumberValidator extends Validator
{
	
	public DoubleNumberValidator(Object[] parameters)
	{
		super(parameters);
		// TODO Auto-generated constructor stub
	}
	
	public String validate(String value, String name)
	{
		try 
		{
			Double.parseDouble(value);
			return null;
		}
		catch (Exception e)
		{
			return name + " kan kun indeholde tal";
		}
	}
}
