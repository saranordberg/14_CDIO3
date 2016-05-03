package cdio.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import cdio.client.gui.MainGUI;
import cdio.client.gui.MainView;


public class Group14_CDIO3 implements EntryPoint {

	private MainGUI mView = new MainGUI();
	
	
	public void onModuleLoad() {
		RootPanel.get().add(mView);
	
	}
}
