package terminPaket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

//import com.mysql.jdbc.Connection;
import java.sql.*;


public class Termin
{
   
	  private static int terminID;
	  private static int terminPersonID;
	  private static LocalDateTime terminDatumVon;
	  private static LocalDateTime terminDatumBis;
	  private static LocalDateTime terminZeit;
	  private static LocalDateTime terminZeitBis;
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
	        
	        while(!lErgebnis.isAfterLast())
	        {
   	             lTermin.setTerminID(lErgebnis.getInt(1));
   	             lTermin.setTerminPersonID(pIdPerson);
   	           //  lTermin.setTerminDatumVon((lErgebnis.getDate(3).toLocalDate()));
   	          // lTermin.setTerminZeit((lErgebnis.getTime(4)).toLocalTime());
   	          // lTermin.setTerminZeitBis((lErgebnis.getTime(5)).toLocalTime());
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
			   
			 
			String insertSQL = "Insert into termin(IDTermin, StartDatum, EndDatum, UhrzeitVon, UhrzeitBis, InfoTermin,OEID,Raum,Privat) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setLong(1, size);
			preparedStatement.setDate(2, Date.valueOf(terminDatumVon.toLocalDate()));
			preparedStatement.setDate(3, Date.valueOf(terminDatumBis.toLocalDate()));
			preparedStatement.setTime(4, Time.valueOf(terminDatumVon.toLocalTime()));
			preparedStatement.setTime(5,  Time.valueOf(terminDatumBis.toLocalTime()));
			preparedStatement.setString(6, getTerminInfo());
			preparedStatement.setString(7, "1");
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
      int PersonenID = 0;
     	
		 try
		 {
			{ 
			Statement stmt = connection.createStatement();
			String query = "SELECT * FROM termin";
		    ResultSet rs = stmt.executeQuery(query);
		    rs.last();
		    PersonenID = rs.getRow();
		    rs.beforeFirst();
			}
		  }
		    catch (SQLException e)
			 {
				// TODO Automatisch generierter Erfassungsblock
				System.out.print("Fehler");
				e.printStackTrace();
			 }
		 return PersonenID+1;
		 
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


  
   
   public static ArrayList<Termin> getlTerminAL()
   {
      return lTerminAL;
   }
   public static void setlTerminAL(ArrayList<Termin> lTerminAL)
   {
      Termin.lTerminAL = lTerminAL;
   }
   public static LocalDateTime getTerminDatumVon()
   {
      return terminDatumVon;
   }
   public static void setTerminDatumVon(LocalDateTime terminDatumVon)
   {
      Termin.terminDatumVon = terminDatumVon;
   }
   public static LocalDateTime getTerminDatumBis()
   {
      return terminDatumBis;
   }
   public static void setTerminDatumBis(LocalDateTime terminDatumBis)
   {
      Termin.terminDatumBis = terminDatumBis;
   }
   public static LocalDateTime getTerminZeit()
   {
      return terminZeit;
   }
   public static void setTerminZeit(LocalDateTime terminZeit)
   {
      Termin.terminZeit = terminZeit;
   }
   public static LocalDateTime getTerminZeitBis()
   {
      return terminZeitBis;
   }
   public static void setTerminZeitBis(LocalDateTime terminZeitBis)
   {
      Termin.terminZeitBis = terminZeitBis;
   }
  
   }


   

