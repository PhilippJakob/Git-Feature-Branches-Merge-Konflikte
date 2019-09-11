package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Stelle
{
   String Name;
   int ID;
   int OE;
   
   
   public Stelle(String name, int iD,int oe)
   {
	  super();
	  Name = name;
	  ID = iD;
	  OE = oe;
   }
   //Liest DB aus und füllt AL
  public static ArrayList<Stelle> auslesenDB(Connection pConnection)
	    {
	      Stelle lStelle;
	      ArrayList<Stelle> lStelleAL = new ArrayList<Stelle>();
	      Statement lBefehl;
	      ResultSet lErgebnis;

	      try {
	      lBefehl 	= pConnection.createStatement();
	      lErgebnis = lBefehl.executeQuery("SELECT StID,StName,OEID FROM stelle;");
	      lErgebnis.first(); 

	      while(!lErgebnis.isAfterLast())   
	         {
	          lStelle = new Stelle(lErgebnis.getString(1),lErgebnis.getInt(2),lErgebnis.getInt(3));
			   lStelleAL.add(lStelle);
	           lErgebnis.next();
	         }
	         } catch (Exception ex)
	              {
	                System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
	              }
	      return lStelleAL ;
	 }
   //Wandelt AL<Personen> in AL<String> um
  public static ArrayList<String> getStellen()
   {
	  ArrayList<String> lStellen = new ArrayList<String>();
	  ArrayList<Stelle> lStellenAL = new ArrayList<Stelle>();
	  
	  for(int i = 0; i<lStellenAL.size();i++)
	  {
		 String lName = new String();
		 lName = lStellenAL.get(i).getName();
		 String lStellenID = "";
		 lStellenID = Integer.toString(lStellenAL.get(i).getID());
		 lName = lName.concat(" " +lStellenID);
		 lStellen.add(lName);
	  }
	  return(lStellen);
   }
   //Holt höchste ID
   public static String getLetzteStelle()
   {
	  String lStelle = new String();
	  ArrayList<String> lStellenAL = new ArrayList<String>();
	  lStellenAL = getStellen();
	  lStelle = lStellenAL.get(lStellenAL.size()-1);
	  return(lStelle);
   }
   
   
   
   
   
   public String getName()
   {
      return Name;
   }
   public void setName(String name)
   {
      Name = name;
   }
   public int getID()
   {
      return ID;
   }
   public void setID(int iD)
   {
      ID = iD;
   }
}

