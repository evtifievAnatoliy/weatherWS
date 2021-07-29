package myWeatherProject.middleware;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// класс для общения с сторонним web service openweathermap.org
public class OpenweathermapImpl implements IForeinWebServiceWeather{

	//метод получения json строки с погодными данными, на вход принимает key (ключ авторизации)
	//сейчас погоду выводит только для Санкт-Петербурга. 
	public String getJson(String key) throws MalformedURLException, IOException {
		
		String url = "http://api.openweathermap.org/data/2.5/forecast?q=Saint%20Petersburg,%20RU&mode=json&appid=" + key;
		
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
        con.setRequestMethod("GET");
 
        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
 
        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        in.close();

        return response.toString();
        
            
	}

}
