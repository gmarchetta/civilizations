package ar.com.civilizations.repository;

import java.util.Date;

import ar.com.civilizations.mapper.Param;
import ar.com.civilizations.model.DayWeather;

public interface WeatherRepository {
	void updateRainPeakDayWeather(DayWeather maxAreaDayWeather);

	DayWeather getDayWithRainPeakDayWeather();

	void saveDayWeather(DayWeather dayWeather);

	DayWeather getDayWeatherById(Long dayWeatherId);

	DayWeather getDayWeatherByDate(Date dayWeatherDate);
	
	DayWeather getDayWeatherByDay(Long dayWeatherDay);
	
	Long getAmountOfDryDays();
	
	Long getAmountOfRainyDays();

	Long getAmountOfOptimalConditionDays();
}
