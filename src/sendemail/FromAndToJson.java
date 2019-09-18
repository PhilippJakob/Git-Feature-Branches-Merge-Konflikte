package sendemail;	
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
import com.google.gson.Gson;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.Gson;

import sendemail.Termin; 
  
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
     
        try 
        { 
           jsonStr = Obj.writeValueAsString(termin); 
        }
        
        catch (IOException e) 
        { 
            e.printStackTrace(); 
        } 
        System.out.println(jsonStr);
        return jsonStr;
	}

    public static Termin getObjectData(Termin termine) 
	{LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		termine.setTerminID(1);
		termine.setpersonID(1);
		termine.setTerminStartDatum(LocalDate.parse("2019-11-12" ));
		termine.setTerminEndeDatum(LocalDate.parse("2019-11-11"));
	termine.setTerminZeit(LocalTime.parse("10:10:10"));
		termine.setTerminZeitBis(LocalTime.parse("10:10:10"));
		termine.setTerminRaum(3);
		termine.setTerminInfo("BeispielInfo");
		termine.setTerminPrivat(0);
		termine.setTerminPrivatInfo("BeispielPrivatInfo");
		termine.setTerminFarbe("Grün");
		return termine;
	}

    public static Termin toJava()
    { 
        String terminJson = jsonStr;
    	Gson gson = new Gson();
    	Termin termin =	gson.fromJson(terminJson, Termin.class);
        System.out.println(termin);
    	return termin;
    }
 
} 
    