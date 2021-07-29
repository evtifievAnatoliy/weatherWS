package ru.myWeatherProject.ws;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import javax.jws.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.myWeatherProject.logic.TemperatureDates;

// но здесь используется с параметром endpointInterface,
// указывающим полное имя класса интерфейса нашего веб-сервиса
@WebService(endpointInterface = "ru.myWeatherProject.ws.IWeatherWebService")
public class WeatherWebServiceImpl implements IWeatherWebService{

	private static final Logger logger = LoggerFactory.getLogger(WeatherWebServiceImpl.class);
		
	public WeatherWebServiceImpl() {
		super();
	}

	//WebMethod /wss/weather
	public String getWeatherString(String localDate) {
		logger.info(LocalDateTime.now() + ": localDate: " + localDate);
		try {
			LocalDate dt = LocalDate.parse(localDate);
			// возвращаем погоду в json формате
			return TemperatureDates.getWeather(dt);
			
		}catch (DateTimeParseException e) {
			logger.error(LocalDateTime.now() + ": DateTimeParseException : " + e.getMessage());
			return "Входной параметр строка: yyyy-MMM-dd";
		}catch (Exception e) {
			logger.error(LocalDateTime.now() + ": Exeption: " + e.getMessage());
			return "Error:500";
		}
    }
}
