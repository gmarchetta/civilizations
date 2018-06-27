package ar.com.civilizations.rest;

import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.civilizations.model.DayWeather;
import ar.com.civilizations.model.Weather;
import ar.com.civilizations.model.response.DayWeatherCount;
import ar.com.civilizations.model.response.DayWeatherResponse;
import ar.com.civilizations.prediction.WeatherForecaster;

@Path("galaxy/weather")
public class WeatherResource {
	@Inject
	private WeatherForecaster weatherForecaster;

	@GET
	@Path("dry")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAmountOfDryDays() {
		DayWeatherResponse weatherResponse = new DayWeatherResponse();
		weatherResponse.setDay(weatherForecaster.getAmountOfDryDays());
		weatherResponse.setWeather(Weather.DRY);
		return Response.ok().entity(weatherResponse).build();
	}

	@GET
	@Path("rainy")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAmountOfRainyDays() {
		DayWeatherResponse weatherResponse = new DayWeatherResponse();
		weatherResponse.setDay(weatherForecaster.getAmountOfRainyDays());
		weatherResponse.setWeather(Weather.RAINY);
		return Response.ok().entity(weatherResponse).build();
	}

	@GET
	@Path("rainypeak")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDayOfRainyPeak() {
		DayWeather dayWeather = weatherForecaster.getDayWithPeakRainfall();
		DayWeatherResponse weatherResponse = new DayWeatherResponse();
		weatherResponse.setDay(weatherForecaster.getDayWithPeakRainfall().getDay());
		weatherResponse.setWeather(dayWeather.getWeather());
		return Response.ok().entity(weatherResponse).build();
	}

	@GET
	@Path("optimal")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAmountOfOptimalConditionDays() {
		long count = weatherForecaster.getAmountOfOptimalConditionDays();
		DayWeatherCount dayWeatherCount = new DayWeatherCount();
		dayWeatherCount.setDayCount(count);
		dayWeatherCount.setWeather(Weather.OPTIMAL);
		return Response.ok().build();
	}

	@GET
	@Path("date/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWeatherForDate(@PathParam("date") Date date) {
		DayWeather dayWeather = weatherForecaster.getDayWeatherByDate(date);
		DayWeatherResponse weatherResponse = new DayWeatherResponse();
		weatherResponse.setDay(dayWeather.getDay());
		weatherResponse.setWeather(dayWeather.getWeather());
		return Response.ok().entity(weatherResponse).build();
	}
	
	@GET
	@Path("day/{day}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWeatherForDay(@PathParam("day") Long day) {
		DayWeather dayWeather = weatherForecaster.getDayWeatherByDay(day);
		DayWeatherResponse weatherResponse = new DayWeatherResponse();
		weatherResponse.setDay(dayWeather.getDay());
		weatherResponse.setWeather(dayWeather.getWeather());
		return Response.ok().entity(weatherResponse).build();
	}
}
