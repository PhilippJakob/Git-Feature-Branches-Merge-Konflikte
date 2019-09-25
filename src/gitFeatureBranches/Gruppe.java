package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Gruppe
{
   int	  ID;
   String Bezeichnung;

   public Gruppe(int iD, String bezeichnung)
   {
	  super();
	  ID = iD;
	  Bezeichnung = bezeichnung;
   }
   public  ArrayList<Termin>sortierenTermin(Connection pConnection)
   {
	  	
	  	 Termin lTermin;
	     ArrayList<Termin> TerminAL = new ArrayList<Termin>();
	     Statement lBefehl;
	     ResultSet lErgebnis;

	     try {
	     lBefehl 	= pConnection.createStatement();
	     lErgebnis = lBefehl.executeQuery("SELECT IDTermin, IDPerson, StartDatum, EndDatum, UhrzeitVon, UhrzeitBis, Raum, InfoTermin, Privat, PrivatInfo, Farbe FROM dbpr_termin.termin where IDGruppe="+getID()+";");
	     lErgebnis.first(); 
	    

	     while(!lErgebnis.isAfterLast())   
	        {
	    	lTermin = new Termin(lErgebnis.getInt(1),lErgebnis.getDate(3).toLocalDate(),lErgebnis.getDate(4).toLocalDate(),lErgebnis.getTime(5).toLocalTime(),lErgebnis.getTime(6).toLocalTime(),lErgebnis.getInt(7),lErgebnis.getString(8),lErgebnis.getInt(9),lErgebnis.getString(10),lErgebnis.getString(11));
	    	TerminAL.add(lTermin);
	        lErgebnis.next();
	          
	        }
	        } catch (Exception ex)
	             {
	               System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
	             }
	     if(!(TerminAL.size()==0))
	     {
	    	 return TerminAL ;
	     }
	     else
	     {
	 
	     }
	     return TerminAL;
	     
}
   public static ArrayList<Gruppe> auslesenDB(Connection pConnection)
   {
	  Gruppe lGruppe;
	  ArrayList<Gruppe> lGruppenAL = new ArrayList<Gruppe>();
	  Statement lBefehl;
	  ResultSet lErgebnis;

	  try
	  {
		 lBefehl = pConnection.createStatement();
		 lErgebnis = lBefehl.executeQuery("SELECT IDGruppe,Bezeichnung FROM gruppe g;");
		 lErgebnis.first();

		 while (!lErgebnis.isAfterLast())
		 {
			lGruppe = new Gruppe(lErgebnis.getInt(1), lErgebnis.getString(2));
			lGruppenAL.add(lGruppe);
			lErgebnis.next();
		 }
	  }
	  catch (Exception ex)
	  {
		 System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
	  }
	  return lGruppenAL;
   }

   public static ArrayList<String> getGruppen()
   {
	  ArrayList<String> lGruppe = new ArrayList<String>();
	  ArrayList<Gruppe> lGruppeAL = new ArrayList<Gruppe>();
	  lGruppeAL = FensterController.getGruppenAL();
	  for (int i = 0; i < lGruppeAL.size(); i++)
	  {
		 String lGruppennamen = " ";
		 lGruppennamen = lGruppeAL.get(i).getBezeichnung();
		 String lGruppenID = "";
		 lGruppenID = Integer.toString(lGruppeAL.get(i).getID());
		 lGruppennamen = lGruppennamen.concat(" " + lGruppenID);
		 lGruppe.add(lGruppennamen);
	  }
	  return (lGruppe);
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
