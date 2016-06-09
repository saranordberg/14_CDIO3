//package cdio.client.gui;
//
//import java.util.ArrayList;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.uibinder.client.UiBinder;
//import com.google.gwt.uibinder.client.UiField;
//import com.google.gwt.uibinder.client.UiHandler;
//import com.google.gwt.uibinder.client.UiTemplate;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.rpc.ServiceDefTarget;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.Composite;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.user.client.ui.Widget;
//import com.google.gwt.view.client.SelectionChangeEvent;
//import com.google.gwt.view.client.SelectionChangeEvent.Handler;
//
//import cdio.client.helpers.CellListHelper;
//import cdio.dal.dto.UserDTO;
//import cdio.service.RawMaterialBatchServiceAsync;
//
//public class RaavareBatchesView extends Composite
//{
//	@UiTemplate("RaavareBatchesView.ui.xml")
//	interface RaavareBatchesViewUiBinder extends UiBinder<Widget, RaavareBatchesView>
//	{
//	}
//	
//	private static RaavareBatchesViewUiBinder uiBinder = GWT.create(RaavareBatchesViewUiBinder.class);
//	
//	private RawMaterialBatchServiceAsync service;
//	private final String SERVICEURL = "rawMaterialBatchService";
//	
//	@UiField
//	public VerticalPanel content;
//	@UiField
//	public TextBox rb_Id, raavare_Id, maenge;
//	@UiField
//	public Button actionButton;
//	
//	private UserDTO user;
//	private String token;
//	
//	/*
//	 * SelectList variables
//	 */
//	private CellListHelper cellList;
//	private SelectionChangeEvent.Handler selectionHandler = selectionHandler();
//	
//	public RaavareBatchesView(UserDTO user, String token)
//	{
//		this.user = user;
//		this.token = token;
//		
//		getOperatorService();
//		initWidget(uiBinder.createAndBindUi(this));
//		populateCellList();
//	}
//	
//	private Handler selectionHandler()
//	{
//		return new SelectionChangeEvent.Handler()
//		{
//			public void onSelectionChange(SelectionChangeEvent event)
//			{
//				String selected = cellList.selected();
//				int userIdFromSelect = Integer.parseInt(selected.split(" : ")[0].replace(" ", ""));
//				
//				service.getOperatoer(userIdFromSelect, new AsyncCallback<UserDTO>()
//				{
//					
//					@Override
//					public void onFailure(Throwable caught)
//					{
//						Window.alert("Der skete en fejl. Kontakt venligst administratoren");
//					}
//					
//					@Override
//					public void onSuccess(UserDTO result)
//					{
//						rb_Id.setText(new Integer(result.userId).toString());
//						raavare_Id.setText(result.firstName);
//						maenge.setText(result.lastName);
//						actionButton.setText("Gem");
//					}
//					
//				});
//			}
//		};
//	}
//	
//	@UiHandler("actionButton")
//	public void actionButtonClick(ClickEvent event)
//	{
//		UserDTO user = new UserDTO(0, raavare_Id.getText(), maenge.getText(), ini.getText(), cpr.getText(),
//				password.getText(), Integer.parseInt(userLevel.getText()));
//		
//		// New user
//		if (rb_Id.getText().equals(""))
//		{
//			service.createOperator(user, actionCallback());
//		}
//		// Update user
//		else
//		{
//			user.userId = Integer.parseInt(rb_Id.getText());
//			service.updateOperator(user, actionCallback());
//		}
//	}
//	
//	@UiHandler("newButton")
//	public void newButtonClick(ClickEvent event) {
//		rb_Id.setText("");
//		raavare_Id.setText("");
//		maenge.setText("");
//		ini.setText("");
//		cpr.setText("");
//		password.setText("");
//		userLevel.setText("");
//		actionButton.setText("Opret");
//	}
//	
//	public void populateCellList()
//	{
//		service.listOperator(user.userId, token, new AsyncCallback<ArrayList<UserDTO>>()
//		{
//			
//			@Override
//			public void onFailure(Throwable caught)
//			{
//				Window.alert("Der skete en fejl. Kontakt venligst administratoren");
//			}
//			
//			@Override
//			public void onSuccess(ArrayList<UserDTO> results)
//			{
//				ArrayList<String> values = new ArrayList<String>();
//				
//				for (UserDTO result : results)
//					values.add(result.userId + " : " + result.getFullName());
//				
//				cellList = new CellListHelper(values, selectionHandler);
//				
//				content.clear();
//				content.add(cellList.get());
//			}
//			
//		});
//	}
//	
//	public AsyncCallback<Void> actionCallback() {
//		return new AsyncCallback<Void>() {
//
//			@Override
//			public void onFailure(Throwable caught)
//			{
//				Window.alert("Der skete en fejl. Kontakt venligst administratoren");
//				GWT.log(caught.getMessage());
//			}
//
//			@Override
//			public void onSuccess(Void result)
//			{
//				populateCellList();
//				
//				rb_Id.setText("");
//				raavare_Id.setText("");
//				maenge.setText("");
//				ini.setText("");
//				cpr.setText("");
//				password.setText("");
//				userLevel.setText("");
//				actionButton.setText("Opret");
//				
//				Window.alert("Din bruger er nu gemt");
//			}
//		};
//	}
//	
//	public void getOperatorService()
//	{
//		this.service = GWT.create(OperatorService.class);
//		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
//		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
//	}
//	
//}
