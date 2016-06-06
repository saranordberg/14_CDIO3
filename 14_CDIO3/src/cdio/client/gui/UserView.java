package cdio.client.gui;

import java.util.ArrayList;
import java.util.UUID;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import cdio.client.UserLevels;
import cdio.client.UserLevels.MenuLevel;
import cdio.dal.dto.UserDTO;
import cdio.service.OperatorService;
import cdio.service.OperatorServiceAsync;

public class UserView extends Composite
{
	@UiTemplate("UserView.ui.xml")
	interface UserViewUiBinder extends UiBinder<Widget, UserView> { }
	private static UserViewUiBinder uiBinder = GWT.create(UserViewUiBinder.class);
	
	private OperatorServiceAsync service;
	private final String SERVICEURL = "operatorService";
	
	@UiField VerticalPanel content;
	@UiField Button button;
	private iUserViewCallback callback;
	
	public UserView(UserDTO user, String token, iUserViewCallback callback)
	{
		getOperatorService();
		this.callback = callback;
		initWidget(uiBinder.createAndBindUi(this));
		service.listOperator(user.oprId, token, new AsyncCallback<ArrayList<UserDTO>>() {

			@Override
			public void onFailure(Throwable caught)
			{
				// TODO Auto-generated method stub
				GWT.log(caught.getMessage());
				Window.alert("Error");
			}

			@Override
			public void onSuccess(ArrayList<UserDTO> result)
			{
				content.clear();
				if(result.size() == 0) {
					Label lbl = new Label("No operators");
					content.add(lbl);
				} else {
					for(UserDTO user : result) {
						Label lbl = new Label(user.getId() + " " + user.oprNavn);
						content.add(lbl);
						Label divider = new Label();
						divider.setStyleName("divider");
						content.add(divider);
					}
				}
			}
			
		});
	}

	public void getOperatorService()
	{
		this.service = GWT.create(OperatorService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
	}
	
	public interface iUserViewCallback {
		public void onOperatorSelected(UserDTO user);
		public void onNewOperator();
	}
	
}
