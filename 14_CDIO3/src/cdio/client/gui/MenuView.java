package cdio.client.gui;

import java.util.UUID;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import cdio.client.helpers.UserLevels;
import cdio.client.helpers.UserLevels.MenuLevel;
import cdio.dal.dto.UserDTO;
import cdio.service.OperatorService;
import cdio.service.OperatorServiceAsync;

public class MenuView extends Composite
{
	private static MenuUiBinder uiBinder = GWT.create(MenuUiBinder.class);
	
	private OperatorServiceAsync service;
	private final String SERVICEURL = "operatorService";
	
	@UiTemplate("Menu.ui.xml")
	interface MenuUiBinder extends UiBinder<Widget, MenuView>
	{
	}
	
	@UiField
	Button opr_button, prescriptions_button, raw_materials_button, raw_materials_batches_button, product_batches_button, example_button;
	@UiField
	VerticalPanel content;
	@UiField
	Button sign_out_btn;
	@UiField
	Label name;
	// private iSignOut_callback sign_out;
	private UserDTO user;
	private String token;
	private int userLevel;
	
	private iMenuCallback callback;
	
	public MenuView(UserDTO user, String token)
	{
		this.token = token;
		this.user = user;
		this.userLevel = user.userLevel;
		
		initWidget(uiBinder.createAndBindUi(this));
		
		name.setText(user.firstName + " " + user.lastName);
		GWT.log(this.userLevel+"");
		GWT.log(""+UserLevels.HasRight(this.userLevel, MenuLevel.AFVEJNING));
		opr_button.setVisible(UserLevels.HasRight(this.userLevel, MenuLevel.AFVEJNING));
		prescriptions_button.setVisible(UserLevels.HasRight(this.userLevel, MenuLevel.RECEPT));
		raw_materials_button.setVisible(UserLevels.HasRight(this.userLevel, MenuLevel.RAAVARE));
		raw_materials_batches_button.setVisible(UserLevels.HasRight(this.userLevel, MenuLevel.RAAVAREBATCH));
		product_batches_button.setVisible(UserLevels.HasRight(this.userLevel, MenuLevel.PRODUKTBATCH));
		example_button.setVisible(UserLevels.HasRight(this.userLevel, MenuLevel.ADMIN));
	}
	
	public void getOperatorService()
	{
		this.service = GWT.create(OperatorService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(SERVICEURL);
	}
	
	@UiHandler("opr_button")
	void userButtonClick(ClickEvent event) {
		content.clear();
		content.add(new UserView(user, token));
	}
	
	public interface iMenuCallback
	{
	}
}
