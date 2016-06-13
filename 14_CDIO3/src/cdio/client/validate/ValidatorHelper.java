package cdio.client.validate;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ValueBoxBase;

import cdio.client.helpers.Tuple;

public class ValidatorHelper
{
	
	ArrayList<Tuple<ValueBoxBase, ArrayList<Validator>>> textBoxes = new ArrayList<Tuple<ValueBoxBase, ArrayList<Validator>>>();
	String errorMessage = "";
	
	public void add(String name, ValueBoxBase textBox, ArrayList<Validator> validators)
	{
		textBoxes.add(new Tuple<ValueBoxBase, ArrayList<Validator>>(textBox, validators));
		textBox.setName(name);
	}
	
	public void add(String name, ValueBoxBase textBox, Validator validator)
	{
		ArrayList<Validator> validators = new ArrayList<Validator>();
		validators.add(validator);
		
		textBoxes.add(new Tuple<ValueBoxBase, ArrayList<Validator>>(textBox, validators));
		textBox.setName(name);
	}
	
	public boolean validate()
	{
		for (Tuple<ValueBoxBase, ArrayList<Validator>> ValueBoxBase : textBoxes)
		{
			for (Validator validator : ValueBoxBase.y)
			{
				GWT.log("HEJ");
				if (ValueBoxBase.x == null)
				{
					GWT.log("x er null");
					continue;
				}
				GWT.log(ValueBoxBase.x.getText());
				String error = validator.validate(ValueBoxBase.x.getText(), ValueBoxBase.x.getName());
				if (error != null)
					errorMessage += error + "\n";
			}
			
		}
		
		if (errorMessage.equals(""))
			return true;
		else
		{
			Window.alert(errorMessage);
			errorMessage = "";
			return false;
		}
	}
	
}
