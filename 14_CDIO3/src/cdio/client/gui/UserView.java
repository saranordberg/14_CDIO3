package cdio.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import cdio.client.implementation.OperatorServiceClientImpl;

public class UserView extends Composite
{
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private FlexTable fTable;
	private Label Fname, Lname, OPRID;
	
	public UserView()
	{
		initWidget(this.vPanel);
		OperatorServiceClientImpl clientImpl = new OperatorServiceClientImpl(
				GWT.getModuleBaseURL() + "operatorService");
		
		clientImpl.getOperator(1);
		
		this.vPanel.add(hPanel);
		
		this.fTable = new FlexTable();
		fTable.setBorderWidth(1);
		fTable.setCellPadding(1);
		// Heading;
		Fname = new Label("First Name");
		fTable.setWidget(0, 0, Fname);
		Lname = new Label("Last Name");
		fTable.setWidget(0, 1, Lname);
		OPRID = new Label("Operator ID");
		fTable.setWidget(0, 2, OPRID);
		
		// First person - this will be a loop based on query done in database
		// row number will be = i for each instance i++
		// columns will always be the same
		Fname = new Label("Rasmus");
		fTable.setWidget(1, 0, Fname);
		Lname = new Label("Gundel");
		fTable.setWidget(1, 1, Lname);
		OPRID = new Label("11");
		fTable.setWidget(1, 2, OPRID);
		
		this.hPanel.add(fTable);
	}
	
}
