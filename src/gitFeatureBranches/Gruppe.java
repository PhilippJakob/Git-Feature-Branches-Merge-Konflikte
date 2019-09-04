package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Gruppe
{
   int ID;
   String Bezeichnung;
   public Gruppe(int iD, String bezeichnung)
   {
	  super();
	  ID = iD;
	  Bezeichnung = bezeichnung;
   }
   public static ArrayList<Gruppe> auslesenDB(Connection pConnection)
   {
     Gruppe lGruppe;
     ArrayList<Gruppe> lGruppenAL = new ArrayList<Gruppe>();
     Statement lBefehl;
     ResultSet lErgebnis;

     try {
     lBefehl 	= pConnection.createStatement();
     lErgebnis = lBefehl.executeQuery("SELECT IDGruppe,Bezeichnung FROM gruppe g;");
     lErgebnis.first(); 

     while(!lErgebnis.isAfterLast())   
        {
    	lGruppe = new Gruppe(lErgebnis.getInt(1),lErgebnis.getString(2));
    	lGruppenAL.add(lGruppe);
          lErgebnis.next();
        }
        } catch (Exception ex)
             {
               System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
             }
     return lGruppenAL ;
}
   public static ArrayList<String> getGruppen()
   {
	  ArrayList<String> lGruppe = new ArrayList<String>();
	  ArrayList<Gruppe> lGruppeAL = new ArrayList<Gruppe>();
	  lGruppeAL = FensterController.getGruppenAL();
	  for(int i = 0; i<lGruppeAL.size();i++)
	  {
		 String lGruppename = new String();
		 lGruppename = lGruppeAL.get(i).getBezeichnung();
		 String lGruppenID = "";
		 lGruppenID = Integer.toString(lGruppeAL.get(i).getID());
		 lGruppename = lGruppename.concat(" " +lGruppenID);
		 lGruppe.add(lGruppename);
	  }
	  return(lGruppe);
   }

   
   
   public int getID()
   {
      return ID;
   }
   public void setID(int iD)
   {
      ID = iD;
   }
   public String getBezeichnung()
   {
      return Bezeichnung;
   }
   public void setBezeichnung(String bezeichnung)
   {
      Bezeichnung = bezeichnung;
   }

}
