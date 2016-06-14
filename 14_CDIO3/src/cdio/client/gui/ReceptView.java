package cdio.client.gui;

import java.util.ArrayList;

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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import cdio.client.helpers.CellListHelper;
import cdio.client.helpers.RawMaterialListBoxHelper;
import cdio.client.helpers.Tuple;
import cdio.client.validate.CharactersValidator;
import cdio.client.validate.LengthValidator;
import cdio.client.validate.Validator;
import cdio.client.validate.ValidatorHelper;
import cdio.dal.dto.ReceptDTO;
import cdio.dal.dto.ReceptKompDTO;
import cdio.dal.dto.UserDTO;
import cdio.service.PrescriptionService;
import cdio.service.PrescriptionServiceAsync;
import cdio.service.RawMaterialService;
import cdio.service.RawMaterialServiceAsync;

public class ReceptView extends Composite
{
	@UiTemplate("ReceptView.ui.xml")
	interface ReceptViewUiBinder extends UiBinder<Widget, ReceptView>
	{
	}
	
	private static ReceptViewUiBinder uiBinder = GWT.create(ReceptViewUiBinder.class);
	
	private PrescriptionServiceAsync service;
	private final String SERVICEURL = "prescriptionService";
	
	private RawMaterialServiceAsync rawMaterialService;
	private final String RAW_MATERIAL_SERVICE_URL = "rawMaterialService";
	
	@UiField
	public VerticalPanel content;
	@UiField
	public TextBox receptId, recept_navn;
	@UiField
	VerticalPanel receptKomponentPanel;
	@UiField
	public Button actionButton, addMaterialButton;
	
	
	public ArrayList<Tuple<String, String>> materials;
	
	private UserDTO user;
	private String token;
	public ValidatorHelper validatorHelper = new ValidatorHelper();
	public RawMaterialListBoxHelper rawMaterialListBoxHelper = new RawMaterialListBoxHelper();
	
	private ArrayList<ArrayList<Tuple<Widget, Label>>> receptKomponents = new ArrayList<ArrayList<Tuple<Widget, Label>>>();
	private ArrayList<Widget> dummyLabels = new ArrayList<Widget>();
	private ArrayList<Widget> dummyButtons = new ArrayList<Widget>();
	
	/*
	 * SelectList variables
	 */
	private CellListHelper cellList;
	private SelectionChangeEvent.Handler selectionHandler = selectionHandler();
	
	public ReceptView(UserDTO user, String token)
	{
		getPrescriptionService();
		getRawMaterialService();
		initWidget(uiBinder.createAndBindUi(this));
		
		this.user = user;
		this.token = token;
		
		populateCellList();
		initializeRaavare();
		
		ArrayList<Validator> textValidators = new ArrayList<Validator>();
		ArrayList<Validator> idValidators = new ArrayList<Validator>();
		
		textValidators.add(new LengthValidator(new Object[] { new Integer(50), '<' }));
		textValidators.add(new CharactersValidator(null));
		validatorHelper.add("Recept navn", recept_navn, textValidators);
	}
	
