package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Gruppe
{
   String Bezeichnung;
   String ID_Gruppe;
   public Gruppe(String id_Gruppe, String bezeichnung)
   {
	  super();
	  ID_Gruppe = id_Gruppe;
	  Bezeichnung = bezeichnung;
   }

   public static ArrayList<Gruppe> auslesenDB(Connection pConnection)
	    {
	      Gruppe lPerson;
	      ArrayList<Gruppe> lPersonenAL = new ArrayList<Gruppe>();
	      Statement lBefehl;
	      ResultSet lErgebnis;

	      try {
	      lBefehl 	= pConnection.createStatement();
	      lErgebnis = lBefehl.executeQuery("SELECT Name,IDPerson FROM person p;");
	      lErgebnis.first(); 

	      while(!lErgebnis.isAfterLast())   
	         {
	          lPerson = new Gruppe(lErgebnis.getString(1),lErgebnis.getInt(2));
	           lPersonenAL.add(lPerson);
	           lErgebnis.next();
	         }
	         } catch (Exception ex)
	              {
	                System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
	              }
	      return lPersonenAL ;
	 }
   public static ArrayList<String> getGruppe()
   {
	  ArrayList<String> lGruppe = new ArrayList<String>();
	  ArrayList<Gruppe> lGruppenAL = new ArrayList<Gruppe>();
	  lGruppenAL = FensterController.getGruppenAL();
	  for(int i = 0; i<lGruppenAL.size();i++)
	  {
		 String lName = new String();
		 lName = lGruppenAL.get(i).getName();
		 String lGruppeID = "";
		 lGruppeID = Integer.toString(lGruppenAL.get(i).getID());
		 lName = lName.concat(" " +lGruppeID);
		 lGruppe.add(lName);
	  }
	  return(lGruppe);
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