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
					return name + " must be  than " + (Integer) parameters[0] + " characters";
				}
			case '<':
				if (value.length() < (Integer) parameters[0])
				{
					return null;
				}
				else
				{
					return name + " must be less than " + (Integer) parameters[0] + " characters";
				}
			case '=':
				if (value.length() == (Integer) parameters[0])
				{
					return null;
				}
				else
				{
					return name + " must be " + (Integer) parameters[0] + " characters";
				}
			default:
				return null;
		}
		
	}
}