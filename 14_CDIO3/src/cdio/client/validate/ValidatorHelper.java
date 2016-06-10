package cdio.client.validate;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.TextBox;

import cdio.client.helpers.Tuple;

public class ValidatorHelper
{
	
	ArrayList<Tuple<TextBox, ArrayList<IValidator>>> textBoxes = new ArrayList<Tuple<TextBox, ArrayList<IValidator>>>();
	String errorMessage = "";
	
	public void add(TextBox textBox, ArrayList<IValidator> validators) {
		textBoxes.add(new Tuple<TextBox, ArrayList<IValidator>>(textBox, validators));
	}
	
	public boolean validate() {
		for(Tuple<TextBox, ArrayList<IValidator>> textBox : textBoxes) {
			for(IValidator validator : textBox.y) {
				String error = validator.validate(textBox.x.getText());
				if(error != null)
					errorMessage += error;
			}
				
		}
		
		if(errorMessage.equals(""))
			return true;
		else {
			Window.alert(errorMessage);
			return false;
		}
	}
	
}
