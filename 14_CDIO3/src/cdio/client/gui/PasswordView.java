package cdio.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;

import cdio.client.helpers.CellListHelper;
import cdio.client.validate.ValidatorHelper;
import cdio.dal.dto.UserDTO;
import cdio.dal.password.PasswordGenerator;
import cdio.service.UserService;
import cdio.service.UserServiceAsync;

public class PasswordView extends Composite
{
	@UiTemplate("PasswordView.ui.xml")
	interface PasswordViewUiBinder extends UiBinder<Widget, PasswordView>
	{
	}
	
	private static PasswordViewUiBinder uiBinder = GWT.create(PasswordViewUiBinder.class);
	
	private UserServiceAsync service;
	private final String SERVICEURL = "userService";
	
	@UiField
	public TextBox oldPw, newPw, newPw2;
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
	
	public PasswordView(UserDTO user, String token)
	{
		getUserService();
		initWidget(uiBinder.createAndBindUi(this));
		
		this.user = user;
		this.token = token;
		
		
	}
	
	@UiHandler("actionButton")
	public void actionButtonClick(ClickEvent event)
	{
		
	}
	
	public void getUserService()
	{
		this.service = GWT.create(UserService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + SERVICEURL);
	}
	
}
