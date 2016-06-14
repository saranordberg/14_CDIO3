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
import cdio.client.validate.NumberValidator;
import cdio.client.validate.Validator;
import cdio.client.validate.ValidatorHelper;
import cdio.dal.dto.UserDTO;
import cdio.dal.password.PasswordGenerator;
import cdio.service.UserService;
import cdio.service.UserServiceAsync;

public class UserView extends Composite
{
	@UiTemplate("UserView.ui.xml")
	interface UserViewUiBinder extends UiBinder<Widget, UserView>
	{
	}
	
	private static UserViewUiBinder uiBinder = GWT.create(UserViewUiBinder.class);
	
	private UserServiceAsync service;
	private final String SERVICEURL = "userService";
	
	@UiField
	public VerticalPanel content;
	@UiField
	public TextBox userId, cpr, firstName, lastName, ini, password, userLevel;
	// @UiField
	// public CustomTextBox cpr;
	@UiField
	public Button actionButton;
	
	private UserDTO user;
	private String token;
	public ValidatorHelper validatorHelper = new ValidatorHelper();
	private PasswordGenerator passwordGenerator = new PasswordGenerator();
	
	/*
	 * SelectList variables
	 */
	private CellListHelper cellList;
	private SelectionChangeEvent.Handler selectionHandler = selectionHandler();
	
	public UserView(UserDTO user, String token)
	{
		getUserService();
		initWidget(uiBinder.createAndBindUi(this));
		
		this.user = user;
		this.token = token;
		
		populateCellList();
		ArrayList<Validator> cprValidators = new ArrayList<Validator>();
		ArrayList<Validator> nameValidators = new ArrayList<Validator>();
		ArrayList<Validator> iniValidators = new ArrayList<Validator>();
		ArrayList<Validator> niveauValidators = new ArrayList<Validator>();
		
		cprValidators.add(new LengthValidator(new Object[] { new Integer(10), '=' }));
		cprValidators.add(new NumberValidator(null));
		validatorHelper.add("CPR nummer", cpr, cprValidators);
		
		nameValidators.add(new LengthValidator(new Object[] { new Integer(50), '<' }));
		nameValidators.add(new CharactersValidator(null));
		validatorHelper.add("First name", firstName, nameValidators);
		validatorHelper.add("Last name", lastName, nameValidators);
		
		iniValidators.add(new LengthValidator(new Object[] { new Integer(5), '<' }));
		iniValidators.add(new CharactersValidator(null));
		validatorHelper.add("Initialer", ini, iniValidators);
		
		niveauValidators.add(new LengthValidator(new Object[] { new Integer(4), '<' }));
		niveauValidators.add(new NumberValidator(null));
		validatorHelper.add("Bruger niveau", userLevel, niveauValidators);
		
	}
	
	private Handler selectionHandler()
	{
		return new SelectionChangeEvent.Handler()
		{
			public void onSelectionChange(SelectionChangeEvent event)
			{
				String selected = cellList.selected();
				int userIdFromSelect = Integer.parseInt(selected.split(" : ")[0].replace(" ", ""));
				
				service.getUser(userIdFromSelect, token, new AsyncCallback<UserDTO>()
				{
					
					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("Der skete en fejl. Kontakt venligst administratoren");
					}
					
					@Override
					public void onSuccess(UserDTO result)
					{
						userId.setText(new Integer(result.userId).toString());
						firstName.setText(result.firstName);
						lastName.setText(result.lastName);
						ini.setText(result.ini);
						cpr.setText(result.cpr);
						password.setText(result.password);
						userLevel.setText(new Integer(result.level).toString());
						actionButton.setText("Gem");
					}
					
				});
			}
		};
	}
	
	@UiHandler("actionButton")
	public void actionButtonClick(ClickEvent event)
	{
		UserDTO user = new UserDTO(0, firstName.getText(), lastName.getText(), ini.getText(), cpr.getText(),
				password.getText(), Integer.parseInt(userLevel.getText()));
		
		if (!validatorHelper.validate())
			return;
		
		// New user
		if (userId.getText().equals(""))
		{
			user.password = passwordGenerator.generatePassword();
			service.createUser(user, token, actionCallback());
		}
		// Update user
		else
		{
			user.userId = Integer.parseInt(userId.getText());
			service.updateUser(user, token, actionCallback());
		}
	}
	
	@UiHandler("newButton")
	public void newButtonClick(ClickEvent event)
	{
		userId.setText("");
		firstName.setText("");
		lastName.setText("");
		ini.setText("");
		cpr.setText("");
		password.setText("");
		userLevel.setText("");
		actionButton.setText("Opret");
	}
	
	public void populateCellList()
	{
		service.listUser(token, new AsyncCallback<ArrayList<UserDTO>>()
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
				
				userId.setText("");
				firstName.setText("");
				lastName.setText("");
				ini.setText("");
				cpr.setText("");
				password.setText("");
				userLevel.setText("");
				actionButton.setText("Opret");
				
				Window.alert("Din bruger er nu gemt");
			}
		};
	}
	
	public void getUserService()
	{
		this.service = GWT.create(UserService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
	}
	
}
