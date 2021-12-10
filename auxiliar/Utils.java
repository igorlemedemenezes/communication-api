package br.com.testgobots.auxiliar;

import java.util.List;

import br.com.testgobots.dto.MainDTO;
import br.com.testgobots.dto.ResponseApiOpenWeather;
import br.com.testgobots.dto.WeatherDTO;

public class Utils {

	public static String montarRecomendacao(ResponseApiOpenWeather body) {
		return "A temperatura em " + body.getName() + " será de aproximadamente de " + retornaMedidaDaTemperatura(body.getMain()) + retornaDescricaoClima(body.getWeather());
	}

	public static String retornaDescricaoClima(List<WeatherDTO> weather) {
		if(weather.size() > 0) {
			return " podendo estar " + weather.get(0).getDescription() + ".";
		}
		return ".";
	}

	public static String retornaMedidaDaTemperatura(MainDTO main) {
		Float media = (main.getTemp_max() + main.getTemp_min())/2;
		return String.format("%.2f", media);
	}
	
}
