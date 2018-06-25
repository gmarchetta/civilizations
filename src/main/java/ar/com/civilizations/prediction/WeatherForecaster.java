package ar.com.civilizations.prediction;

import ar.com.civilizations.model.DayWeather;
import ar.com.civilizations.model.Galaxy;

public interface WeatherForecaster {
	DayWeather predictWeather(Galaxy galaxy);
}
