package ru.myWeatherProject.logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.myWeatherProject.models.TemperatureDate;

// объект хранения полученных дней погоды.
// сейчас сделано по простому, нет связи с БД и хранить информацию можно только для одного города
// реализован подход синглтон
public class TemperatureDates {
	
	private static TemperatureDates instance;
	private static Map<LocalDate, Object> temperatureDates = new HashMap<LocalDate, Object>();

	private TemperatureDates(){}
	
	//синглтон
	public static TemperatureDates getInstance(){ 
        if(instance == null){		
            instance = new TemperatureDates();
        }
        return instance;	
	}

	// получить погоду в определенный день, входной параметр дата
	public static String getWeather(LocalDate localDate) {
		return temperatureDates.get(localDate).toString();
	}

	// добавить погоду в объект хранения. В map ключ - дата, значение - объект
	public static void setTemperatureDays(ArrayList<TemperatureDate> temperatureDays) {
		for (TemperatureDate temperatureDate : temperatureDays) {
			temperatureDates.put(temperatureDate.getLocalDate(), temperatureDate);
		}
	}
	
}
