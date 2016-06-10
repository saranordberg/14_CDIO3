package cdio.client.validate;
public class CPRValidator extends Validator {

	 public boolean validate(String value) {
	    if (value.length() == 10 && value.matches("[0-9]+")) {
	        errorMessage = "";
	        return true;
	    } else {
	        errorMessage = "Enter valid CPR, 10 characters";
	        return false;
	    }
	}

	public String getErrorMessage() {
	    return errorMessage;
	 }
	}