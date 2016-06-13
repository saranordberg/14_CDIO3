package cdio.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdio.client.helpers.CellListHelper;
import cdio.client.validate.PasswordValidator;
import cdio.client.validate.ValidatorHelper;
import cdio.dal.dto.UserDTO;
import cdio.service.UserService;
import cdio.service.UserServiceAsync;

public class PasswordView extends Composite
{
	@UiTemplate("PasswordView.ui.xml")
	interface PasswordViewUiBinder extends UiBinder<Widget, PasswordView>
	{
	}
	
	private static PasswordViewUiBinder uiBinder = GWT.create(PasswordViewUiBinder.class);
	
	private UserServiceAsync service;
	private final String SERVICEURL = "userService";
	
	@UiField
	public TextBox oldPw, newPw, newPw2;
	@UiField
	public Button actionButton;
	
	private UserDTO user;
	private String token;
	public ValidatorHelper validatorHelper = new ValidatorHelper();
	private PasswordValidator passwordValidator = new PasswordValidator();
	
	/*
	 * SelectList variables
	 */
	private CellListHelper cellList;
	
	public PasswordView(UserDTO user, String token)
	{
		getUserService();
		initWidget(uiBinder.createAndBindUi(this));
		
		this.user = user;
		this.token = token;
		
	}
	
	@UiHandler("actionButton")
	public void actionButtonClick(ClickEvent event)
	{
		if (this.user.password.equals(oldPw.getText()))
		{
			if ((passwordValidator.validate(newPw.getText())))
			{
				if (this.newPw.getText().equals(this.newPw2.getText()))
				{
					this.user.password = newPw.getText();
					service.updateUser(this.user, token, actionCallback());
					Window.alert("Dit kodeord er nu skiftet");
				}
				else
				{
					Window.alert("Dit nye kodeord matcher ikke hinanden");
				}
				
			}
			else
			{
				Window.alert("Dit nye kodeord skal mindst et stort, et lille bogstav, et tal og et af foelgende tegn: @#$%!-.");
			}
			
		}
		else
		{
			Window.alert("Din tekst matcher ikke dit kodeord");
		}
		oldPw.setText("");
		newPw.setText("");
		newPw2.setText("");
	}
	
	public void getUserService()
	{
		this.service = GWT.create(UserService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
	}
	
	public AsyncCallback<Void> actionCallback()
	{
		return new AsyncCallback<Void>()
		{
			
			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Der skete en fejl. Kontakt venligst administratoren");
				GWT.log(caught.getMessage());
			}
			
			@Override
			public void onSuccess(Void result)
			{
				
			}
		};
		
	}
}
