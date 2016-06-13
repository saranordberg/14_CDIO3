package cdio.client.helpers;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.ListBox;

public class ListBoxHelper
{
	public static Integer getIndexByValue(String value, ListBox listBox)
	{
		if(value == null)
			return 0;
		
		GWT.log("item count: " + listBox.getItemCount());
		
		for(int i = 0; i < listBox.getItemCount(); i++)
		{
			if(listBox.getValue(i).equals(value))
				return i;
		}
		return null;
	}
}
