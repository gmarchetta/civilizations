package ar.com.civilizations.repository;

import java.util.Date;

import ar.com.civilizations.model.DayWeather;

public interface WeatherRepository {
	void updateRainPeakDayWeather(DayWeather maxAreaDayWeather);
	
	DayWeather saveDayWeather(DayWeather dayWeather);
	
	DayWeather getDayWeather(Long dayWeatherId);
	
	DayWeather getDayWeather(Date dayWeatherDate);
}
