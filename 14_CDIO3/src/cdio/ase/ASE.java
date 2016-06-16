package cdio.ase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import cdio.dal.dao.MySQLProduktBatchDAO;
import cdio.dal.dao.MySQLUserDAO;
import cdio.dal.dao.interfaces.DALException;
import cdio.dal.dto.ASEDTO;
import cdio.dal.dto.ProduktBatchDTO;
import cdio.dal.dto.UserDTO;
public class ASE
{
	public static void main(String[] args) throws InterruptedException, NumberFormatException, DALException
	{
			
		String hostName = "169.254.2.3";
		Socket echoSocket = null;
		int portNumber = 8000;
		try
		{
			echoSocket = new Socket(hostName, portNumber);
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String inmessage = "not initialized";
			String inline;
			
			loop(out, in, echoSocket);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			try
			{
				echoSocket.close();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		try
		{
			echoSocket.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loop(PrintWriter out, BufferedReader in, Socket echoSocket)
			throws InterruptedException, IOException, NumberFormatException, DALException
	{
		boolean loopchecker = true;
		while (loopchecker)
		{
			if (StartAfvejning(out, in))
			{
//				skipMessages(in, 1);
//				;
				KoerAfvejning(out, in, echoSocket);
			}
		}		
	}
	
	public static boolean StartAfvejning(PrintWriter out, BufferedReader in) throws IOException
	{
		sendMessage(out, "RM30 \"Start\"");
		sendMessage(out, "RM31 1");
		sendMessage(out, "RM39 1");
		skipMessages(in, 2);
		String check = getWeightReturnRM30(in);
		if (check.equals("A") || check.equals("1"))
		{
			sendMessage(out, "RM39 0");
			System.out.println("Her slutter get weight");
			
			return true;
			
		}
		else
		{
			return false;
		}
	}
	
	public static void KoerAfvejning(PrintWriter out, BufferedReader in, Socket echoSocket)
			throws InterruptedException, IOException, NumberFormatException, DALException
	{
		sendMessage(out, "@");
		sendMessage(out, "RM20 8 \"Skriv dit ID\" \"\" \"&3\"");
		String id = ExtractMessageFromRM20(in);
		MySQLUserDAO userDoa = new MySQLUserDAO();
		System.out.println(id);
		UserDTO User = userDoa.getUser(Integer.parseInt(id));
		String name = User.firstName + " " + User.lastName;
		sendMessage(out, "P111 \"er du?" + name + " \"");
		
		getConfirmation(in, out);
		sendMessage(out, "P110");
		System.out.println(in.readLine());
		sendMessage(out, "RM20 8 \"Skriv produktbatch id'en\" \"\" \"&3\"");
		String produktbatchid = ExtractMessageFromRM20(in);
		System.out.println(produktbatchid);
		MySQLProduktBatchDAO  produktdao = new MySQLProduktBatchDAO ();
		int PbId = Integer.parseInt(produktbatchid);
		ArrayList<ASEDTO> components;
		try
		{	
			components = produktdao.getStuff(PbId);
			ProduktBatchDTO pbDTO = produktdao.getProduktBatch(PbId);
			pbDTO.status = "1";
			produktdao.updateProduktBatch(pbDTO);
			
			for (int i = 1; i <= components.size(); i++)
			{
				sendMessage(out, "T");
				skipMessages(in, 1);
				sendMessage(out, "P111 \"Tjek at vægten er 0\"");
				if (getConfirmation(in, out))
				{
					
					sendMessage(out, "P111 \"Sæt beholderen på vægten\"");
					skipMessages(in, 1);
					if (getConfirmation(in, out))
					{
						sendMessage(out, "P110");
						sendMessage(out, "RM20 8 \"Skriv raavare id\" \"\" \"&3\"");
						String raavare_id = ExtractMessageFromRM20(in);
						ASEDTO dto = getNettoFromID(components, raavare_id);
						sendMessage(out, "P111 \"Afmål" + dto.netto + " og tryk ok\"");
						getConfirmation(in, out);
						sendMessage(out, "T");
						System.out.println("Start afmåling");
						if (getWeightconfirmation(in, out))
						{
							sendMessage(out, "S");
							double weight = getWeight(in);
							System.out.println("vægten er: " + weight);
							System.out.println("Aflsut afmåling");
							sendMessage(out, "P110");
						}
					}
				}
				
			}
			pbDTO = produktdao.getProduktBatch(PbId);
			pbDTO.status = "2";
			produktdao.updateProduktBatch(pbDTO);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public static void sendMessage(PrintWriter out, String message)
	{
		out.flush();
		out.println(message);
		out.flush();
		System.out.println("Sending: " + message);
	}
	
	
	
	public static boolean getConfirmation(BufferedReader in, PrintWriter out) throws IOException
	{
		sendMessage(out, "RM30 \"Ok\"");
		sendMessage(out, "RM31 1");
		sendMessage(out, "RM39 1");
//		System.out.println("message in getConfirm:" + in.readLine());
		
		
		if (getReturnValueFromRM30(in).equals("A") || getReturnValueFromRM30(in).equals("1"))
		{
			sendMessage(out, "RM39 0");
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public static double getWeight(BufferedReader in) throws IOException
	{
		String weight = in.readLine();
		while(!weight.startsWith("S S"))
			weight = in.readLine();
		
		weight = weight.substring(8, 13);
		double a = Double.parseDouble(weight);
		return a;
	}
	
	public static boolean getWeightconfirmation(BufferedReader in, PrintWriter out) throws IOException
	{
		System.out.println("Her starter get weight confirmation");
		sendMessage(out, "RM30 \"Ok\"");
		sendMessage(out, "RM31 1");
		sendMessage(out, "RM39 1");
//		skipMessages(in, 3);
		String check = getWeightReturnRM30(in);
		if (check.equals("A") || check.equals("1"))
		{
			sendMessage(out, "RM39 0");
			System.out.println("Her slutter get weight");
			
			return true;
			
		}
		else
		{
			return false;
		}
	}
	
	public static String getWeightReturnRM30(BufferedReader in) throws IOException
	{
		String inmessage;
		inmessage = in.readLine();
		//RM30 1
		while(inmessage.length() < 5 && (!inmessage.startsWith("RM30 1") || !inmessage.startsWith("RM30 A")))
			inmessage = in.readLine();
		
		System.out.println(" weight 2: " + inmessage);
		inmessage = inmessage.substring(5);
		System.out.println(" weight 3: " + inmessage);
		inmessage = inmessage.substring(0, 1);
		System.out.println(" weight 4: " + inmessage);
		return inmessage;
	}
	
	public static String getReturnValueFromRM30(BufferedReader in) throws IOException
	{
		String inmessage;
		inmessage = in.readLine();
		while(inmessage.length() < 5 && !inmessage.startsWith("RM30 1") || !inmessage.startsWith("RM30 A")) {
			inmessage = in.readLine();			
		}

		System.out.println(" rm30 4: " + inmessage);
		inmessage = inmessage.substring(5);
		System.out.println(" rm30 5: " + inmessage);
		inmessage = inmessage.substring(0, 1);
		System.out.println(" rm30 6: " + inmessage);
		return inmessage;
	}
	
	
	public static String ExtractMessageFromRM20(BufferedReader in) throws IOException
	{
		String inmessage = in.readLine();
		while(inmessage.length() < 8 || !inmessage.startsWith("RM20 A \"")) {
			inmessage = in.readLine();
			String hej ="";
		}
		
		inmessage = inmessage.substring(8);
		System.out.println(inmessage);
		int lastindex = inmessage.indexOf("\"");
		inmessage = inmessage.substring(0, lastindex);
		System.out.println(inmessage);
		return inmessage;
	}
	
//	public static String ExtractPbIdRM20(BufferedReader in) throws IOException
//	{
//		String inmessage = in.readLine();
//		
//		System.out.println(inmessage);
//		System.out.println(in.readLine());
//		inmessage = in.readLine();
//		inmessage = inmessage.substring(8);
//		
//		System.out.println(inmessage);
//		int lastindex = inmessage.indexOf("\"");
//		inmessage = inmessage.substring(0, lastindex);
//		System.out.println(inmessage);
//		return inmessage;
//	}
	
	public static void skipMessages(BufferedReader in, int i) throws IOException
	{
		for (int j = 0; j < i; j++)
		{
			System.out.println("skipped message: " + in.readLine());
		}
	}
	
	
	
	public static ASEDTO getNettoFromID (ArrayList<ASEDTO> list, String id){
		
		int intid = Integer.parseInt(id);
		
		for(ASEDTO x:list){
			
			if(intid == x.raavare_id){
				return x;
				
				
			}
			
		}
		return null;
		
		
		
	}
	
	public static void skipAllMessages(BufferedReader in) throws IOException
	{
		while (!(in.readLine().equals("")))
		{
			
		}
	}
}
