package br.com.testgobots.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWeatherTalkDTO {

	private String text;
	private IntentDTO intent;
	private List<EntitieDTO> entities;
	
}
