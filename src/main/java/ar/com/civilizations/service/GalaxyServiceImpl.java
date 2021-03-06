package ar.com.civilizations.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import ar.com.civilizations.exceptions.CivilizationsInternalServerErrorException;
import ar.com.civilizations.model.DayWeather;
import ar.com.civilizations.model.Galaxy;
import ar.com.civilizations.model.Planet;
import ar.com.civilizations.model.Weather;
import ar.com.civilizations.prediction.WeatherForecaster;
import ar.com.civilizations.repository.WeatherRepository;

/**
 * Class responsible for initializing the galaxy model and orchestrating galaxy
 * updates to feed to the weather forecaster
 * 
 * @author gaston.marchetta
 *
 */
public class GalaxyServiceImpl implements GalaxyService {
	private final static Logger LOGGER = LogManager.getLogger(GalaxyServiceImpl.class);

	private WeatherForecaster weatherForecaster;
	private WeatherRepository weatherRepository;
	private LocationService locationService;

	@Inject
	public GalaxyServiceImpl(WeatherForecaster weatherForecaster, WeatherRepository weatherRepository,
			LocationService locationService) {
		this.weatherForecaster = weatherForecaster;
		this.weatherRepository = weatherRepository;
		this.locationService = locationService;
	}

	/**
	 * Method to initialize model with predictions. Galaxy initialization is handed
	 * over to initGalaxy method
	 */
	// TODO: this should be configured as a job
	@Override
	public void initModel() {
		Calendar calendar = new GregorianCalendar();
		int dayPrediction = 0;

		Galaxy galaxy = initGalaxy();
		DayWeather maxAreaDayWeather = null;

		while (dayPrediction < 365 * 10) {
			LOGGER.info("Logging for day: {}", dayPrediction);
			DayWeather currentDayWeather = weatherForecaster.predictWeather(galaxy);
			LOGGER.info("Predicted weather for day: {}", currentDayWeather);
			currentDayWeather.setDate(calendar.getTime());
			currentDayWeather.setDay(dayPrediction);

			if (maxAreaDayWeather == null
					|| currentDayWeather.getAreaTriangle() > maxAreaDayWeather.getAreaTriangle()) {
				maxAreaDayWeather = currentDayWeather;
			}

			weatherRepository.saveDayWeather(currentDayWeather);

			dayPrediction++;
			calendar.add(Calendar.DATE, 1);

			updateGalaxy(galaxy);
		}

		maxAreaDayWeather.setWeather(Weather.RAINY_PEAK);
		weatherRepository.updateRainPeakDayWeather(maxAreaDayWeather);
	}

	/**
	 * Method loads planet configuration from a json file and hands over to
	 * initModel
	 * 
	 * @return
	 */
	private Galaxy initGalaxy() {
		Galaxy galaxy = null;
		Gson gson = new Gson();
		BufferedReader br = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			br = new BufferedReader(new FileReader(classLoader.getResource("planets.json").getFile()));
			galaxy = gson.fromJson(br, Galaxy.class);
		} catch (FileNotFoundException e) {
			LOGGER.error("Error reading data source for galaxy initialization.");
			throw new CivilizationsInternalServerErrorException();
		}

		// Sorting by distance to sun
		Collections.sort(galaxy.getPlanets());

		for (Planet planet : galaxy.getPlanets()) {
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
