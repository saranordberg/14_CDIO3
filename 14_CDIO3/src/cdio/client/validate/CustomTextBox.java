package cdio.client.validate;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.TextBox;

public class CustomTextBox extends TextBox{

private static final String TEXTBOX_VALIDATION_ERROR_STYLE = "error-text-box";
private String errorMessage = "";
private List<IValidator> validators = new ArrayList<IValidator>();

public CustomTextBox() {
   }

public CustomTextBox(String name) {
    setName(name);
}

public String getErrorMessage() {
    return errorMessage;
}

public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
}

public void addValidator(IValidator validator) {
    validators.add(validator);
}

public boolean validate() {
    boolean validationResult = true;
    for (IValidator validator : validators) {
        validationResult = validator.validate(getValue().trim());
        if (!validationResult) {
            errorMessage = validator.getErrorMessage();
            break;
        }
        errorMessage = validator.getErrorMessage();
    }
    setErrorStyles(validationResult);
    return validationResult;
}

private void setErrorStyles(boolean validationResult) {
    if (validationResult) {
        removeStyleName(TEXTBOX_VALIDATION_ERROR_STYLE);
        setTitle("");
    } else {
        addStyleName(TEXTBOX_VALIDATION_ERROR_STYLE);
        setTitle(errorMessage);
    }
}

@Override
public void setValue(String s) {
    removeStyleDependentName(TEXTBOX_VALIDATION_ERROR_STYLE);
    super.setValue(s);
}

@Override
public String getValue() {
    return super.getValue().trim();
}
}