package ar.com.civilizations.model;

import java.util.Date;

public class DayWeather {
	private long id;
	private Date date;
	private Weather weather;
	private float areaTriangle;
	private long day;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	public float getAreaTriangle() {
		return areaTriangle;
	}

	public void setAreaTriangle(float areaTriangle) {
		this.areaTriangle = areaTriangle;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDay() {
		return day;
	}

	public void setDay(long day) {
		this.day = day;
	}
}
