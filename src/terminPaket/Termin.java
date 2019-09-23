package terminPaket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

//import com.mysql.jdbc.Connection;
import java.sql.*;


public class Termin
{
   
	  private  int terminID;
	  private  int terminPersonID;
	  private  LocalDateTime terminDatumVon;
	  private  LocalDateTime terminDatumBis;
	  private  int terminRaum;
	  private  String terminInfo;
	  private  int terminPrivat;
	  private  String terminPrivatInfo;
	  private  String terminFarbe;
	 
	  
   
   
   
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
   	             lTermin.setTerminDatumVon(LocalDateTime.of(lErgebnis.getDate(3).toLocalDate(),lErgebnis.getTime(5).toLocalTime()));
   	             lTermin.setTerminDatumBis(LocalDateTime.of(lErgebnis.getDate(4).toLocalDate(),lErgebnis.getTime(6).toLocalTime()));
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
   public LocalDateTime getTerminDatumVon()
   {
      return terminDatumVon;
   }
   public void setTerminDatumVon(LocalDateTime terminDatumVon)
   {
      this.terminDatumVon = terminDatumVon;
   }
   public LocalDateTime getTerminDatumBis()
   {
      return terminDatumBis;
   }
   public void setTerminDatumBis(LocalDateTime terminDatumBis)
   {
      this.terminDatumBis = terminDatumBis;
   }
 
   }


   

