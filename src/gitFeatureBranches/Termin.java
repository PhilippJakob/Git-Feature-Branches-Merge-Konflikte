package gitFeatureBranches;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

//import com.mysql.jdbc.Connection;

public class Termin
{

   public Termin(int terminID, LocalDate terminDatumVon, LocalDate terminDatumBis, LocalTime terminZeit,
			LocalTime terminZeitBis, int terminRaum, String terminInfo, int terminPrivat, String terminPrivatInfo,
			String terminFarbe)
   {
	  super();
	  this.terminID = terminID;
	  this.terminDatumVon = terminDatumVon;
	  this.terminDatumBis = terminDatumBis;
	  this.terminZeit = terminZeit;
	  this.terminZeitBis = terminZeitBis;
	  this.terminRaum = terminRaum;
	  this.terminInfo = terminInfo;
	  this.terminPrivat = terminPrivat;
	  this.terminPrivatInfo = terminPrivatInfo;
	  this.terminFarbe = terminFarbe;
   }

   public Termin()
   {

   }

   private int				 terminID;
   private int				 terminPersonID;
   private LocalDate		 terminDatumVon;
   private LocalDate		 terminDatumBis;
   private LocalTime		 terminZeit;
   private LocalTime		 terminZeitBis;
   private int				 terminRaum;
   private String			 terminInfo;
   private int				 terminPrivat;
   private String			 terminPrivatInfo;
   private String			 terminFarbe;
   static final DBVerbindung dbVerbindung = new DBVerbindung();

   public static ArrayList<Termin> auslesenTermine(Connection pConnection, int pIdPerson)
   {
	  ArrayList<Termin> lTerminAL = new ArrayList<Termin>();
	  int lIdPerson = pIdPerson;

	  Statement lBefehl;
	  ResultSet lErgebnis;
	  try

	  {
		 lBefehl = pConnection.createStatement();
		 lErgebnis = lBefehl.executeQuery("SELECT * FROM dbpr_termin.termin");
		 lErgebnis.first();

		 while (!lErgebnis.isAfterLast())
		 {
			Termin lTermin = new Termin(
					 lErgebnis.getInt(1), lErgebnis.getDate(3).toLocalDate(), lErgebnis.getDate(4).toLocalDate(), lErgebnis.getTime(5).toLocalTime(), lErgebnis.getTime(6).toLocalTime(), lErgebnis.getInt(7), lErgebnis.getString(8), lErgebnis.getInt(10), lErgebnis.getString(11), lErgebnis.getString(13)
			);
			lTermin.setTerminID(lErgebnis.getInt(1));
			// lTermin.setTerminPersonID(pIdPerson);
			// lTermin.setTerminPrivatInfo();
			// lTermin.setTerminFarbe();
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
	  System.out.println(getTerminDatumVon());
	  System.out.println(Date.valueOf(getTerminDatumVon()));
	  try
	  {
		 {
			String insertSQL = "Insert into termin(IDTermin, StartDatum, EndDatum, UhrzeitVon, UhrzeitBis, InfoTermin,OEID,Raum,Privat) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setLong(1, size);
			preparedStatement.setDate(2, Date.valueOf(getTerminDatumVon()));
			preparedStatement.setDate(3, Date.valueOf(getTerminDatumBis()));
			preparedStatement.setTime(4, Time.valueOf(getTerminZeit()));
			preparedStatement.setTime(5, Time.valueOf(getTerminZeitBis()));
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
	  return PersonenID + 1;

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

   public LocalDate getTerminDatumVon()
   {
	  return terminDatumVon;
   }

   public void setTerminDatumVon(LocalDate localDate)
   {
	  terminDatumVon = localDate;
   }

   public LocalDate getTerminDatumBis()
   {
	  return terminDatumBis;
   }

   public void setTerminDatumBis(LocalDate TerminDatumBis)
   {
	  terminDatumBis = TerminDatumBis;
   }

   public LocalTime getTerminZeit()
   {
	  return terminZeit;
   }

   public void setTerminZeit(LocalTime TerminZeit)
   {
	  terminZeit = TerminZeit;
   }

   public LocalTime getTerminZeitBis()
   {
	  return terminZeitBis;
   }

   public void setTerminZeitBis(LocalTime TerminZeitBis)
   {
	  terminZeitBis = TerminZeitBis;
   }

}
