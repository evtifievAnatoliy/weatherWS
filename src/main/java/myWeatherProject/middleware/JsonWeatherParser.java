package myWeatherProject.middleware;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ru.myWeatherProject.models.TemperatureDate;
import ru.myWeatherProject.models.TemperatureTime;

// класс для парсинга json  
public class JsonWeatherParser {
	
	//метод парсинга json. На вход поступает json строка, на выход коллекция Температурных дней 
	public static ArrayList <TemperatureDate> getTemperatureDates (String jsonStr) throws ParseException{
		
		JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonStr.toString());
		
		JSONArray jsonList= (JSONArray) jsonObject.get("list");
        ArrayList <TemperatureDate> temperatureDays= new ArrayList<TemperatureDate>();
        
        Iterator i = jsonList.iterator();
        String lastDateStr = "first";
        TemperatureDate temperatureDate = new TemperatureDate();
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            String localDate = (String) innerObj.get("dt_txt");
            String[] timeArray = localDate.split(" ");
            
            if(!lastDateStr.equals(timeArray[0])){
                if (lastDateStr.equals("first"))
                    temperatureDate.setLocalDate(LocalDate.parse(timeArray[0]));
                else
                    temperatureDate = new TemperatureDate(LocalDate.parse(timeArray[0]));
                temperatureDays.add(temperatureDate);
            }
            LocalTime time = LocalTime.parse(timeArray[1]);
            String tempMin = parseJsonObject(innerObj, "main", "temp_min");
            String tempMax = parseJsonObject(innerObj, "main", "temp_max");
            temperatureDate.addTemperatureTime(new TemperatureTime(time, Float.parseFloat(tempMin), Float.parseFloat(tempMax)));
            lastDateStr = timeArray[0];
        }
        return temperatureDays;
	}
	
	//вспомогательный метод парсинга внутри объекта
	private static String parseJsonObject(JSONObject object, String objectName, String innerName){
        JSONObject jsonObject= (JSONObject) object.get(objectName);
        return (String) jsonObject.get(innerName).toString();
    }
}