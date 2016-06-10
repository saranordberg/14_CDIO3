package cdio.client.validate;

public abstract class Validator
{
	protected Object[] parameters;
	
	public Validator(Object[] parameters)
	{
		this.parameters = parameters;
	}
	
	public abstract String validate(String value, String name);
}