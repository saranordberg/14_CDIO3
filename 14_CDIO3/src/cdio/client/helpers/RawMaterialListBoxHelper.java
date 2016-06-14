package cdio.client.helpers;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.ListBox;

import cdio.client.gui.ReceptView;
import cdio.dal.dto.RaavareDTO;
import cdio.service.RawMaterialService;
import cdio.service.RawMaterialServiceAsync;

public class RawMaterialListBoxHelper
{
	public ArrayList<Tuple<String, String>> materials;
	public RawMaterialServiceAsync rawMaterialService;
	private final String RAW_MATERIAL_SERVICE_URL = "rawMaterialService";
	
	public RawMaterialListBoxHelper(){
		getRawMaterialService();
	}
	
	public ArrayList<Tuple<String, String>> getMaterialsListBox(String token)
	{
		if (materials != null)
			return materials;
		
		this.materials = new ArrayList<Tuple<String, String>>();
		populateWithRawMaterials(token);
		
		return materials;
	}

	private void populateWithRawMaterials(String token)
	{
		rawMaterialService.listRaavare(token, new AsyncCallback<ArrayList<RaavareDTO>>()
		{
			
			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Der skete en fejl. Kontakt venligst administratoren");
				GWT.log(caught.getMessage());
			}
			
			@Override
			public void onSuccess(ArrayList<RaavareDTO> results)
			{
				for (RaavareDTO raavare : results)
					materials.add(new Tuple<String, String>(
							raavare.raavareId + " : " + raavare.raavareNavn + " : " + raavare.leverandoer,
							raavare.raavareId + ""));
			}
			
		});
	}
	
	private void populateWithProducts(String token)
	{
		rawMaterialService.listRaavare(token, new AsyncCallback<ArrayList<RaavareDTO>>()
		{
			
			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Der skete en fejl. Kontakt venligst administratoren");
				GWT.log(caught.getMessage());
			}
			
			@Override
			public void onSuccess(ArrayList<RaavareDTO> results)
			{
				for (RaavareDTO raavare : results)
					materials.add(new Tuple<String, String>(
							raavare.raavareId + " : " + raavare.raavareNavn + " : " + raavare.leverandoer,
							raavare.raavareId + ""));
			}
			
		});
	}
	
	public void populateListBoxWithMaterials(String value, ListBox listBox, String token)
	{
		ArrayList<Tuple<String, String>> materials = getMaterialsListBox(token);
		
		for (Tuple<String, String> material : materials)
			listBox.addItem(material.x, material.y);
		
		if (value != null || !value.equals(""))
			listBox.setSelectedIndex(ListBoxHelper.getIndexByValue(value, listBox));
	}
	
	public void getRawMaterialService()
	{
		this.rawMaterialService = GWT.create(RawMaterialService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.rawMaterialService;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + RAW_MATERIAL_SERVICE_URL);
	}
	
}
