
package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Organisationseinheit
{
   String Name;
   int ID;
   int Über;
   
   
   public Organisationseinheit(String name, int iD,int über)
   {
	  super();
	  Name = name;
	  ID = iD;
	  Über = über;
   }
   //Liest DB aus und füllt AL
   public static ArrayList<Organisationseinheit> auslesenDB(Connection pConnection)
	    {
	  Organisationseinheit lOrganisationseinheit;
	      ArrayList<Organisationseinheit> lOrganisationseinheitAL = new ArrayList<Organisationseinheit>();
	      Statement lBefehl;
	      ResultSet lErgebnis;

	      try {
	      lBefehl 	= pConnection.createStatement();
	      lErgebnis = lBefehl.executeQuery("SELECT OENAME,OEID,OEÜBER FROM organisationseinheit o;");
	      lErgebnis.first(); 

	      while(!lErgebnis.isAfterLast())   
	         {
	          lOrganisationseinheit = new Organisationseinheit(lErgebnis.getString(1),lErgebnis.getInt(2),lErgebnis.getInt(3));
			   lOrganisationseinheitAL.add(lOrganisationseinheit);
	           lErgebnis.next();
	         }
	         } catch (Exception ex)
	              {
	                System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
	              }
	      return lOrganisationseinheitAL ;
	 }
   //Wandelt AL<Personen> in AL<String> um
   public static ArrayList<String> getOrganisationseinheiten()
   {
	  ArrayList<String> lOrganisationseinheiten = new ArrayList<String>();
	  ArrayList<Organisationseinheit> lOrganisationseinheitenAL = new ArrayList<Organisationseinheit>();
	  lOrganisationseinheitenAL = FensterController.getOrganisationseinheitAL();
	  for(int i = 0; i<lOrganisationseinheitenAL.size();i++)
	  {
		 String lName = new String();
		 lName = lOrganisationseinheitenAL.get(i).getName();
		 String lOrganisationseinheitenID = "";
		 lOrganisationseinheitenID = Integer.toString(lOrganisationseinheitenAL.get(i).getID());
		 lName = lName.concat(" " +lOrganisationseinheitenID);
		 lOrganisationseinheiten.add(lName);
	  }
	  return(lOrganisationseinheiten);
   }
   //Holt höchste ID
   public static String getLetzteOrganisationseinheit()
   {
	  String lOrganisationseinheit = new String();
	  ArrayList<String> lOrganisationseinheitenAL = new ArrayList<String>();
	  lOrganisationseinheitenAL = getOrganisationseinheiten();
	  lOrganisationseinheit = lOrganisationseinheitenAL.get(lOrganisationseinheitenAL.size()-1);
	  return(lOrganisationseinheit);
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
