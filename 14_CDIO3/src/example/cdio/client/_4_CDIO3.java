package example.cdio.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import example.cdio.client.gui.MainView;


public class _4_CDIO3 implements EntryPoint {

	private MainView mView = new MainView();
	
	
	public void onModuleLoad() {
		RootPanel.get().add(mView);
	
	}
}
