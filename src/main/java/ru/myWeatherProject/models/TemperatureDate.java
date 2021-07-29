package ru.myWeatherProject.models;

//класс день. сущности: список температурных точек, дата

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class TemperatureDate {
    
    LocalDate localDate;
    ArrayList <TemperatureTime> temperatureTimes;

    public TemperatureDate() {
        temperatureTimes = new ArrayList<TemperatureTime>();
    }
    
    public TemperatureDate(LocalDate localDate) {
        this();
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
    
    public void addTemperatureTime(TemperatureTime temperatureTime){
        temperatureTimes.add(temperatureTime);
    }
    
    // функция расчета  средней температуры в течении дня
    public String getMediumTemperature(ArrayList<TemperatureTime> arrayList){
        float mediumSum = 0;
        for (TemperatureTime temperatureTime: arrayList){
            mediumSum += temperatureTime.getMediumTemp();
        }
        return String.valueOf(mediumSum/arrayList.size());
    }
    
    // функция расчета максимальной температуры в утренний период
    public String getMaxMorningTemperature(ArrayList<TemperatureTime> arrayList){
        float maxMornTemp = -99999;
        for (TemperatureTime temperatureTime: arrayList){
            if (temperatureTime.getLocalTime().getHour()<12 && temperatureTime.getLocalTime().getHour()>5)
                if (maxMornTemp < temperatureTime.getTempMax())
                    maxMornTemp = temperatureTime.getTempMax();
        }
        if (maxMornTemp == -99999){
            return "There is no such data";
        }
        return String.valueOf(maxMornTemp);
    }
    
    @Override
    public String toString() {
    	//cоздаем объект json
    	JSONObject obj = new JSONObject();
    	obj.put("localDate", localDate.toString());
    	obj.put("MediumTemperature", getMediumTemperature(temperatureTimes));
    	obj.put("MaxMorningTemperature", getMaxMorningTemperature(temperatureTimes));
        
    	return obj.toJSONString();
    }
    
}

