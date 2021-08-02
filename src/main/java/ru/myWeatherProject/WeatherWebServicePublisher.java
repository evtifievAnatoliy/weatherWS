package ru.myWeatherProject;
// класс, для запуска веб-сервера с веб-сервисами

import java.net.URI;

import javax.xml.ws.Endpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.xml.ws.server.ServerRtException;

import ru.myWeatherProject.logic.UpdateTemperatureDates;
import ru.myWeatherProject.ws.WeatherWebServiceImpl;

public class WeatherWebServicePublisher {
    public static void main(String[] args) {
//		args[]
//    	localhost
//    	8083
//    	7ad6640f2d4b76480afbe652df3a7efa
//    	60000

    	
    	final Logger logger = LoggerFactory.getLogger(WeatherWebServicePublisher.class);
    	
    	try {
			
    		//в фоновом режиме запускаем обновление данных погоды
    		UpdateTemperatureDates updateTemperatureDates = new UpdateTemperatureDates(args[2], Long.parseLong(args[3]));
    		updateTemperatureDates.setDaemon(true);
    		updateTemperatureDates.start();

		
			// запускаем веб-сервер на порту (порт задается из среды окружения)
	        // и по адресу, указанному в первом аргументе,
	        // запускаем веб-сервис, передаваемый во втором аргументе
	    	final String URL =  "http://" + args[0] + ":" + args[1] + "/wss/weather"; 
	        Endpoint.publish(URL, new WeatherWebServiceImpl());
	        logger.info(URL + " сервер запущен.");
    	
    	} catch (ServerRtException e) {
			logger.error("Ошибка запуска сервера: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Args[0] - сервер, Args[1] - порт, Args[2] - ключ авторизации Openweathermap, Args[3] - частота обновления в миллисеундах ", "Error: " + e.getMessage());
		} 
    	
        
    }
}