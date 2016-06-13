package cdio.client.validate;

import com.google.gwt.core.client.GWT;

public class LengthValidator extends Validator
{
	
	public LengthValidator(Object[] parameters)
	{
		super(parameters);
	}
	
	public String validate(String value, String name)
	{
		GWT.log(parameters[1].toString());
		GWT.log((Integer) parameters[0] + "");
		switch ((char) parameters[1])
		{
			case '>':
				if (value.length() > (Integer) parameters[0])
				{
					return null;
				}
				else
				{
					return name + " skal indeholde mere end " + (Integer) parameters[0] + " tegn";
				}
			case '<':
				if (value.length() < (Integer) parameters[0])
				{
					return null;
				}
				else
				{
					return name + " skal indeholde mindre end " + (Integer) parameters[0] + " tegn";
				}
			case '=':
				if (value.length() == (Integer) parameters[0])
				{
					return null;
				}
				else
				{
					return name + " skal indeholde præcis " + (Integer) parameters[0] + " tegn";
				}
			default:
				return null;
		}
		
	}
}