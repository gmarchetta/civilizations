package ar.com.civilizations.prediction;

import java.util.Date;

import ar.com.civilizations.model.DayWeather;
import ar.com.civilizations.model.Galaxy;

public interface WeatherForecaster {
	DayWeather predictWeather(Galaxy galaxy);

	DayWeather getDayWithPeakRainfall();

	DayWeather getDayWeatherById(Long dayWeatherId);

	DayWeather getDayWeatherByDate(Date dayWeatherDate);
	
	Long getAmountOfRainyDays();
	
	Long getAmountOfDryDays();
	
	Long getAmountOfOptimalConditionDays();

	DayWeather getDayWeatherByDay(Long day);
}
