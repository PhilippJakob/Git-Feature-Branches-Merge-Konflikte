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
	      Gruppe lGruppen;
	      ArrayList<Gruppe> lGruppenAL = new ArrayList<Gruppe>();
	      Statement lBefehl;
	      ResultSet lErgebnis;

	      try {
	      lBefehl 	= pConnection.createStatement();
	      lErgebnis = lBefehl.executeQuery("SELECT IDGruppe,Bezeichnung FROM gruppe g;");
	      lErgebnis.first(); 

	      while(!lErgebnis.isAfterLast())   
	         {
	          lGruppen = new Gruppe(lErgebnis.getString(1),lErgebnis.getString(2));
	           lGruppenAL.add(lGruppen);
	           lErgebnis.next();
	         }
	         } catch (Exception ex)
	              {
	                System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
	              }
	      return lGruppenAL ;
	 }
   public static ArrayList<String> getGruppe()
   {
	  ArrayList<String> lGruppe = new ArrayList<String>();
	  ArrayList<Gruppe> lGruppenAL = new ArrayList<Gruppe>();
	  lGruppenAL = FensterController.getGruppenAL();
	  for(int i = 0; i<lGruppenAL.size();i++)
	  {
		 String lBezeichnung = new String();
		 lBezeichnung = lGruppenAL.get(i).getID_Gruppe();
		 String lGruppeID = "";
		 lGruppeID = lGruppenAL.get(i).getBezeichnung();
		 lBezeichnung = lBezeichnung.concat(" " +lGruppeID);
		 lGruppe.add(lBezeichnung);
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

   public String getID_Gruppe()
   {
      return ID_Gruppe;
   }

   public void setID_Gruppe(String iD_Gruppe)
   {
      ID_Gruppe = iD_Gruppe;
   }
}