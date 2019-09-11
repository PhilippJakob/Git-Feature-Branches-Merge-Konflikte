package terminPaket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ZusatzinfosController 
{

   private static DBVerbindung dbVerbindung = new DBVerbindung();
    @FXML
    private Label lbZusatzinfos;

    @FXML
    private ChoiceBox<Integer> cbTermine;
    
    @FXML
    private Button btÜbernehmen;

    @FXML
    private TextField tfZusatzinfos;

    @FXML
    public void initialize()
    {
       cbTermine.getItems().addAll(auslesenDB(dbVerbindung.holenConnection()));
       btÜbernehmen.setOnAction(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event)
		    {
		       erstellenZusatzinfos(dbVerbindung.holenConnection());	       
		    }
    });
    }
    
    public static ArrayList<Integer> auslesenDB(Connection connection)
    {
       int IDTermin;
       ArrayList<Integer> IDTerminAL = new ArrayList<Integer>();
       Statement lBefehl;
       ResultSet lErgebnis;
       try
	  {
		 lBefehl = connection.createStatement();
		 lErgebnis = lBefehl.executeQuery("SELECT IDTermin FROM termin t");
		 lErgebnis.first();
		 
		 while(!lErgebnis.isAfterLast())
		 {
			IDTermin = lErgebnis.getInt(1);
			IDTerminAL.add(IDTermin);
			lErgebnis.next();
		 }
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }
       return IDTerminAL;
    }
     
     @FXML
     public void erstellenZusatzinfos(Connection connection)
     {
    	Statement lBefehl;
    	

		 try
		 {
			if(btÜbernehmen.onActionProperty() != null)
			{
			lBefehl = connection.createStatement();
			lBefehl.executeUpdate("update termin set Zusatzinfos = '"+tfZusatzinfos.getText()+"' Where IDTermin = '"+cbTermine.getValue()+"'");
			}
		 }
		 catch (SQLException e)
		 {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		 }
		
		 
     }
     
     
     
     
     
    
}	