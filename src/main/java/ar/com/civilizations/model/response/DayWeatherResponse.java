package ar.com.civilizations.model.response;

import ar.com.civilizations.model.Weather;

public class DayWeatherResponse {
	private Long day;
	private Weather weather;

	public Long getDay() {
		return day;
	}

	public void setDay(Long day) {
		this.day = day;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

}
