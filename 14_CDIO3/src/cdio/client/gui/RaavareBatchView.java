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
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import cdio.client.helpers.CellListHelper;
import cdio.client.helpers.ListBoxPopulater;
import cdio.client.validate.DoubleNumberValidator;
import cdio.client.validate.LengthValidator;
import cdio.client.validate.Validator;
import cdio.client.validate.ValidatorHelper;
import cdio.dal.dto.RaavareBatchDTO;
import cdio.dal.dto.RaavareDTO;
import cdio.dal.dto.UserDTO;
import cdio.service.RawMaterialBatchService;
import cdio.service.RawMaterialBatchServiceAsync;

public class RaavareBatchView extends Composite
{
	@UiTemplate("RaavareBatchView.ui.xml")
	interface RaavareBatchViewUiBinder extends UiBinder<Widget, RaavareBatchView>
	{
	}
	
	private static RaavareBatchViewUiBinder uiBinder = GWT.create(RaavareBatchViewUiBinder.class);
	
	private RawMaterialBatchServiceAsync service;
	private final String SERVICEURL = "rawMaterialBatchService";
	
	@UiField
	public VerticalPanel content;
	@UiField
	public TextBox rb_Id;
	@UiField
	public ListBox raavare_Id;
	@UiField
	public DoubleBox maengde;
	@UiField
	public Button actionButton;
	
	private UserDTO user;
	private String token;
	public ValidatorHelper validatorHelper = new ValidatorHelper();
	public ListBoxPopulater listBoxPopulater = new ListBoxPopulater();
	
	/*
	 * SelectList variables
	 */
	private CellListHelper cellList;
	private SelectionChangeEvent.Handler selectionHandler = selectionHandler();
	
	public RaavareBatchView(UserDTO user, String token)
	{
		getService();
		initWidget(uiBinder.createAndBindUi(this));
		
		this.user = user;
		this.token = token;
		
		populateCellList();
		listBoxPopulater.populateWithRawMaterials(token);
		ArrayList<Validator> maengdeValidators = new ArrayList<Validator>();
		
		maengdeValidators.add(new LengthValidator(new Object[] { new Integer(21), '<' }));
		maengdeValidators.add(new DoubleNumberValidator(null));
		validatorHelper.add("Mængde", maengde, maengdeValidators);
	}
	
	private Handler selectionHandler()
	{
		return new SelectionChangeEvent.Handler()
		{
			public void onSelectionChange(SelectionChangeEvent event)
			{
				String selected = cellList.selected();
				int raavareBatchIdFromSelect = Integer.parseInt(selected.split(" : ")[0].replace(" ", ""));
				
				service.getRaavareBatch(raavareBatchIdFromSelect, token, new AsyncCallback<RaavareBatchDTO>()
				{
					
					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("Der skete en fejl. Kontakt venligst administratoren");
					}
					
					@Override
					public void onSuccess(RaavareBatchDTO result)
					{
						rb_Id.setText(new Integer(result.rbId).toString());
						// raavare_Id.setText(new
						// Integer(result.raavareId).toString());
						
						// raavare_Id = new ListBox();
						listBoxPopulater.populateListBoxWithMaterials(result.raavareNavn + "", raavare_Id, token);
						// raavareId.setSelectedIndex(ListBoxHelper.getIndexByValue(receptKomp.receptId+"",
						// raavareId));
						
						maengde.setText(new Double(result.maengde).toString());
						actionButton.setText("Gem");
					}
					
				});
			}
		};
	}
	
	@UiHandler("actionButton")
	public void actionButtonClick(ClickEvent event)
	{
		RaavareBatchDTO raavareBatch = new RaavareBatchDTO(0, Integer.parseInt(raavare_Id.getSelectedValue()),
				Double.parseDouble(maengde.getText()), "");
		
		if (!validatorHelper.validate())
			return;
		
		// New user
		if (rb_Id.getText().equals(""))
		{
			service.createRaavareBatch(raavareBatch, token, actionCallback());
		}
		// Update user
		else
		{
			raavareBatch.rbId = Integer.parseInt(rb_Id.getText());
			service.updateRaavareBatch(raavareBatch, token, actionCallback());
		}
	}
	
	@UiHandler("newButton")
	public void newButtonClick(ClickEvent event)
	{
		rb_Id.setText("");
		listBoxPopulater.populateListBoxWithMaterials(null, raavare_Id, token);
		maengde.setText("");
		actionButton.setText("Opret");
	}
	
	public void populateCellList()
	{
		service.listRaavareBatch(token, new AsyncCallback<ArrayList<RaavareBatchDTO>>()
		{
			
			@Override
			public void onFailure(Throwable caught)
			{
				
				Window.alert("Der skete en fejl. Kontakt venligst administratoren");
			}
			
			@Override
			public void onSuccess(ArrayList<RaavareBatchDTO> results)
			{
				ArrayList<String> values = new ArrayList<String>();
				
				for (RaavareBatchDTO result : results)
					values.add(result.rbId + " : " + result.raavareId);
				
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
				
				rb_Id.setText("");
				raavare_Id.setSelectedIndex(0);
				maengde.setText("");
				actionButton.setText("Opret");
				
				Window.alert("Din raavarebatch er nu gemt");
			}
		};
	}
	
	public void getService()
	{
		this.service = GWT.create(RawMaterialBatchService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
	}
	
}
