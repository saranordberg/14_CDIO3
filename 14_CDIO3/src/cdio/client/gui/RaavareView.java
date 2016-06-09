package cdio.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import cdio.client.helpers.CellListHelper;
import cdio.dal.dto.RaavareDTO;
import cdio.dal.dto.UserDTO;
import cdio.service.RawMaterialService;
import cdio.service.RawMaterialServiceAsync;

public class RaavareView extends Composite
{
	@UiTemplate("RaavareView.ui.xml")
	interface RawMaterialsUiBinder extends UiBinder<Widget, RaavareView>
	{
	}
	
	private static RawMaterialsUiBinder uiBinder = GWT.create(RawMaterialsUiBinder.class);
	
	private RawMaterialServiceAsync service;
	private final String SERVICEURL = "rawMaterialService";
	
	@UiField
	public VerticalPanel content;
	@UiField
	public TextBox raavareId, raavareNavn, leverandoer;
	@UiField
	public Button actionButton;
	
	private UserDTO user;
	private String token;
	
	/*
	 * SelectList variables
	 */
	private CellListHelper cellList;
	private SelectionChangeEvent.Handler selectionHandler = selectionHandler();
	
	public RaavareView(UserDTO user, String token)
	{
		this.user = user;
		this.token = token;
		
		getOperatorService();
		initWidget(uiBinder.createAndBindUi(this));
		populateCellList();
	}
	
	private Handler selectionHandler()
	{
		return new SelectionChangeEvent.Handler()
		{
			public void onSelectionChange(SelectionChangeEvent event)
			{
				String selected = cellList.selected();
				int raavareIdFromSelect = Integer.parseInt(selected.split(" : ")[0].replace(" ", ""));
				
				service.getRaavare(raavareIdFromSelect, token, new AsyncCallback<UserDTO>()
				{
					
					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("Der skete en fejl. Kontakt venligst administratoren");
					}
					
					@Override
					public void onSuccess(UserDTO result)
					{
						raavareId.setText(new Integer(result.userId).toString());
						raavareNavn.setText(result.firstName);
						leverandoer.setText(result.lastName);
						actionButton.setText("Gem");
					}
					
				});
			}
		};
	}
	
	@UiHandler("actionButton")
	public void actionButtonClick(ClickEvent event)
	{
		RaavareDTO raavare = new RaavareDTO(0, raavareNavn.getText(), leverandoer.getText());
		
		// New user
		if (raavareId.getText().equals(""))
		{
			service.createRaavare(raavare, token, actionCallback());
		}
		// Update user
		else
		{
			raavare.raavareId = Integer.parseInt(raavareId.getText());
			service.updateRaavare(raavare, token, actionCallback());
		}
	}
	
	@UiHandler("newButton")
	public void newButtonClick(ClickEvent event) {
		raavareId.setText("");
		raavareNavn.setText("");
		leverandoer.setText("");
		actionButton.setText("Opret");
	}
	
	public void populateCellList()
	{
		service.listRaavare(token, new AsyncCallback<ArrayList<UserDTO>>()
		{
			
			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Der skete en fejl. Kontakt venligst administratoren");
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
	
	public AsyncCallback<Void> actionCallback() {
		return new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Der skete en fejl. Kontakt venligst administratoren");
				GWT.log(caught.getMessage());
			}

			@Override
			public void onSuccess(Void result)
			{
				populateCellList();
				
				raavareId.setText("");
				raavareNavn.setText("");
				leverandoer.setText("");
				actionButton.setText("Opret");
				
				Window.alert("Din raavare er nu gemt");
			}
		};
	}
	
	public void getOperatorService()
	{
		this.service = GWT.create(RawMaterialService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
	}
	
}
