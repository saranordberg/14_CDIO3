package cdio.client.helpers;

import java.util.ArrayList;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class CellListHelper
{
	private CellList<String> cellList = new CellList<String>(new TextCell());
	private SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
	
	public CellListHelper(ArrayList<String> values, SelectionChangeEvent.Handler selectionHandler)
	{
		createCellList(values, selectionHandler);
	}
	
	public void createCellList(ArrayList<String> values, SelectionChangeEvent.Handler selectionHandler)
	{
		cellList.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		cellList.setPageSize(values.size());
		cellList.setVisibleRange(new Range(0, values.size()));
		
		// Add a selection model to handle user selection.
		cellList.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(selectionHandler);
		
		// Set the total row count. This isn't strictly necessary, but it
		// affects
		// paging calculations, so its good habit to keep the row count up to
		// date.
		cellList.setRowCount(values.size(), true);
		
		// Push the data into the widget.
		cellList.setRowData(0, values);
	}
	
	public CellList get()
	{
		return cellList;
	}
	
	public String selected()
	{
		return selectionModel.getSelectedObject();
	}
}
