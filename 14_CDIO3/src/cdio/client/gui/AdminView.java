package cdio.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import cdio.client.implementation.OperatorServiceClientImpl;
import cdio.dal.dto.OperatoerDTO;

public class AdminView extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private FlexTable fTable;
	private Label Fname, Lname, OPRID, edit, details, cprnr, pw;
	private Button createBtn, editBtn, detailsBtn, saveBtn, cancelBtn;
	private TextBox fName, lName, oprId, cpr, password;

	public AdminView() {
		initWidget(this.vPanel);
		OperatorServiceClientImpl clientImpl = new OperatorServiceClientImpl(GWT.getModuleBaseURL() + "operatorService");

//		clientImpl.getOperator(1);
		this.createBtn = new Button("Create");
		createBtn.addClickHandler(new createClickHandler());
		this.vPanel.add(createBtn);

		this.editBtn = new Button("Edit");
		editBtn.addClickHandler(new editClickHandler());

		this.detailsBtn = new Button("Details");
		detailsBtn.addClickHandler(new detailsClickHandler());

		this.vPanel.add(hPanel);

		this.fTable = new FlexTable();
		fTable.setBorderWidth(1);
		fTable.setCellPadding(1);
		// Heading
		edit = new Label("Edit");
		fTable.setWidget(0, 0, edit);
		details = new Label("Details");
		fTable.setWidget(0, 1, details);
		Fname = new Label("First Name");
		fTable.setWidget(0, 2, Fname);
		Lname = new Label("Last Name");
		fTable.setWidget(0, 3, Lname);
		OPRID = new Label("Operator ID");
		fTable.setWidget(0, 4, OPRID);

		// First person - this will be a loop based on query done in database
		// row number will be = i for each instance i++
		// columns will always be the same
		fTable.setWidget(1, 0, editBtn);
		fTable.setWidget(1, 1, detailsBtn);
		Fname = new Label("Rasmus");
		fTable.setWidget(1, 2, Fname);
		Lname = new Label("Gundel");
		fTable.setWidget(1, 3, Lname);
		OPRID = new Label("11");
		fTable.setWidget(1, 4, OPRID);

		this.hPanel.add(fTable);
	}
	/**
	 * ClickHandler for the "Create" button.
	 * Creates a new row as the first, displaying text fields
	 * and a save and cancel button
	 */
	private class createClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {

			fTable.insertRow(1);
			fName = new TextBox();
			lName = new TextBox();
			//oprId = new TextBox();
			cpr = new TextBox();
			password = new TextBox();
			
			cpr.setMaxLength(10);
			password.setMaxLength(8);

			saveBtn = new Button("Save");
			saveBtn.addClickHandler(new saveClickHandler());
			hPanel.add(saveBtn);

			cancelBtn = new Button("Cancel");
			cancelBtn.addClickHandler(new cancelClickHandler());
			hPanel.add(cancelBtn);

			cprnr = new Label("CPR number");
			pw = new Label("Password");

			fTable.setWidget(0, 5, cprnr);
			fTable.setWidget(0, 6, pw);

			fTable.setWidget(1, 2, fName);
			fTable.setWidget(1, 3, lName);
			//fTable.setWidget(1, 4, oprId);
			fTable.setWidget(1, 5, cpr);
			fTable.setWidget(1, 6, password);

		}

	}

	/**
	 * ClickHandler for the "Edit" button
	 * Redirects you to a new page or makes the fields ready for edit
	 */
	private class editClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			String buttonName = event.getClass().getName();
			int rownr = fTable.getCellForEvent(event).getCellIndex() +1;
			fName = new TextBox();
			fName.setText(fTable.getText(rownr, 2));
			lName = new TextBox();
			lName.setText(fTable.getText(rownr, 3));
			cpr = new TextBox();
//			
//			cpr.setMaxLength(10);
//			password.setMaxLength(8);

			saveBtn = new Button("Save");
			saveBtn.addClickHandler(new saveClickHandler());
			hPanel.add(saveBtn);

			cancelBtn = new Button("Cancel");
			cancelBtn.addClickHandler(new cancelClickHandler2());
			hPanel.add(cancelBtn);
			
			cprnr = new Label("CPR number");
			pw = new Label("Password");

			fTable.setWidget(0, 5, cprnr);
			fTable.setWidget(0, 6, pw);

			fTable.setWidget(rownr, 2, fName);
			fTable.setWidget(rownr, 3, lName);
			fTable.setWidget(rownr, 5, cpr);
			fTable.setWidget(rownr, 6, password);
			
			String oprID = buttonName.replaceAll("editBtn", "");
		}

	}
	
	/**
	 * ClickHandler for the "Details" button
	 * Displays more details about a person
	 */
	private class detailsClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			
			
			
			
			String buttonName = event.getClass().getName();
			
			String oprID = buttonName.replaceAll("detailsBtn", "");
		}

	}
	
	/**
	 * ClickHandler for the "Save" button
	 * saves the data entered into the TextBox fields and calls the private method "add".
	 *  Also removes "Save" and Cancel" button
	 */
	private class saveClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			saveBtn.removeFromParent();
			cancelBtn.removeFromParent();

			for (int i = 0; i <= 1; i++) {
				fTable.removeCell(i, 6);
				fTable.removeCell(i, 5);
			}
			
			
			add("editBtn", "Edit", 0, new editClickHandler());
			add("detailsBtn", "Details", 1, new detailsClickHandler());
			
			Label firstName = new Label(fName.getText());
			fTable.setWidget(1, 2, firstName);

			Label lastName = new Label(lName.getText());
			fTable.setWidget(1, 3, lastName);

			Label newOprId = new Label(oprId.getText());
			fTable.setWidget(1, 4, newOprId);

		}

	}

	
	/**
	 * ClickHandler for the "Cancel" button
	 * Removes the newly created row and removes save and cancel button
	 */
	private class cancelClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			saveBtn.removeFromParent();
			cancelBtn.removeFromParent();
			fTable.removeRow(1);

			fTable.removeCell(0, 6);
			fTable.removeCell(0, 5);
			
			

			

		}

	}
	/**
	 * ClickHandler for the "Cancel" button
	 * Removes the newly created row and removes save and cancel button
	 */
	private class cancelClickHandler2 implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			saveBtn.removeFromParent();
			cancelBtn.removeFromParent();

			fTable.removeCell(0, 6);
			fTable.removeCell(0, 5);
			
			fName.removeFromParent();
			lName.removeFromParent();
			cpr.removeFromParent();
			
			fTable.setWidget(1, 2, Fname);
			fTable.setWidget(1, 3, Lname);
			fTable.setWidget(1, 4, OPRID);
		}

	}
	
	/**
	 * private method that adds button to the newly created row
	 */
	private void add(String name, String text, int column, ClickHandler handler){
			String number = name + fTable.getRowCount();
			Button tempButton;
			tempButton = new Button(number, handler);
			tempButton.setText(text);
			fTable.setWidget(1, column, tempButton);
	}
	
	public static void GetOperator(OperatoerDTO result) {
		
	}

	public static void getListOperators(ArrayList<OperatoerDTO> result)
	{
		// TODO Auto-generated method stub
		
	}
}
