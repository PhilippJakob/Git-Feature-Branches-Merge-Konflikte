
package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OE
{
   String Name;
   int ID;
   public OE(String name, int iD)
   {
	  super();
	  Name = name;
	  ID = iD;
   }
   //Liest DB aus und füllt AL
   public static ArrayList<OE> auslesenDB(Connection pConnection)
	    {
	      OE lOE;
	      ArrayList<OE> lOEAL = new ArrayList<OE>();
	      Statement lBefehl;
	      ResultSet lErgebnis;

	      try {
	      lBefehl 	= pConnection.createStatement();
	      lErgebnis = lBefehl.executeQuery("SELECT OENAME,OEID FROM organisationseinheit p;");
	      lErgebnis.first(); 

	      while(!lErgebnis.isAfterLast())   
	         {
	          lOE = new OE(lErgebnis.getString(1),lErgebnis.getInt(2));
	           lOEAL.add(lOE);
	           lErgebnis.next();
	         }
	         } catch (Exception ex)
	              {
	                System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
	              }
	      return lOEAL ;
	 }
   //Wandelt AL<Personen> in AL<String> um
   public static ArrayList<String> getOEn()
   {
	  ArrayList<String> lOEn = new ArrayList<String>();
	  ArrayList<OE> lOEnAL = new ArrayList<OE>();
	  lOEnAL = OEController.getOEnAL();
	  for(int i = 0; i<lOEnAL.size();i++)
	  {
		 String lName = new String();
		 lName = lOEnAL.get(i).getName();
		 String lOEnID = "";
		 lOEnID = Integer.toString(lOEnAL.get(i).getID());
		 lName = lName.concat(" " +lOEnID);
		 lOEn.add(lName);
	  }
	  return(lOEn);
   }
   //Holt höchste ID
   public static String getLetzteOE()
   {
	  String lOE = new String();
	  ArrayList<String> lOEnAL = new ArrayList<String>();
	  lOEnAL = getOEn();
	  lOE = lOEnAL.get(lOEnAL.size()-1);
	  return(lOE);
   }
   public String getName()
   {
      return Name;
   }
   public void setName(String name)
   {
      Name = name;
   }
   public int getID()
   {
      return ID;
   }
   public void setID(int iD)
   {
      ID = iD;
   }
}