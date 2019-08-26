package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Gruppe
{
   String Bezeichnung;
   int ID_Gruppe;
   public Gruppe(String bezeichnung, int iD_Gruppe)
   {
	  super();
	  Bezeichnung = bezeichnung;
	  ID_Gruppe = iD_Gruppe;
   }

   public static ArrayList<Gruppe> auslesenDB(Connection pConnection)
	    {
	      Gruppe lGruppe;
	      ArrayList<Gruppe> lGruppeAL = new ArrayList<Gruppe>();
	      Statement lBefehl;
	      ResultSet lErgebnis;

	      try {
	      lBefehl 	= pConnection.createStatement();
	      lErgebnis = lBefehl.executeQuery("SELECT Bezeichnung,ID Gruppe FROM person p;");
	      lErgebnis.first(); 

	      while(!lErgebnis.isAfterLast())   
	         {
	          lGruppe = new Gruppe(lErgebnis.getString(1),lErgebnis.getInt(2));
	           lGruppeAL.add(lGruppe);
	           lErgebnis.next();
	         }
	         } catch (Exception ex)
	              {
	                System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
	              }
	      return lGruppeAL ;
	 }
   public static ArrayList<String> getPersonen()
   {
	  ArrayList<String> lGruppe = new ArrayList<String>();
	  ArrayList<Gruppe> lGruppeAL = new ArrayList<Gruppe>();
	  lGruppeAL = GruppenbearbeitungController.; //Methode in controller verknüpfen
	  for(int i = 0; i<lGruppeAL.size();i++)
	  {
		 String lName = new String();
		 lName = lGruppeAL.get(i).getBezeichnung();
		 String lPersonenID = "";
		 lPersonenID = Integer.toString(lGruppeAL.get(i).getID_Gruppe());
		 lName = lName.concat(" " +lPersonenID);
		 lGruppe.add(lName);
	  }
	  return(lGruppe);
   }

   public String getBezeichnung()
   {
      return Bezeichnung;
   }

   public void setBezeichnung(String bezeichnung)
   {
      Bezeichnung = bezeichnung;
   }

   public int getID_Gruppe()
   {
      return ID_Gruppe;
   }

   public void setID_Gruppe(int iD_Gruppe)
   {
      ID_Gruppe = iD_Gruppe;
   }

}
