package myWeatherProject.middleware;

import java.io.IOException;
import java.net.MalformedURLException;

//  интерфейс коммуникации со сторонним web service
public interface IForeinWebServiceWeather  {
	//метод получения json строки со стороннего web service
    public String getJson(String key) throws MalformedURLException, IOException;
}
