package cdio.client;


import java.util.UUID;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.RootPanel;

import cdio.dal.dto.UserDTO;
import cdio.client.gui.LoginView;
import cdio.client.gui.MenuView;

public class Group14_CDIOFinal implements EntryPoint, LoginView.iLoginCallback
{
	
//	private MainGUI mView = new MainGUI();
	
	public void onModuleLoad()
	{
//		RootPanel.get().add(mView);
		loginView();
	}
	
	public void loginView() {
		this.clear();
		
		LoginView login = new LoginView(this);
		RootPanel.get().add(login);
	}

	@Override
	public void login(UserDTO user, String token)
	{
		this.clear();
		RootPanel.get().add(new MenuView(user, token));
	}
	
	
	
	private void clear() {
		RootPanel.get().clear();
	}
}
