package br.com.testgobots._services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.testgobots.dto.EntitieDTO;
import br.com.testgobots.dto.InterpretadorTextoDTO;
import br.com.testgobots.dto.ResponseApiOpenWeather;
import br.com.testgobots.dto.ResponseWeatherTalkDTO;

@Service
public class ComunicacaoExternaService {

	private final String KEY = "";
	private final String URL_WEATHER_TALK = "https://weathertalk.gobots.com.br/model/parse";
	private final String URL_API_OPEN_WEATHER = "https://api.openweathermap.org/data";
	
	public String retornaCidade(InterpretadorTextoDTO objDTO) {
    	RestTemplate restTemplate = new RestTemplate();
    	HttpEntity<InterpretadorTextoDTO> request = new HttpEntity<>(objDTO);
    	ResponseEntity<ResponseWeatherTalkDTO> response = restTemplate.exchange(URL_WEATHER_TALK, HttpMethod.POST, request, ResponseWeatherTalkDTO.class);
    	Optional<EntitieDTO> findFirst = response.getBody().getEntities().stream().filter(e -> e.getEntity().equals("city")).findFirst();
    	
    	if(findFirst.isPresent()) {
    		System.out.println("Entrei");
    		return findFirst.get().getValue();
    	}
    	
    	return null;
    	
	}

	public ResponseApiOpenWeather respostaOpenWeatherAPI(String cidade) {
		RestTemplate restTemplate = new RestTemplateBuilder().rootUri(URL_API_OPEN_WEATHER).build();
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/2.5/weather");
		System.out.println(cidade);
		builder.queryParam("q", cidade);
		builder.queryParam("units", "metrics");
		builder.queryParam("lang", "pt-br");
		builder.queryParam("appid", KEY);
		
		HttpEntity<?> entity = new HttpEntity<>(null);
		
		System.out.println(builder.toUriString());
		
		ResponseEntity<ResponseApiOpenWeather> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, ResponseApiOpenWeather.class);
		return exchange.getBody();
		
	}
	
}
