package cdio.client.gui;

import java.util.UUID;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import cdio.dal.dto.UserDTO;
import cdio.service.OperatorService;
import cdio.service.OperatorServiceAsync;

public class LoginView extends Composite
{
	@UiTemplate("LoginView.ui.xml")
	interface LoginUiBinder extends UiBinder<Widget, LoginView>
	{
	}
	
	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	
	private OperatorServiceAsync service;
	private final String SERVICEURL = "operatorService";
	
	@UiField
	public TextBox userID, password;
	@UiField
	public Button loginButton;
	private iLoginCallback callback;
	
	public void getOperatorService()
	{
		this.service = GWT.create(OperatorService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
	}
	
	public LoginView(iLoginCallback callback)
	{
		initWidget(uiBinder.createAndBindUi(this));
		this.callback = callback;
		
		this.userID.setText("1");
		this.password.setText("Test");
		
		getOperatorService();
	}
	
	@UiHandler("loginButton")
	void onClick(ClickEvent e)
	{
		doLogin();
	}
	
	private void doLogin()
	{
		try
		{
			int id = Integer.parseInt(this.userID.getText());
			String password = this.password.getText();
			UserDTO user = new UserDTO();
			
			user.oprId = 1;
			user.userLevel = 100;
			//TODO: FIX LOGIN
			callback.login(user, Document.get().createUniqueId());
			
			
		}
		catch (NumberFormatException e)
		{
			loginFailed("UserID must be a number");
		}
	}
	
	private void loginFailed(String message)
	{
		Window.alert(message);
	}
	
	public interface iLoginCallback
	{
		public void login(UserDTO user, String token);
	}
}
