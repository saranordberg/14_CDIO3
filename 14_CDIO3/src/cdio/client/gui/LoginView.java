package cdio.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
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

import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.UserDTO;
import cdio.service.UserService;
import cdio.service.UserServiceAsync;

public class LoginView extends Composite
{
	@UiTemplate("LoginView.ui.xml")
	interface LoginUiBinder extends UiBinder<Widget, LoginView>
	{
	}
	
	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	
	private UserServiceAsync service;
	private final String SERVICEURL = "userService";
	
	@UiField
	public TextBox userID, password;
	@UiField
	public Button loginButton;
	public iLoginCallback callback;
	
	public void getUserService()
	{
		this.service = GWT.create(UserService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
	}
	
	public LoginView(iLoginCallback callback)
	{
		initWidget(uiBinder.createAndBindUi(this));
		this.callback = callback;
		
		this.userID.setText("2");
		this.password.setText("jegharfaaetis");
		
		getUserService();
	}
	
	@UiHandler("loginButton")
	void onClick(ClickEvent e)
	{
		
		doLogin();
	}
	
	private void doLogin()
	{
			int id = Integer.parseInt(this.userID.getText());
			String password = this.password.getText();
			UserDTO user = new UserDTO();
			user.userId = id;
			user.password = password;
			
			service.login(user, new AsyncCallback<UserDTO>() {

					@Override
					public void onFailure(Throwable caught)
					{
						String message = "Brugernavn eller password er forkert";
						loginFailed(message);
					}

					@Override
					public void onSuccess(UserDTO result)
					{
						callback.login(result, "HEJ");
					
					}
						});

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
