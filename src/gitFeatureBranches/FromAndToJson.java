package gitFeatureBranches;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import com.google.gson.Gson;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
public class FromAndToJson
{
   static String jsonStr;

   public static void main(String[] a)
   {
	  toJson();
	  toJava();
   }

   public static String toJson()
   {
	  jsonStr = null;
	  Termin termin = new Termin();
	  termin = getObjectData(termin);
	  ObjectMapper Obj = new ObjectMapper();
	  JavaTimeModule javaTimeModule = new JavaTimeModule();
	  javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyyMMdd")));
	  javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyyMMdd")));
	  Obj.registerModule(javaTimeModule);
	  Obj.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	  try
	  {
		 Obj.writeValue(new File("p:\\staff.json"), termin);
		 String jsonInString2 = Obj.writerWithDefaultPrettyPrinter().writeValueAsString(termin);
		 System.out.println(jsonInString2);

	  }

	  catch (IOException e)
	  {
		 e.printStackTrace();
	  }
	  return jsonStr;
   }

   public static Termin getObjectData(Termin termine)
   {
	  LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
	  termine = new Termin(1,1,LocalDate.parse("2019-11-12"),LocalDate.parse("2019-11-11"),LocalTime.parse("10:10:10"),LocalTime.parse("10:10:10"),3,"BeispielInfo",0,"BeispielPrivatInfo","grün");
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