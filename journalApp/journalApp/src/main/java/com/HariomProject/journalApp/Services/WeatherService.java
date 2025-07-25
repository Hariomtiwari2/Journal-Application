package com.HariomProject.journalApp.Services;

import com.HariomProject.journalApp.ApiResponse.WeatherResponse;
import com.HariomProject.journalApp.Constants.PlaceHolders;
import com.HariomProject.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class WeatherService {

	@Value("${weather.api.key}")
	private String apiKey;
//	private static final String apiKey = "454b265cd18b432f68be7a064179193b";
//	private static final String api = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AppCache appCache;

	public WeatherResponse getweather(String city){
		String finalApi = appCache.AppCache.get(AppCache.Keys.WEATHER_API.toString()).replace("<city>", city).replace("<apikey>",apiKey);
		ResponseEntity<WeatherResponse> response =  restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
		WeatherResponse body = response.getBody();
		return body;
	}
	// converting json response  to java object is known as deserialization//
}
