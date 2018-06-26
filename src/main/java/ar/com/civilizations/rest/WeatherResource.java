package ar.com.civilizations.rest;

import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.civilizations.model.DayWeather;
import ar.com.civilizations.prediction.WeatherForecaster;

@Path("galaxy/weather")
public class WeatherResource {
	@Inject
	private WeatherForecaster weatherForecaster;

	@GET
	@Path("dry")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAmountOfDryDays() {
		weatherForecaster.getAmountOfDryDays();
		return Response.ok().build();
	}

	@GET
	@Path("rainy")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAmountOfRainyDays() {
		weatherForecaster.getAmountOfRainyDays();
		return Response.ok().build();
	}

	@GET
	@Path("rainypeak")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDayOfRainyPeak() {
		DayWeather dayWeather = weatherForecaster.getDayWithPeakRainfall();
		return Response.ok().entity(dayWeather).build();
	}

	@GET
	@Path("optimal")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAmountOfOptimalConditionDays() {
		weatherForecaster.getAmountOfOptimalConditionDays();
		return Response.ok().build();
	}

	@GET
	@Path("{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWeatherForDay(Date date) {
		DayWeather dayWeather = weatherForecaster.getDayWeatherByDate(date);
		return Response.ok().entity(dayWeather).build();
	}
}
