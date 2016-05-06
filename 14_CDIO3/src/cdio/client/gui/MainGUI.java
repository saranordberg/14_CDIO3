package cdio.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import cdio.dal.dto.OperatoerDTO;

public class MainGUI extends Composite
{
	private IntegerBox username = new IntegerBox();
	private PasswordTextBox password = new PasswordTextBox();
	private Button login = new Button("Login");
	private Button logout = new Button("Logout");
	private VerticalPanel vPanel = new VerticalPanel();
	private Label usernameTxt, passwordTxt, wrongLogin,logininfo;
	private Composite newView;
	private OperatoerDTO opr;
	
	public MainGUI()
	{
		opr = new OperatoerDTO(1, "John Doe", "JD", "1111111", "Password");
		
		initWidget(this.vPanel);
		
		vPanel.add(logininfo = new Label("Username 1, Password = Password"));
		
		wrongLogin = new Label();
		vPanel.add(wrongLogin);
		wrongLogin.setVisible(false);
		
		usernameTxt = new Label("Username");
		passwordTxt = new Label("Password");
		
		this.login.addClickHandler(new LoginClickHandler());
		this.logout.addClickHandler(new LogoutClickHandler());
		
		vPanel.add(usernameTxt);
		vPanel.add(username);
		
		vPanel.add(passwordTxt);
		vPanel.add(password);
		vPanel.add(login);
		
	}
	
	private class LoginClickHandler implements ClickHandler
	{
		
		@Override
		public void onClick(ClickEvent event)
		{
			
			// remember to remove getters from OperatoerDTO and use DB instead.
			if (password.getText() == opr.getPassword() && username.getValue() == opr.getId())
			{
				// Lav login funktion der tjekker om password i textbox stemmer
				// overens med opr.getPassword(oprId)
				// hvor oprId kommer fra username textbox
				
				login.removeFromParent();
				password.removeFromParent();
				username.removeFromParent();
				usernameTxt.removeFromParent();
				passwordTxt.removeFromParent();
				logininfo.removeFromParent();
				
				vPanel.add(logout);
				wrongLogin.setVisible(false);
				// LoginUser();
				LoginAdmin();
			}
			else
			{
				wrongLogin.setVisible(true);
				wrongLogin.setText("Wrong username or password");
				
			}
			
		}
		
	}
	
	private class LogoutClickHandler implements ClickHandler
	{
		
		@Override
		public void onClick(ClickEvent event)
		{
			newView.removeFromParent();
			logout.removeFromParent();
			
			username.setText("");
			password.setText("");
			
			vPanel.add(logininfo);
			vPanel.add(usernameTxt);
			vPanel.add(username);
			
			vPanel.add(passwordTxt);
			vPanel.add(password);
			vPanel.add(login);
			
		}
		
	}
	
	public void LoginAdmin()
	{
		newView = new AdminView();
		RootPanel.get().add(newView);
	}
	
	public void LoginUser()
	{
		newView = new UserView();
		RootPanel.get().add(newView);
	}
}
