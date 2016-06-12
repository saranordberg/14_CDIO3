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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;

import cdio.client.helpers.CellListHelper;
import cdio.client.validate.LengthValidator;
import cdio.client.validate.NumberValidator;
import cdio.client.validate.Validator;
import cdio.client.validate.ValidatorHelper;
import cdio.dal.dto.ProduktBatchDTO;
import cdio.dal.dto.UserDTO;
import cdio.service.ProductBatchService;
import cdio.service.ProductBatchServiceAsync;

public class ProductBatchView extends Composite
{
	@UiTemplate("ProductBatchView.ui.xml")
	interface ProductBatchViewUiBinder extends UiBinder<Widget, ProductBatchView>
	{
	}
	
	private static ProductBatchViewUiBinder uiBinder = GWT.create(ProductBatchViewUiBinder.class);
	
	private ProductBatchServiceAsync service;
	private final String SERVICEURL = "productBatchService";
	
	@UiField
	public VerticalPanel content;
	@UiField
	public TextBox pbId, receptID;
	@UiField
	public ListBox status;
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
	
	public ProductBatchView(UserDTO user, String token)
	{
		getProductBatchService();
		initWidget(uiBinder.createAndBindUi(this));
		
		this.user = user;
		this.token = token;
		
		populateCellList();
		
		ArrayList<Validator> idValidators = new ArrayList<Validator>();
		
		idValidators.add(new LengthValidator(new Object[] { new Integer(30), '<' }));
		idValidators.add(new NumberValidator(null));
		validatorHelper.add("Recept ID", receptID, idValidators);
	}
	
	private Handler selectionHandler()
	{
		return new SelectionChangeEvent.Handler()
		{
			public void onSelectionChange(SelectionChangeEvent event)
			{
				String selected = cellList.selected();
				int pbIdFromSelect = Integer.parseInt(selected.split(" : ")[0].replace(" ", ""));
				
				service.getProduktBatch(pbIdFromSelect, token, new AsyncCallback<ProduktBatchDTO>()
				{
					
					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("Der skete en fejl. Kontakt venligst administratoren");
					}
					
					@Override
					public void onSuccess(ProduktBatchDTO result)
					{
						pbId.setText(new Integer(result.pbId).toString());
						status.setSelectedIndex(result.status);
						receptID.setText(new Integer(result.receptId).toString());
						actionButton.setText("Gem");
					}
					
				});
			}
		};
	}
	
	@UiHandler("actionButton")
	public void actionButtonClick(ClickEvent event)
	{
		ProduktBatchDTO pb = new ProduktBatchDTO(Integer.parseInt(pbId.getText()),
				Integer.parseInt(status.getSelectedValue()), Integer.parseInt(receptID.getText()));
		
		if (!validatorHelper.validate())
			return;
		// New productBatch
		if (pbId.getText().equals(""))
		{
			service.createProduktBatch(pb, token, actionCallback());
		}
		// Update productBatch
		else
		{
			pb.pbId = Integer.parseInt(pbId.getText());
			service.updateProduktBatch(pb, token, actionCallback());
		}
	}
	
	@UiHandler("newButton")
	public void newButtonClick(ClickEvent event)
	{
		pbId.setText("");
		status.setSelectedIndex(0);
		receptID.setText("");
		actionButton.setText("Opret");
	}
	
	public void populateCellList()
	{
		service.listProduktBatch(token, new AsyncCallback<ArrayList<ProduktBatchDTO>>()
		{
			
			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Der skete en fejl. Kontakt venligst administratoren");
			}
			
			@Override
			public void onSuccess(ArrayList<ProduktBatchDTO> results)
			{
				ArrayList<String> values = new ArrayList<String>();
				
				for (ProduktBatchDTO result : results)
					values.add(result.pbId + " : " + result.status);
				
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
				
				pbId.setText("");
				status.setSelectedIndex(1);
				receptID.setText("");
				actionButton.setText("Opret");
				
				Window.alert("Din produktbatch er nu gemt");
			}
		};
	}
	
	public void getProductBatchService()
	{
		this.service = GWT.create(ProductBatchService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
	}
}
