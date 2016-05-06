package cdio.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import cdio.dal.dto.OperatoerDTO;
import cdio.service.OperatorService;
import cdio.service.OperatorServiceAsync;

public class AdminView extends Composite
{
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private static FlexTable fTable;
	private Label Fname, Lname, OPRID, edit, details, cprnr, pw;
	private Button createBtn, editBtn, detailsBtn, saveBtn, cancelBtn;
	private TextBox fName, lName, oprId, cpr, password;
	private OperatorServiceAsync service;
	
	public AdminView()
	{
		initWidget(this.vPanel);
//		OperatorServiceClientImpl clientImpl = new OperatorServiceClientImpl(
//				GWT.getModuleBaseURL() + "operatorService");
		
		getOperatorService(GWT.getModuleBaseURL() + "operatorService");
		
		
		// clientImpl.getOperator(1);
		this.createBtn = new Button("Create");
		createBtn.addClickHandler(new createClickHandler());
		this.vPanel.add(createBtn);
		
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
		
		this.hPanel.add(fTable);
		
		getListOperators();
	}
	
	public void getOperatorService(String url)
	{
		System.out.println(url);
		this.service = GWT.create(OperatorService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
	}
	
	/**
	 * ClickHandler for the "Create" button. Creates a new row as the first,
	 * displaying text fields and a save and cancel button
	 */
	private class createClickHandler implements ClickHandler
	{
		
		public void onClick(ClickEvent event)
		{
			
			fTable.insertRow(1);
			fName = new TextBox();
			lName = new TextBox();
			// oprId = new TextBox();
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
			// fTable.setWidget(1, 4, oprId);
			fTable.setWidget(1, 5, cpr);
			fTable.setWidget(1, 6, password);
			
		}
		
	}
	
	/**
	 * ClickHandler for the "Edit" button Redirects you to a new page or makes
	 * the fields ready for edit
	 */
	private class editClickHandler implements ClickHandler
	{
		
		@Override
		public void onClick(ClickEvent event)
		{
			String buttonName = ((Button)event.getSource()).getStyleName();
			int rownr = fTable.getCellForEvent(event).getRowIndex();
			int oprId = Integer.parseInt(buttonName.replace("gwt-Button Edit", ""));
			getOperator(oprId, new EditDetailsCallback(rownr, oprId));
			
			fName = new TextBox();
			fName.setText(fTable.getText(rownr, 2));
			lName = new TextBox();
			lName.setText(fTable.getText(rownr, 3));
			
			
			saveBtn = new Button("Save");
			saveBtn.addClickHandler(new saveClickHandler2(rownr, oprId));
			hPanel.add(saveBtn);
			
			cancelBtn = new Button("Cancel");
			cancelBtn.addClickHandler(new cancelClickHandler2(rownr));
			hPanel.add(cancelBtn);
			
			
			fTable.setWidget(0, 5, cprnr);
			fTable.setWidget(0, 6, pw);
			
			fTable.setWidget(rownr, 2, fName);
			fTable.setWidget(rownr, 3, lName);
		}
		
	}
	
	/**
	 * ClickHandler for the "Details" button Displays more details about a
	 * person
	 */
	private class detailsClickHandler implements ClickHandler
	{
		
		@Override
		public void onClick(ClickEvent event)
		{
			String buttonName = ((Button)event.getSource()).getStyleName();
			int rownr = fTable.getCellForEvent(event).getRowIndex();
			int oprId = Integer.parseInt(buttonName.replace("gwt-Button Details", ""));
			getOperator(oprId, new DetailsCallback(rownr, oprId));
		}
		
	}
	
	/**
	 * ClickHandler for the "Save" button saves the data entered into the
	 * TextBox fields and calls the private method "add". Also removes "Save"
	 * and Cancel" button
	 */
	private class saveClickHandler implements ClickHandler
	{
		
		@Override
		public void onClick(ClickEvent event)
		{
			saveBtn.removeFromParent();
			cancelBtn.removeFromParent();
			
			fTable.removeCell(0, 6);
			fTable.removeCell(0, 5);
			fTable.removeCell(1, 6);
			fTable.removeCell(1, 5);
			
			OperatoerDTO opr = new OperatoerDTO(0, fName.getText(), lName.getText(), cpr.getText(), password.getText());
			
			insertOperator(opr);
			
			// Label newOprId = new Label(oprId.getText());
			// fTable.setWidget(1, 4, newOprId);
			
		}
		
	}
	
	private class saveClickHandler2 implements ClickHandler
	{
		
		private int rownr, oprId;
		
		public saveClickHandler2(int rownr, int oprId)
		{
			this.rownr = rownr;
			this.oprId = oprId;
		}
		
		@Override
		public void onClick(ClickEvent event)
		{
			saveBtn.removeFromParent();
			cancelBtn.removeFromParent();
			//private TextBox fName, lName, oprId, cpr, password;
			
			fTable.removeCell(0, 6);
			fTable.removeCell(0, 5);
			fTable.removeCell(rownr, 6);
			fTable.removeCell(rownr, 5);
			
			Label firstName = new Label(fName.getText());
			fTable.setWidget(rownr, 2, firstName);
			
			Label lastName = new Label(lName.getText());
			fTable.setWidget(rownr, 3, lastName);
			OperatoerDTO op = new OperatoerDTO(oprId, fName.getText(), "hej", cpr.getText(), password.getText()); 
			GWT.log("Oprid: " + oprId);
			GWT.log(fName.getText() + " hej " + " " + cpr.getText() + " " + password.getText());
			updateOperator(op);
			
		}
		
	}
	
	/**
	 * ClickHandler for the "Cancel" button Removes the newly created row and
	 * removes save and cancel button
	 */
	private class cancelClickHandler implements ClickHandler
	{
		
		@Override
		public void onClick(ClickEvent event)
		{
			saveBtn.removeFromParent();
			cancelBtn.removeFromParent();
			fTable.removeRow(1);
			
			fTable.removeCell(0, 6);
			fTable.removeCell(0, 5);
		}
		
	}
	
	/**
	 * ClickHandler for the "Cancel" button Removes the newly created row and
	 * removes save and cancel button
	 */
	private class cancelClickHandler2 implements ClickHandler
	{
		
		private int rownr;
		
		public cancelClickHandler2(int rownr)
		{
			this.rownr = rownr;
		}
		
		@Override
		public void onClick(ClickEvent event)
		{
			GWT.log(((Integer)rownr).toString());
			cancelBtn.removeFromParent();
			fTable.removeCell(rownr, 6);
			fTable.removeCell(rownr, 5);
			fTable.removeCell(0, 6);
			fTable.removeCell(0, 5);
			
			fName.removeFromParent();
			lName.removeFromParent();
			cpr.removeFromParent();
			password.removeFromParent();
			
			fTable.setWidget(rownr, 2, new Label(fName.getValue()));
			fTable.setWidget(rownr, 3, new Label(fName.getValue()));
			saveBtn.removeFromParent();
		}
		
	}
	
	/**
	 * private method that adds button to the newly created row
	 */
	private void add(String name, String style, int column, ClickHandler handler)
	{
		String number = name + fTable.getRowCount();
		Button tempButton;
		tempButton = new Button(number, handler);
		tempButton.setStyleName(style, true);
		fTable.setWidget(1, column, tempButton);
	}
	
	public void getOperator(int oprId, AsyncCallback callback)
	{
		GWT.log("id: " + oprId);
		service.getOperatoer(oprId, callback);
	}
	
	
	public void getListOperators()
	{
		service.listOperator(new ListOperatorsCallback());
	}
	
	public void insertOperator(OperatoerDTO opr) {
		service.createOperator(opr, new CreateCallback());
	}
	
	public void updateOperator(OperatoerDTO opr)
	{
		service.updateOperator(opr, new updateCallback());
	}
	
	private class CreateCallback implements AsyncCallback<OperatoerDTO>
	{
		@Override
		public void onFailure(Throwable caught)
		{
			GWT.log(caught.getMessage());
			System.out.println("An Error has accoured");
			
		}

		@Override
		public void onSuccess(OperatoerDTO result)
		{
			getListOperators();
		}
		
	}
	
	private class DetailsCallback implements AsyncCallback<OperatoerDTO>
	{
		private int rownr, oprId;
		
		public DetailsCallback(int rownr, int oprId) {
			this.rownr = rownr;
			this.oprId = oprId;
		}
		@Override
		public void onFailure(Throwable caught)
		{
			GWT.log(caught.getMessage());
			System.out.println("An Error has accoured");
			
		}

		@Override
		public void onSuccess(OperatoerDTO result)
		{
			GWT.log(result.cpr);
			GWT.log(result.password);
			GWT.log("" + rownr);
			GWT.log(oprId + "");
			Label newcpr = new Label(result.cpr);
			Label newpassword = new Label(result.password);
			
			cancelBtn = new Button("Cancel");
			cancelBtn.addClickHandler(new cancelClickHandler2(rownr));
			hPanel.add(cancelBtn);
			
			cprnr = new Label("CPR number");
			pw = new Label("Password");
			
			fTable.setWidget(0, 5, cprnr);
			fTable.setWidget(0, 6, pw);
			
			fTable.setWidget(rownr, 5, newcpr);
			fTable.setWidget(rownr, 6, newpassword);
		}
		
	}
	
	private class EditDetailsCallback implements AsyncCallback<OperatoerDTO>
	{
		private int rownr, oprId;
		
		public EditDetailsCallback(int rownr, int oprId) {
			this.rownr = rownr;
			this.oprId = oprId;
		}
		@Override
		public void onFailure(Throwable caught)
		{
			GWT.log(caught.getMessage());
			System.out.println("An Error has accoured");
			
		}

		@Override
		public void onSuccess(OperatoerDTO result)
		{
			cpr = new TextBox();
			cpr.setValue(result.cpr);
			password = new TextBox();
			password.setValue(result.password);
			
			fTable.setWidget(rownr, 5, cpr);
			fTable.setWidget(rownr, 6, password);
		}
		
	}
	
	private class updateCallback implements AsyncCallback
	{
		
		@Override
		public void onFailure(Throwable caught)
		{
			GWT.log(caught.getMessage());
			System.out.println("An Error has accoured");
			
		}

		@Override
		public void onSuccess(Object result)
		{
			GWT.log("Success");
		}
		
	}
	
	private class ListOperatorsCallback implements AsyncCallback<ArrayList<OperatoerDTO>>
	{
		
		@Override
		public void onFailure(Throwable caught)
		{
			System.out.println("An Error has accoured");
			
		}
		
		@Override
		public void onSuccess(ArrayList<OperatoerDTO> result)
		{
			int i = 1;
			// First person - this will be a loop based on query done in database
			// row number will be = i for each instance i++
			// columns will always be the same
			for(OperatoerDTO opr : result)
			{
				
				Button editBtn = new Button();
				editBtn.setText("Edit");
				editBtn.setStyleName("Edit" + opr.oprId, true);
				editBtn.addClickHandler(new editClickHandler());
				
				Button detailsBtn = new Button();
				detailsBtn.setText("Details");
				detailsBtn.setStyleName("Details" + opr.oprId, true);
				detailsBtn.addClickHandler(new detailsClickHandler());
				
				fTable.setWidget(i, 0, editBtn);
				fTable.setWidget(i, 1, detailsBtn);
				fTable.setWidget(i, 2, new Label(opr.oprNavn));
				fTable.setWidget(i, 3, new Label(opr.oprNavn));
				fTable.setWidget(i, 4, new Label(((Integer)opr.oprId).toString()));
				i++;
			}
		}
		
	}
}
