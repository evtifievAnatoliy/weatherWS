package ru.myWeatherProject.logic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.myWeatherProject.WeatherWebServicePublisher;
import ru.myWeatherProject.middleware.JsonWeatherParser;
import ru.myWeatherProject.middleware.OpenweathermapImpl;

//класс обновления данных по погоде. Работает в асинхронном режиме
public class UpdateTemperatureDates extends Thread{
	
	// авторизационный ключ для web service, задается в параметрах среды
	private String keyString;
	// период обновления в миллисекундах, задается в параметрах среды
	private long updateTime;
	// объект доступа к стороннему web service
	private OpenweathermapImpl openweathermapImpl;

	static final Logger logger = LoggerFactory.getLogger(UpdateTemperatureDates.class);
	
	public UpdateTemperatureDates(String keyString, long updateTime) {
		super();
		this.keyString = keyString;
		this.updateTime = updateTime;
		openweathermapImpl = new OpenweathermapImpl();
	}



	@Override
    public void run() {
		while(true) {
			try {
				TemperatureDates.getInstance().setTemperatureDays(
						JsonWeatherParser.getTemperatureDates(
								openweathermapImpl.getJson(keyString)));
				logger.info(LocalDateTime.now() + ": Update success");
				
			} catch (MalformedURLException e) {
				logger.error(LocalDateTime.now() + ": Update false: " + " MalformedURLException:  " + e.getMessage());
			} catch (ParseException e) {
				logger.error(LocalDateTime.now() + ": Update false: " + " ParseException:  " + e.getMessage());
			} catch (IOException e) {
				logger.error(LocalDateTime.now() + ": Update false: " + " IOException:  " + e.getMessage());
			}
			finally {
				//спим минуту
				try {
					sleep(updateTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					logger.error(LocalDateTime.now() + " Error : InterruptedException : " + e.getMessage());
				}
			}
		}
	}
	
}
