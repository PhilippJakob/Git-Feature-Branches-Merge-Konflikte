package gitFeatureBranches;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import gitFeatureBranches.NeueOE.OE;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import jfxtras.scene.control.agenda.Agenda;
public class OEController 
{

    @FXML
    private ChoiceBox<?> cbUeber;

    @FXML
    private TextField tfName;

    @FXML
    private ChoiceBox<?> cbStelle;

    @FXML
    private Button btZuweisen;
    
    public void initialize()
    {
 	
 	 btZuweisen.setOnAction(new EventHandler<ActionEvent>() {
 		@Override
 	    public void handle(ActionEvent event)
 	    { 
 		   erstellenOE(tfName.commitValue(), DBVerbindung.holenConnection());
 	    }
    } );
    }
    public void erstellenOE(String pOE, Connection connection)
    {
       Statement lBefehl;
       Statement lBefehl2;
 	  String ID = new String();
 	  String[] tokens;
 	  
 	  ID = OE.getLetzteOE();
 	  tokens = ID.split(" ");
 	  ID = tokens[tokens.length-1];
 	  ID = Integer.toString(Integer.parseInt(ID)+hinzugefügteOEn);
 	  hinzugefügteOEn++;
 	  try
 	  {
 		 lBefehl 	= connection.createStatement();
 		 lBefehl.executeQuery("SET SQL_SAFE_UPDATES = 0;");
 		 lBefehl2	= connection.createStatement();
 		 lBefehl2.executeUpdate("INSERT INTO tasks(title)VALUES"+pOE.getID() +"';");
 	  }
 	  catch (SQLException e)
 	  {
 		 // TODO Automatisch generierter Erfassungsblock
 		e.printStackTrace();
 	  }
 	 
    }
  
  
}


