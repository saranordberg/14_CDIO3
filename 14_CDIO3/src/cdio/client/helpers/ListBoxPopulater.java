package cdio.client.helpers;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.ListBox;

import cdio.dal.dto.RaavareDTO;
import cdio.dal.dto.ReceptDTO;
import cdio.service.PrescriptionService;
import cdio.service.PrescriptionServiceAsync;
import cdio.service.RawMaterialService;
import cdio.service.RawMaterialServiceAsync;

public class ListBoxPopulater
{
	public ArrayList<Tuple<String, String>> materials;
	public RawMaterialServiceAsync rawMaterialService;
	public PrescriptionServiceAsync prescriptionService;
	private final String RAW_MATERIAL_SERVICE_URL = "rawMaterialService";
	private final String PRESCRIPTION_SERVICE_URL = "prescriptionService";
	
	public ListBoxPopulater(){
		getRawMaterialService();
		getPrescriptionService();
	}


	public void populateWithRawMaterials(String token)
	{
		this.materials = new ArrayList<Tuple<String, String>>();
		
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
	
	public void populateWithProducts(String token)
	{
		this.materials = new ArrayList<Tuple<String, String>>();
		
		prescriptionService.listRecept(token, new AsyncCallback<ArrayList<ReceptDTO>>()
		{
			
			@Override
			public void onFailure(Throwable caught)
			{
				Window.alert("Der skete en fejl. Kontakt venligst administratoren");
				GWT.log(caught.getMessage());
			}
			
			@Override
			public void onSuccess(ArrayList<ReceptDTO> results)
			{
				for (ReceptDTO recept : results)
					materials.add(new Tuple<String, String>(
							recept.receptId + " : " + recept.receptNavn,
							recept.receptId + ""));
			}
			
		});
	}
	
	public void populateListBoxWithMaterials(String value, ListBox listBox, String token)
	{
//		ArrayList<Tuple<String, String>> materials = getMaterialsListBox(token);
		
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
	
	public void getPrescriptionService(){
		this.prescriptionService = GWT.create(PrescriptionService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.prescriptionService;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + PRESCRIPTION_SERVICE_URL);
	}
	
}
