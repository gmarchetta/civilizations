package ar.com.civilizations.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import ar.com.civilizations.model.DayWeather;
import ar.com.civilizations.model.Galaxy;
import ar.com.civilizations.model.Planet;
import ar.com.civilizations.prediction.WeatherForecaster;
import ar.com.civilizations.repository.WeatherRepository;

public class GalaxyServiceImpl implements GalaxyService {
	private final static Logger LOGGER = Logger.getLogger(GalaxyServiceImpl.class);
	
	private WeatherForecaster weatherForecaster;
	private WeatherRepository weatherRepository;
	private LocationService locationService;

	@Inject
	public GalaxyServiceImpl(WeatherForecaster weatherForecaster, WeatherRepository weatherRepository, LocationService locationService) {
		this.weatherForecaster = weatherForecaster;
		this.weatherRepository = weatherRepository;
		this.locationService = locationService;
	}

	@Override
	public void initModel() {
		Calendar calendar = new GregorianCalendar();
		int dayPrediction = 0;

		Galaxy galaxy = initGalaxy();
		DayWeather maxAreaDayWeather = null;

		while (dayPrediction < 365 * 10) {
			LOGGER.debug("Logging for day: " + calendar.getTime());
			DayWeather currentDayWeather = weatherForecaster.predictWeather(galaxy);
			currentDayWeather.setDate(calendar.getTime());

			if (maxAreaDayWeather == null
					|| currentDayWeather.getAreaTriangle() > maxAreaDayWeather.getAreaTriangle()) {
				maxAreaDayWeather = currentDayWeather;
			}

			weatherRepository.saveDayWeather(currentDayWeather);
			dayPrediction++;
			calendar.add(Calendar.DATE, 1);

			updateGalaxy(galaxy);
		}

		weatherRepository.updateRainPeakDayWeather(maxAreaDayWeather);
	}

	private Galaxy initGalaxy() {
		Galaxy galaxy = null;
		Gson gson = new Gson();
		BufferedReader br = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			br = new BufferedReader(new FileReader(classLoader.getResource("planets.json").getFile()));
			galaxy = gson.fromJson(br, Galaxy.class);
		} catch (FileNotFoundException e) {
			// LOGGER.log("Error reading data source for galaxy initialization.");
		}

		// Sorting by distance to sun
		Collections.sort(galaxy.getPlanets());
		
		for(Planet planet : galaxy.getPlanets()) {
			locationService.updatePlanetCartesianCoordinates(planet);
		}
		
		return galaxy;
	}

	private void updateGalaxy(Galaxy galaxy) {
		for (Planet planet : galaxy.getPlanets()) {
			float currentAngle = planet.getAngle() + planet.getDailyAngleVariation();
			if (currentAngle < 0) {
				currentAngle = 360 + currentAngle;
			}

			planet.setAngle(currentAngle % 360);
			locationService.updatePlanetCartesianCoordinates(planet);
		}
	}
}
