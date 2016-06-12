package cdio.client.validate;

import java.util.ArrayList;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;

import cdio.client.helpers.Tuple;

public class ValidatorHelper
{
	
	ArrayList<Tuple<TextBox, ArrayList<Validator>>> textBoxes = new ArrayList<Tuple<TextBox, ArrayList<Validator>>>();
	String errorMessage = "";
	
	public void add(String name, TextBox textBox, ArrayList<Validator> validators)
	{
		textBoxes.add(new Tuple<TextBox, ArrayList<Validator>>(textBox, validators));
		textBox.setName(name);
	}
	
	public void add(String name, TextBox textBox, Validator validator)
	{
		ArrayList<Validator> validators = new ArrayList<Validator>();
		validators.add(validator);
		
		textBoxes.add(new Tuple<TextBox, ArrayList<Validator>>(textBox, validators));
		textBox.setName(name);
	}
	
	public boolean validate()
	{
		for (Tuple<TextBox, ArrayList<Validator>> textBox : textBoxes)
		{
			for (Validator validator : textBox.y)
			{
				GWT.log("HEJ");
				if (textBox.x == null)
				{
					GWT.log("x er null");
					continue;
				}
				GWT.log(textBox.x.getText());
				String error = validator.validate(textBox.x.getText(), textBox.x.getName());
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