	private Handler selectionHandler()
	{
		return new SelectionChangeEvent.Handler()
		{
			public void onSelectionChange(SelectionChangeEvent event)
			{
				String selected = cellList.selected();
				int receptIdFromSelect = Integer.parseInt(selected.split(" : ")[0].replace(" ", ""));
				
				service.getRecept(receptIdFromSelect, token, new AsyncCallback<ReceptDTO>()
				{
					
					@Override
					public void onFailure(Throwable caught)
					{
						GWT.log(caught.getMessage());
						Window.alert("Der skete en fejl. Kontakt venligst administratoren");
					}
					
					@Override
					public void onSuccess(ReceptDTO result)
					{
						
						removeObjects();
						
						rawMaterialListBoxHelper.getMaterialsListBox(token);
						int i = 0;
						for (ReceptKompDTO receptKomp : result.receptKomps)
						{
							Label raavareIdLabel = new Label();
							raavareIdLabel.setText("Råvare ID:");
							receptKomponentPanel.add(raavareIdLabel);
							
							ListBox raavareId = new ListBox();
							rawMaterialListBoxHelper.populateListBoxWithMaterials(receptKomp.raavareId+"", raavareId, token);
							//raavareId.setSelectedIndex(ListBoxHelper.getIndexByValue(receptKomp.receptId+"", raavareId));
							receptKomponentPanel.add(raavareId);
							
							Label nomNettoLabel = new Label();
							nomNettoLabel.setText("Nom netto:");
							receptKomponentPanel.add(nomNettoLabel);
							
							TextBox nomNetto = new TextBox();
							nomNetto.setText(receptKomp.nomNetto + "");
							receptKomponentPanel.add(nomNetto);
							
							Label toleranceLabel = new Label();
							toleranceLabel.setText("Tolerance:");
							receptKomponentPanel.add(toleranceLabel);
							
							TextBox tolerance = new TextBox();
							tolerance.setText(receptKomp.tolerance + "");
							receptKomponentPanel.add(tolerance);
							
							Button deleteReceptKomp = new Button();
							deleteReceptKomp.setText("Fjern råvare");
							deleteReceptKomp.setStylePrimaryName(i+"");
							dummyButtons.add(deleteReceptKomp);
							deleteReceptKomp.addClickHandler(removeRaavareClickHandler());
							receptKomponentPanel.add(deleteReceptKomp);
							
							Label emptyLabel = new Label();
							emptyLabel.setText("-");
							receptKomponentPanel.add(emptyLabel);
							dummyLabels.add(emptyLabel);
							
							ArrayList<Tuple<Widget, Label>> receptKompArray = new ArrayList<Tuple<Widget, Label>>();
							receptKompArray.add(new Tuple<Widget, Label>(raavareId, raavareIdLabel));
							receptKompArray.add(new Tuple<Widget, Label>(nomNetto, nomNettoLabel));
							receptKompArray.add(new Tuple<Widget, Label>(tolerance, toleranceLabel));
							
							receptKomponents.add(receptKompArray);
							i++;
						}
						
						receptId.setText(new Integer(result.receptId).toString());
						recept_navn.setText(result.receptNavn);
						actionButton.setText("Gem");
					}

					
					
				});
			}
		};
	}
	
	@UiHandler("actionButton")
	public void actionButtonClick(ClickEvent event)
	{
		ReceptDTO recept = new ReceptDTO(0, recept_navn.getText());
		
		if (!validatorHelper.validate())
			return;
		ArrayList<ReceptKompDTO> data = createReceptKompDTOs();
		
		recept.receptKomps = new ReceptKompDTO[data.size()];
		data.toArray(recept.receptKomps);
		
		// New user
		if (receptId.getText().equals(""))
		{
			GWT.log("FUCK DIG");
			service.createRecept(recept, token, actionCallback());
		}
		// Update user
		else
		{
			recept.receptId = Integer.parseInt(receptId.getText());
			service.updateRecept(recept, token, actionCallback());
		}
	}
	
	@UiHandler("newButton")
	public void newButtonClick(ClickEvent event)
	{
		receptId.setText("");
		recept_navn.setText("");
		actionButton.setText("Opret");
		
		receptKomponentPanel.clear();
		removeObjects();
		

		
		initializeRaavare();
	}
	
	@UiHandler("addMaterialButton")
	public void addMaterialButtonClick(ClickEvent event)
	{
		initializeRaavare();
	}

	private void initializeRaavare()
	{
		rawMaterialListBoxHelper.getMaterialsListBox(token);
		
		Label raavareIdLabel = new Label();
		raavareIdLabel.setText("Råvare ID:");
		receptKomponentPanel.add(raavareIdLabel);
		
		ListBox raavareId = new ListBox();
		rawMaterialListBoxHelper.populateListBoxWithMaterials(null, raavareId, token);
		receptKomponentPanel.add(raavareId);
		
		Label nomNettoLabel = new Label();
		nomNettoLabel.setText("Nom netto:");
		receptKomponentPanel.add(nomNettoLabel);
		
		TextBox nomNetto = new TextBox();
		receptKomponentPanel.add(nomNetto);
		
		Label toleranceLabel = new Label();
		toleranceLabel.setText("Tolerance:");
		receptKomponentPanel.add(toleranceLabel);
		
		TextBox tolerance = new TextBox();
		receptKomponentPanel.add(tolerance);
		
		Button deleteReceptKomp = new Button();
		deleteReceptKomp.setText("Fjern");
		deleteReceptKomp.setStylePrimaryName("0");
		deleteReceptKomp.addClickHandler(this.removeRaavareClickHandler());
		receptKomponentPanel.add(deleteReceptKomp);
		
		Label emptyLabel = new Label();
		emptyLabel.setText("-");
		receptKomponentPanel.add(emptyLabel);
		dummyLabels.add(emptyLabel);
		dummyButtons.add(deleteReceptKomp);
		
		ArrayList<Tuple<Widget, Label>> receptKompArray = new ArrayList<Tuple<Widget, Label>>();
		receptKompArray.add(new Tuple<Widget, Label>(raavareId, raavareIdLabel));
		receptKompArray.add(new Tuple<Widget, Label>(nomNetto, nomNettoLabel));
		receptKompArray.add(new Tuple<Widget, Label>(tolerance, toleranceLabel));
		
		receptKomponents.add(receptKompArray);
	}
	
