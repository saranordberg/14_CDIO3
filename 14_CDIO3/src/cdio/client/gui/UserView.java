package cdio.client.gui;

import java.util.ArrayList;
import java.util.UUID;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

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
		
		service.listOperator(user.userId, token, new AsyncCallback<ArrayList<UserDTO>>() {

			@Override
			public void onFailure(Throwable caught)
			{
				GWT.log(caught.getMessage());
				Window.alert("Error");
			}

			@Override
			public void onSuccess(ArrayList<UserDTO> results)
			{
				populateCellList(results);
			}
			
		});
	}
	
	private void populateCellList(ArrayList<UserDTO> data) {
		content.clear();
		
		ArrayList<String> values = new ArrayList<String>();
	    
	    for(UserDTO result : data)
	    {
	    	values.add(result.userId + " : " + result.getFullName());
	    }
		
		TextCell textCell = new TextCell();

	    // Create a CellList that uses the cell.
	    CellList<String> cellList = new CellList<String>(textCell);
	    cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

	    // Add a selection model to handle user selection.
	    final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
	    cellList.setSelectionModel(selectionModel);
	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
	      public void onSelectionChange(SelectionChangeEvent event) {
	        String selected = selectionModel.getSelectedObject();
	        if (selected != null) {
	          Window.alert("You selected: " + selected);
	        }
	      }
	    });

	    // Set the total row count. This isn't strictly necessary, but it affects
	    // paging calculations, so its good habit to keep the row count up to date.
	    cellList.setRowCount(values.size(), true);

	    // Push the data into the widget.
	    cellList.setRowData(0, values);
	    
	    content.add(cellList);
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
