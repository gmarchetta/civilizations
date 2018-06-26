package ar.com.civilizations.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import ar.com.civilizations.model.DayWeather;

public interface WeatherMapper {
	void updateRainPeakDayWeather(@Param("dayWeather") DayWeather maxAreaDayWeather);

	DayWeather getDayWithRainfallPeakDayWeather();

	void saveDayWeather(@Param("dayWeather") DayWeather dayWeather);

	DayWeather getDayWeatherById(@Param("dayWeatherId") Long dayWeatherId);

	DayWeather getDayWeatherByDate(@Param("dayWeatherDate") Date dayWeatherDate);
	
	DayWeather getDayWeatherByDay(@Param("dayWeatherDay") Long dayWeatherDay);

	DayWeather getMaxDayWeather();
	
	Long getAmountOfRainyDays();
	
	Long getAmountOfDryDays();
	
	Long getAmountOfOptimalConditionDays();
}