	public void populateCellList()
	{
		service.listRecept(token, new AsyncCallback<ArrayList<ReceptDTO>>()
		{
			@Override
			public void onFailure(Throwable caught)
			{
				GWT.log(caught.getMessage());
				Window.alert("Der skete en fejl. Kontakt venligst administratoren");
			}
			
			@Override
			public void onSuccess(ArrayList<ReceptDTO> results)
			{
				ArrayList<String> values = new ArrayList<String>();
				
				for (ReceptDTO result : results)
					values.add(result.receptId + " : " + result.receptNavn);
				
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
				
				receptId.setText("");
				recept_navn.setText("");
				actionButton.setText("Opret");
				
				receptKomponentPanel.clear();
				initializeRaavare();
				
				
				
				Window.alert("Din recept er nu gemt");
			}
		};
	}
	
	public void removeDummyObjects()
	{
		for (Widget label : dummyLabels)
			label.removeFromParent();
		
		for (Widget button : dummyButtons)
			button.removeFromParent();
		
		dummyLabels = new ArrayList<Widget>();
		dummyButtons = new ArrayList<Widget>();
	}
	
	public void getPrescriptionService()
	{
		this.service = GWT.create(PrescriptionService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
	}
	
	public void getRawMaterialService()
	{
		this.rawMaterialService = GWT.create(RawMaterialService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.rawMaterialService;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + RAW_MATERIAL_SERVICE_URL);
	}
	
	
	
	private ArrayList<ReceptKompDTO> createReceptKompDTOs() {
		ArrayList<ReceptKompDTO> receptKomps = new ArrayList<ReceptKompDTO>();
		
		for(ArrayList<Tuple<Widget, Label>> receptKompArray : receptKomponents) {
			ReceptKompDTO receptKomp = new ReceptKompDTO();
			
			for(Tuple<Widget, Label> receptKompTuple : receptKompArray) {
				if(receptKompTuple.y.getText().equals("Råvare ID:"))
					receptKomp.raavareId = Integer.parseInt(((ListBox)receptKompTuple.x).getSelectedValue());
				else if(receptKompTuple.y.getText().equals("Nom netto:"))
					receptKomp.nomNetto = Double.parseDouble(((TextBox)receptKompTuple.x).getValue());
				else if(receptKompTuple.y.getText().equals("Tolerance:"))
					receptKomp.tolerance = Double.parseDouble(((TextBox)receptKompTuple.x).getValue());
			}
			
			receptKomps.add(receptKomp);
			GWT.log(receptKomp.toString());
		}
		
		if(receptKomps.size() == 0) {
			Window.alert("Du skal tilfoeje mindst et receptkomponent");
			return null;
		}
		
		return receptKomps;
	}
	public ClickHandler removeRaavareClickHandler()
	{
		return new ClickHandler() {

			@Override
			public void onClick(ClickEvent event)
			{
				Button button = (Button)event.getSource();
				
				int index = Integer.parseInt(button.getStylePrimaryName());
				
				for(Tuple<Widget, Label> receptKomp : receptKomponents.get(index)) {
					receptKomp.x.removeFromParent();
					receptKomp.y.removeFromParent();
				}
				
				receptKomponents.remove(index);
			
				dummyButtons.get(index).removeFromParent();
				dummyLabels.get(index).removeFromParent();
				
				button.removeFromParent();
			}
		};
	}
	
	public void removeObjects() {
		for (ArrayList<Tuple<Widget, Label>> receptKomp : receptKomponents)
		{
			for (Tuple<Widget, Label> tuple : receptKomp)
			{
				tuple.x.removeFromParent();
				tuple.y.removeFromParent();
			}
		}
		
		removeDummyObjects();
		
		receptKomponents = new ArrayList<ArrayList<Tuple<Widget, Label>>>();
	}
	
}
