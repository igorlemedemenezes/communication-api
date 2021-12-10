package br.com.testgobots._controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.testgobots._services.ComunicacaoExternaService;
import br.com.testgobots.auxiliar.Utils;
import br.com.testgobots.dto.InterpretadorTextoDTO;
import br.com.testgobots.dto.RecomendacaoDTO;
import br.com.testgobots.dto.ResponseApiOpenWeather;

@RestController
@RequestMapping(value = "/modelo")
public class Controller {

	@Autowired
	private ComunicacaoExternaService comunicacaoExternaService;
	
//	https://weathertalk.gobots.com.br/model/parse
//	https://api.openweathermap.org/data/2.5/weather?q=São paulo&units=metric&lang=pt_br&appid=809b14c511f15ce21ece2e776b56cdcf
	
    @PostMapping(value = "/rota")
    public ResponseEntity<RecomendacaoDTO> modelo(@RequestBody InterpretadorTextoDTO objDTO) {
    	String cidade = comunicacaoExternaService.retornaCidade(objDTO);
    	ResponseApiOpenWeather responseWeatherAPI = comunicacaoExternaService.respostaOpenWeatherAPI(cidade);
    	String fraseRecomendacao = Utils.montarRecomendacao(responseWeatherAPI);
    	RecomendacaoDTO recomendacao = new RecomendacaoDTO(fraseRecomendacao);
    	return ResponseEntity.ok().body(recomendacao);
    }

}