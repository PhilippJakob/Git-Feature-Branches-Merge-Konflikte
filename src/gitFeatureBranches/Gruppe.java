package gitFeatureBranches;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

import terminPaket.Termin;

public class Gruppe
{
   private int ID;
   String Bezeichnung;
   
   
   
  
   
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
	    	lTermin = new Termin(lErgebnis.getInt(1),lErgebnis.getInt(2),LocalDateTime.of((lErgebnis.getDate(3)).toLocalDate(),(lErgebnis.getTime(5)).toLocalTime()),LocalDateTime.of((lErgebnis.getDate(4)).toLocalDate(),(lErgebnis.getTime(6)).toLocalTime()),lErgebnis.getInt(7),lErgebnis.getString(8),lErgebnis.getInt(9),lErgebnis.getString(10),lErgebnis.getString(11));
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
	    	System.out.print("Fehler DA ISSE NICHTS IN arraylist bruh");
	 
	     }
	     return TerminAL;
	     
   }
   
  
   
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
