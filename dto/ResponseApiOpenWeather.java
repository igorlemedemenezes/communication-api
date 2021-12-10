package br.com.testgobots.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApiOpenWeather {

	private List<WeatherDTO> weather;
	private MainDTO main;
	private String name;
	
}
