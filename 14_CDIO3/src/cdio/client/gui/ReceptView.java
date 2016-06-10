package cdio.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.util.Pair;
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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import cdio.client.helpers.CellListHelper;
import cdio.client.helpers.Tuple;
import cdio.dal.dto.ReceptDTO;
import cdio.dal.dto.ReceptKompDTO;
import cdio.dal.dto.UserDTO;
import cdio.service.PrescriptionService;
import cdio.service.PrescriptionServiceAsync;

public class ReceptView extends Composite
{
	@UiTemplate("ReceptView.ui.xml")
	interface ReceptViewUiBinder extends UiBinder<Widget, ReceptView>
	{
	}
	
	private static ReceptViewUiBinder uiBinder = GWT.create(ReceptViewUiBinder.class);
	
	private PrescriptionServiceAsync service;
	private final String SERVICEURL = "prescriptionService";
	
	@UiField
	public VerticalPanel content;
	@UiField
	public TextBox receptId, recept_navn, raavareId, nomNetto, tolerance;
	@UiField
	public Label raavareIdLabel, nomNettoLabel, toleranceLabel;
	@UiField VerticalPanel receptKomponentPanel;
	@UiField
	public Button actionButton;
	
	private UserDTO user;
	private String token;
	
	private ArrayList<ArrayList<Tuple<TextBox, Label>>> receptKomponents = new ArrayList<ArrayList<Tuple<TextBox, Label>>>();
	
	/*
	 * SelectList variables
	 */
	private CellListHelper cellList;
	private SelectionChangeEvent.Handler selectionHandler = selectionHandler();
	
	public ReceptView(UserDTO user, String token)
	{
		this.user = user;
		this.token = token;
		
		getReceptService();
		initWidget(uiBinder.createAndBindUi(this));
		populateCellList();
		receptKomponents.add(new ArrayList<Tuple<TextBox,Label>>());
		receptKomponents.get(0).add(new Tuple<TextBox, Label>(raavareId, raavareIdLabel));
		receptKomponents.get(0).add(new Tuple<TextBox, Label>(nomNetto, nomNettoLabel));
		receptKomponents.get(0).add(new Tuple<TextBox, Label>(tolerance, toleranceLabel));
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
						for (ArrayList<Tuple<TextBox, Label>> receptKomp : receptKomponents)
						{
							for(Tuple<TextBox, Label> tuple : receptKomp) {
								tuple.x.removeFromParent();
								tuple.y.removeFromParent();
							}
						}
						
						receptKomponents = new ArrayList<ArrayList<Tuple<TextBox, Label>>>();
						
						for (ReceptKompDTO receptKomp : result.receptKomps)
						{
							Label raavareIdLabel = new Label();
							raavareIdLabel.setText("Råvare ID:");
							receptKomponentPanel.add(raavareIdLabel);
							
							TextBox raavareId = new TextBox();
							raavareId.setText(receptKomp.raavareId+"");
							receptKomponentPanel.add(raavareId);
							
							Label nomNettoLabel = new Label();
							nomNettoLabel.setText("Nom netto:");
							receptKomponentPanel.add(nomNettoLabel);
							
							TextBox nomNetto = new TextBox();
							nomNetto.setText(receptKomp.nomNetto+"");
							receptKomponentPanel.add(nomNetto);
							
							Label toleranceLabel = new Label();
							toleranceLabel.setText("Tolerance:");
							receptKomponentPanel.add(toleranceLabel);
							
							TextBox tolerance = new TextBox();
							tolerance.setText(receptKomp.tolerance+"");
							receptKomponentPanel.add(tolerance);
							
							ArrayList<Tuple<TextBox, Label>> receptKompArray = new ArrayList<Tuple<TextBox,Label>>();
							receptKompArray.add(new Tuple<TextBox, Label>(raavareId, raavareIdLabel));
							receptKompArray.add(new Tuple<TextBox, Label>(nomNetto, nomNettoLabel));
							receptKompArray.add(new Tuple<TextBox, Label>(tolerance, toleranceLabel));
							
							receptKomponents.add(receptKompArray);
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
		
		// New user
		if (receptId.getText().equals(""))
		{
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
				
				Window.alert("Din recept er nu gemt");
			}
		};
	}
	
	public void getReceptService()
	{
		this.service = GWT.create(PrescriptionService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
	}
	
}
