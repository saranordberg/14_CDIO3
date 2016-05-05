package cdio.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import cdio.dal.dto.OperatoerDTO;

public class MainGUI extends Composite {
	private TextBox username = new TextBox();
	private PasswordTextBox password = new PasswordTextBox();
	private Button login = new Button("Login");
	private Button logout = new Button("Logout");
	private VerticalPanel vPanel = new VerticalPanel();
	private Label usernameTxt, passwordTxt;
	private Composite newView;
	private OperatoerDTO opr;
	
	public MainGUI(){
		initWidget(this.vPanel);
		
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
	private class LoginClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			opr = new OperatoerDTO();
			//Lav login funktion der tjekker om password i textbox stemmer overens med opr.getPassword(oprId)
			//hvor oprId kommer fra username textbox
			
			login.removeFromParent();
			password.removeFromParent();
			username.removeFromParent();
			usernameTxt.removeFromParent();
			passwordTxt.removeFromParent();
			
			vPanel.add(logout);
			LoginUser();
			
			
			
		}
		
	}
	private class LogoutClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			newView.removeFromParent();
			logout.removeFromParent();
			
			vPanel.add(usernameTxt);
			vPanel.add(username);
			
			vPanel.add(passwordTxt);
			vPanel.add(password);
			vPanel.add(login);
			
		}
		
	}
	public void LoginAdmin(){
		newView = new AdminView();
		RootPanel.get().add(newView);
	}
	public void LoginUser(){
		newView = new UserView();
		RootPanel.get().add(newView);
	}
}
