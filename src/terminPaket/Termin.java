package terminPaket;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

//import com.mysql.jdbc.Connection;
import java.sql.*;


public class Termin 
{
   
	  private int terminID;
	  private int terminPersonID;
	  private LocalDateTime terminDatum;
	  private LocalDateTime terminDatumEnde;
	  private int terminRaum;
	  private String terminInfo;
	  private int terminPrivat;
	  private String terminPrivatInfo;
	  private String terminFarbe;
	  
   
   
   
   private static final Datenbankverbindung dbVerbindung = new Datenbankverbindung();
	  int lIdPerson;
	  static ArrayList<Termin> lTerminAL = new ArrayList<Termin>();
   
	
 
	


   public Termin( int pTerminID, int pTerminPersonID, LocalDateTime pTerminDatum,LocalDateTime pTerminDatumEnde, int pTerminRaum, String pTerminInfo, int pTerminPrivat,
			   String pTerminPrivatInfo , String pTerminFarbe)
   {
	  super();
	  this.terminID = pTerminID;
	  this.terminPersonID = pTerminPersonID;
	  this.terminDatum = pTerminDatum;
	  this.terminDatumEnde = pTerminDatumEnde;
	  this.terminRaum = pTerminRaum;
	  this.terminInfo = pTerminInfo;
	  this.terminPrivat = pTerminPrivat;
	  this.terminPrivatInfo = pTerminPrivatInfo;
	  this.terminFarbe = pTerminFarbe;
   }


   

   public Termin()
   {
	  // TODO Automatisch generierter Konstruktorstub
   }




   public static ArrayList <Termin> auslesenTermine(Connection pConnection, int pIdPerson)
    {
	  int lIdPerson = pIdPerson;
	  Termin lTermin = new Termin();
	  Statement lBefehl;
	  ResultSet lErgebnis;
	  try
	  
	     {
	    	lBefehl=pConnection.createStatement();
	        lErgebnis = lBefehl.executeQuery("SELECT * FROM dbpr_termin.termin WHERE IDPerson=" + pIdPerson);
	        lErgebnis.first();
	        
	        while(! lErgebnis.isAfterLast())
	        {
   	             lTermin.setTerminID(lErgebnis.getInt(1));
   	             lTermin.setTerminPersonID(pIdPerson);
   	         //    lTermin.setTerminDatum((lErgebnis.getDate(3).toLocalDate()));
   	          //   lTermin.setTerminZeit((lErgebnis.getTime(4)).toLocalTime());
   	          //   lTermin.setTerminZeitBis((lErgebnis.getTime(5)).toLocalTime());
   	             lTermin.setTerminRaum(lErgebnis.getInt(6));
   	             lTermin.setTerminInfo(lErgebnis.getString(7));
   	             lTermin.setTerminPrivat(lErgebnis.getInt(8));
   	             lTermin.setTerminPrivatInfo(lErgebnis.getString(9));
   	             lTermin.setTerminFarbe(lErgebnis.getString(11));
   	             lTerminAL.add(lTermin);
   	             lErgebnis.next();
   	             
   	             
	        }
		
	      
	     }

	     catch (SQLException e)
	     {
		   
		     e.printStackTrace();
	    
	     }

	     return lTerminAL;
   }


   public LocalDateTime getTerminDatum()
   {
      return terminDatum;
   }




   public void setTerminDatum(LocalDateTime terminDatum)
   {
      this.terminDatum = terminDatum;
   }




   public LocalDateTime getTerminDatumEnde()
   {
      return terminDatumEnde;
   }




   public void setTerminDatumEnde(LocalDateTime terminDatumEnde)
   {
      this.terminDatumEnde = terminDatumEnde;
   }




   public int getTerminID()
   {
      return terminID;
   }


   public void setTerminID(int terminID)
   {
      this.terminID = terminID;
   }


   public int getTerminPersonID()
   {
      return terminPersonID;
   }


   public void setTerminPersonID(int terminPersonID)
   {
      this.terminPersonID = terminPersonID;
   }


   


   public int getTerminRaum()
   {
      return terminRaum;
   }


   public void setTerminRaum(int terminRaum)
   {
      this.terminRaum = terminRaum;
   }


   public String getTerminInfo()
   {
      return terminInfo;
   }


   public void setTerminInfo(String terminInfo)
   {
      this.terminInfo = terminInfo;
   }


   public int getTerminPrivat()
   {
      return terminPrivat;
   }


   public void setTerminPrivat(int terminPrivat)
   {
      this.terminPrivat = terminPrivat;
   }


   public String getTerminPrivatInfo()
   {
      return terminPrivatInfo;
   }


   public void setTerminPrivatInfo(String terminPrivatInfo)
   {
      this.terminPrivatInfo = terminPrivatInfo;
   }


   public String getTerminFarbe()
   {
      return terminFarbe;
   }


   public void setTerminFarbe(String terminFarbe)
   {
      this.terminFarbe = terminFarbe;
   }


  
   
}
