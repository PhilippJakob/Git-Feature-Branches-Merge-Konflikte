package terminPaket;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

//import com.mysql.jdbc.Connection;
import java.sql.*;


public class Termin
{
   
    private int terminID;
    private int terminPersonID;
    private LocalDate terminDatum;
    private LocalTime terminZeit;
    private LocalTime terminZeitBis;
    private int terminRaum;
    private String terminInfo;
    private int terminPrivat;
    private String terminPrivatInfo;
    private String terminFarbe;
    private LocalDate terminEnde;
    
   
   
   
   private static final Datenbankverbindung dbVerbindung = new Datenbankverbindung();
    int lIdPerson;
    static ArrayList<Termin> terminAL = new ArrayList<Termin>();
   
  
   public static ArrayList <Termin> auslesenTermine(Connection pConnection, int pIdPerson)
    {
    int lIdPerson = pIdPerson;
    Statement lBefehl;
    ResultSet lErgebnis;
    ArrayList<Termin> lTerminAL = new ArrayList<Termin>();
    try
    
       {
        lBefehl=pConnection.createStatement();
          lErgebnis = lBefehl.executeQuery("SELECT * FROM dbpr_termin.termin WHERE IDPerson=" + pIdPerson);
          lErgebnis.first();
          
          while(! lErgebnis.isAfterLast())
          {
               Termin lTermin = new Termin();
               lTermin.setTerminID(lErgebnis.getInt(1));
                 lTermin.setTerminPersonID(pIdPerson);
                 lTermin.setTerminDatum((lErgebnis.getDate(3).toLocalDate()));
                 lTermin.setTerminEnde(lErgebnis.getDate(4).toLocalDate());
                 lTermin.setTerminZeit((lErgebnis.getTime(5)).toLocalTime());
                 lTermin.setTerminZeitBis((lErgebnis.getTime(6)).toLocalTime());
                 lTermin.setTerminRaum(lErgebnis.getInt(7));
                 lTermin.setTerminInfo(lErgebnis.getString(8));
                 lTermin.setTerminPrivat(lErgebnis.getInt(9));
                 lTermin.setTerminPrivatInfo(lErgebnis.getString(10));
                 lTermin.setTerminFarbe(lErgebnis.getString(12));
                 lTerminAL.add(lTermin);
                 lErgebnis.next();
//                 sa
                 
                 
          }
    
        
       }

       catch (SQLException e)
       {
       
         e.printStackTrace();
      
       }

       return lTerminAL;
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


   public LocalDate getTerminDatum()
   {
      return terminDatum;
   }


   public void setTerminDatum(LocalDate terminDatum)
   {
      this.terminDatum = terminDatum;
   }


   public LocalTime getTerminZeit()
   {
      return terminZeit;
   }


   public void setTerminZeit(LocalTime terminZeit)
   {
      this.terminZeit = terminZeit;
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


   public LocalTime getTerminZeitBis()
   {
      return terminZeitBis;
   }


   public void setTerminZeitBis(LocalTime terminZeitBis)
   {
      this.terminZeitBis = terminZeitBis;
   }


   public LocalDate getTerminEnde()
   {
      return terminEnde;
   }


   public void setTerminEnde(LocalDate terminEnde)
   {
      this.terminEnde = terminEnde;
   }

   
}