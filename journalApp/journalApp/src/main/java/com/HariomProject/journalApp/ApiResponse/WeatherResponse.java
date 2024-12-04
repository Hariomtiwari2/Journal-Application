package com.HariomProject.journalApp.ApiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Getter
@Setter
public class WeatherResponse {
	private  Current current;

	@Setter
	@Getter
	public static class Current{
		public String observation_time;
		public int temperature;
		@JsonProperty("weather_descriptions")
		public List<String> weatherDescriptions;
		public int feelslike;

	}

}




