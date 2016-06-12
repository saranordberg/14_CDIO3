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
import cdio.client.validate.CharactersValidator;
import cdio.client.validate.LengthValidator;
import cdio.client.validate.Validator;
import cdio.client.validate.ValidatorHelper;
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
	public ValidatorHelper validatorHelper = new ValidatorHelper();
	/*
	 * SelectList variables
	 */
	private CellListHelper cellList;
	private SelectionChangeEvent.Handler selectionHandler = selectionHandler();
	
	public RaavareView(UserDTO user, String token)
	{
		getService();
		initWidget(uiBinder.createAndBindUi(this));
		
		this.user = user;
		this.token = token;
		
		populateCellList();
		ArrayList<Validator> textValidators = new ArrayList<Validator>();
		
		textValidators.add(new LengthValidator(new Object[] { new Integer(30), '<' }));
		validatorHelper.add("Råvare navn", raavareNavn, textValidators);
		validatorHelper.add("Leverandør", leverandoer, textValidators);
	}
	
	private Handler selectionHandler()
	{
		return new SelectionChangeEvent.Handler()
		{
			public void onSelectionChange(SelectionChangeEvent event)
			{
				String selected = cellList.selected();
				int raavareIdFromSelect = Integer.parseInt(selected.split(" : ")[0].replace(" ", ""));
				
				service.getRaavare(raavareIdFromSelect, token, new AsyncCallback<RaavareDTO>()
				{
					
					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("Der skete en fejl. Kontakt venligst administratoren");
					}
					
					@Override
					public void onSuccess(RaavareDTO result)
					{
						raavareId.setText(new Integer(result.raavareId).toString());
						raavareNavn.setText(result.raavareNavn);
						leverandoer.setText(result.leverandoer);
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
		
		if (!validatorHelper.validate())
			return;
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
	public void newButtonClick(ClickEvent event)
	{
		raavareId.setText("");
		raavareNavn.setText("");
		leverandoer.setText("");
		actionButton.setText("Opret");
	}
	
	public void populateCellList()
	{
		service.listRaavare(token, new AsyncCallback<ArrayList<RaavareDTO>>()
		{
			
			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Der skete en fejl. Kontakt venligst administratoren");
			}
			
			@Override
			public void onSuccess(ArrayList<RaavareDTO> results)
			{
				ArrayList<String> values = new ArrayList<String>();
				
				for (RaavareDTO result : results)
					values.add(result.raavareId + " : " + result.raavareNavn);
				
				cellList = new CellListHelper(values, selectionHandler);
				
				content.clear();
				content.add(cellList.get());
			}
			
		});
	}
	
	public AsyncCallback<Void> actionCallback()
	{
		return new AsyncCallback<Void>()
		{
			
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
	
	public void getService()
	{
		this.service = GWT.create(RawMaterialService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
	}
	
}
