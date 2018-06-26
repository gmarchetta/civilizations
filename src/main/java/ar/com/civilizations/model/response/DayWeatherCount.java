package ar.com.civilizations.model.response;

import ar.com.civilizations.model.Weather;

public class DayWeatherCount {
	private Long dayCount;
	private Weather weather;

	public Long getDayCount() {
		return dayCount;
	}

	public void setDayCount(Long dayCount) {
		this.dayCount = dayCount;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

}
