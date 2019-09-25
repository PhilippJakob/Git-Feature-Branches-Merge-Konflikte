package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Gruppenzugehörigkeit
{
   int IDGruppe;

   public Gruppenzugehörigkeit(int iDGruppe)
   {
	  super();
	  IDGruppe = iDGruppe;
   }

   public static ArrayList<Gruppe> auslesenDB(Connection pConnection, String pPerson)
   {
	  Gruppe lGruppe;
	  ArrayList<Gruppe> lGruppenAL = new ArrayList<Gruppe>();
	  Statement lBefehl;
	  ResultSet lErgebnis;
	  String[] tokens;
	  tokens = pPerson.split(" ");
	  try
	  {
		 lBefehl = pConnection.createStatement();
		 lErgebnis = lBefehl.executeQuery(
				  "SELECT IDGruppe,Bezeichnung FROM gruppe g where IDGruppe = ANY(SELECT IDGruppe from gruppenzugehörigkeit where IDPerson = "
						   + tokens[tokens.length - 1] + ");"
		 );
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
		 String lGruppename = new String();
		 lGruppename = lGruppeAL.get(i).getBezeichnung();
		 String lGruppenID = "";
		 lGruppenID = Integer.toString(lGruppeAL.get(i).getID());
		 lGruppename = lGruppename.concat(" " + lGruppenID);
		 lGruppe.add(lGruppename);
	  }
	  return (lGruppe);
   }

}
