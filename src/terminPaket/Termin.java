package terminPaket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

//import com.mysql.jdbc.Connection;
import java.sql.*;


public class Termin
{
   
	  private static int terminID;
	  private static int terminPersonID;
	  private static LocalDate terminDatum;
	  private static LocalTime terminZeit;
	  private static LocalTime terminZeitBis;
	  private static int terminRaum;
	  private static String terminInfo;
	  private static int terminPrivat;
	  private static String terminPrivatInfo;
	  private static String terminFarbe;
	 
	  
   
   
   
   private static final Datenbankverbindung dbVerbindung = new Datenbankverbindung();
	  int lIdPerson;
	  static ArrayList<Termin> lTerminAL = new ArrayList<Termin>();
   
	
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
   	             lTermin.setTerminDatum((lErgebnis.getDate(3).toLocalDate()));
   	             lTermin.setTerminZeit((lErgebnis.getTime(4)).toLocalTime());
   	             lTermin.setTerminZeitBis((lErgebnis.getTime(5)).toLocalTime());
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
   public void übergebenInDB(Connection connection)
   {
	 
     int size = ermittelnReihen(connection);
  	
		 try
		 {
			{
			String insertSQL = "Insert into termin(IDTermin,Datum, UhrzeitVon, UhrzeitBis, InfoTermin,OEID,EndDatum,Raum,Privat) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setLong(1, size);
			preparedStatement.setString(2, getTerminDatum().toString());
			preparedStatement.setString(3, getTerminZeit().toString());
			preparedStatement.setString(4, getTerminZeitBis().toString());
			preparedStatement.setString(5, getTerminInfo());
			preparedStatement.setString(6, "1");
			preparedStatement.setString(7, getTerminDatum().toString());
			preparedStatement.setInt(8, getTerminRaum());
			preparedStatement.setInt(9, getTerminPrivat());
			preparedStatement.executeUpdate();
			}
		 }
		 catch (SQLException e)
		 {
			// TODO Automatisch generierter Erfassungsblock
			System.out.print("Fehler");
			e.printStackTrace();
		 }
		
		 
   }
   
   
   public int ermittelnReihen(Connection connection)
   {
      int size = 0;
     	
		 try
		 {
			{ 
			Statement stmt = connection.createStatement();
			String query = "SELECT * FROM termin";
		    ResultSet rs = stmt.executeQuery(query);
		    rs.last();
		    size = rs.getRow();
		    rs.beforeFirst();
		    System.out.print(size+1);
			}
		  }
		    catch (SQLException e)
			 {
				// TODO Automatisch generierter Erfassungsblock
				System.out.print("Fehler");
				e.printStackTrace();
			 }
		 return size+1;
		 
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


   
}
