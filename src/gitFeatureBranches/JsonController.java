package gitFeatureBranches;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class JsonController
{
   static String jsonStr;
   @FXML
   private Button btToJava;
   @FXML
   private Button btToJson;
   @FXML
   private TextArea taText;
   @FXML
   public void initialize()
   {
	  btToJson.setOnAction(new EventHandler<ActionEvent>() {
		 @Override
		 public void handle(ActionEvent event)
		 {
			
			taText.setText(toJson());
		 }
	  });
	  btToJava.setOnAction(new EventHandler<ActionEvent>() {
		 @Override
		 public void handle(ActionEvent event)
		 {
			Termin termin = toJava();
			taText.setText("IDTermin: "+ termin.getTerminID()+"\nIDPerson: "+termin.getTerminPersonID()+"\nterminDatumVon: "+termin.getTerminDatumVon() +"\nterminDatumBis: "+termin.getTerminDatumBis()+"\nterminRaum: "+termin.getTerminRaum()+"\nterminInfo: "+termin.getTerminInfo()+"\nterminPrivat: "+termin.getTerminPrivat()+"\nterminPrivatInfo: "+termin.getTerminPrivatInfo()+"\nterminFarbe: "+termin.getTerminFarbe());
		 }
	  });
   }

   public static String toJson()
   {
	  jsonStr = null;
	  Termin termin = new Termin();
	  termin = getObjectData(termin);
	  String jsonInString2 ="";
	  ObjectMapper Obj = new ObjectMapper();
	  JavaTimeModule javaTimeModule = new JavaTimeModule();
	  javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyyMMdd")));
	  javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyyMMdd")));
	  Obj.registerModule(javaTimeModule);
	  Obj.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	  try
	  {
		 Obj.writeValue(new File("p:\\staff.json"), termin);
		 jsonInString2 = Obj.writerWithDefaultPrettyPrinter().writeValueAsString(termin);
	  }

	  catch (IOException e)
	  {
		 e.printStackTrace();
	  }
	  return jsonInString2;
   }
   public static Termin getObjectData(Termin termine)
   {
	  LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	  termine = new Termin("Titel",1,1,LocalDate.parse("2019-11-12"),LocalDate.parse("2019-11-11"),LocalTime.parse("10:10:10"),LocalTime.parse("10:10:10"),3,"BeispielInfo",0,"BeispielPrivatInfo","grün");
 	  return termine;
   }
   public static Termin toJava()
   {
	  String terminJson = jsonStr;
	  ObjectMapper Obj = new ObjectMapper();
	  Termin termin = null;
	  try
	  {
		 termin = Obj.readValue(new File("p:\\staff.json"), Termin.class);
	  }
	  catch (JsonParseException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }
	  catch (JsonMappingException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }
	  catch (IOException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }

	  System.out.println(termin.getTerminZeit());
	  return termin;
   }

}
