package cdio.client.gui;

import java.util.ArrayList;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import cdio.client.helpers.CellListHelper;
import cdio.dal.dto.UserDTO;
import cdio.service.OperatorService;
import cdio.service.OperatorServiceAsync;

public class UserView extends Composite
{
	@UiTemplate("UserView.ui.xml")
	interface UserViewUiBinder extends UiBinder<Widget, UserView>
	{
	}
	
	private static UserViewUiBinder uiBinder = GWT.create(UserViewUiBinder.class);
	
	private OperatorServiceAsync service;
	private final String SERVICEURL = "operatorService";
	
	@UiField
	VerticalPanel content;
	@UiField
	Button button;
	private iUserViewCallback callback;
	
	/*
	 * SelectList variables
	 */
	private CellListHelper cellList;
	private SelectionChangeEvent.Handler selectionHandler = selectionHandler();
	
	public UserView(UserDTO user, String token, iUserViewCallback callback)
	{
		getOperatorService();
		this.callback = callback;
		initWidget(uiBinder.createAndBindUi(this));
		
		service.listOperator(user.userId, token, new AsyncCallback<ArrayList<UserDTO>>()
		{
			
			@Override
			public void onFailure(Throwable caught)
			{
				GWT.log(caught.getMessage());
				Window.alert("Error");
			}
			
			@Override
			public void onSuccess(ArrayList<UserDTO> results)
			{
				ArrayList<String> values = new ArrayList<String>();
				
				for (UserDTO result : results)
					values.add(result.userId + " : " + result.getFullName());
				
				cellList = new CellListHelper(values, selectionHandler);
				
				content.clear();
				content.add(cellList.get());
			}
			
		});
	}
	
	private Handler selectionHandler()
	{
		return new SelectionChangeEvent.Handler()
		{
			public void onSelectionChange(SelectionChangeEvent event)
			{
				String selected = cellList.selected();
				if (selected != null)
				{
					Window.alert("You selected: " + selected);
				}
			}
		};
	}
	
	public void getOperatorService()
	{
		this.service = GWT.create(OperatorService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
	}
	
	public interface iUserViewCallback
	{
		public void onUserSelected(UserDTO user);
		
		public void onNewOperator();
	}
	
}
