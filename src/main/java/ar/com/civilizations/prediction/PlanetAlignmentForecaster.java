package ar.com.civilizations.prediction;

import java.util.Date;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import ar.com.civilizations.model.DayWeather;
import ar.com.civilizations.model.Galaxy;
import ar.com.civilizations.model.Planet;
import ar.com.civilizations.model.Weather;
import ar.com.civilizations.repository.WeatherRepository;
import ar.com.civilizations.service.LocationService;
import ar.com.civilizations.service.MathService;

@Service
public class PlanetAlignmentForecaster implements WeatherForecaster {
	private LocationService locationService;
	private MathService mathService;
	private WeatherRepository weatherRepository;

	@Inject
	public PlanetAlignmentForecaster(LocationService locationService, MathService mathService,
			WeatherRepository weatherRepository) {
		this.locationService = locationService;
		this.mathService = mathService;
		this.weatherRepository = weatherRepository;
	}

	public DayWeather predictWeather(Galaxy galaxy) {
		Planet firstPlanet = galaxy.getPlanets().get(0);
		Planet secondPlanet = galaxy.getPlanets().get(1);
		Planet thirdPlanet = galaxy.getPlanets().get(2);

		DayWeather dayWeather = new DayWeather();
		if (locationService.arePlanetsAligned(firstPlanet, secondPlanet, thirdPlanet)) {
			if (locationService.arePlanetsAlignedWithTheSun(firstPlanet, secondPlanet)) {
				dayWeather.setWeather(Weather.DRY);
			} else {
				dayWeather.setWeather(Weather.OPTIMAL);
			}
		} else {
			float ab = mathService.calculateVectorModule(firstPlanet.getxCoordinate(), firstPlanet.getyCoordinate(),
					secondPlanet.getxCoordinate(), secondPlanet.getyCoordinate());
			float bc = mathService.calculateVectorModule(secondPlanet.getxCoordinate(), secondPlanet.getyCoordinate(),
					thirdPlanet.getxCoordinate(), thirdPlanet.getyCoordinate());
			float ac = mathService.calculateVectorModule(firstPlanet.getxCoordinate(), firstPlanet.getyCoordinate(),
					thirdPlanet.getxCoordinate(), thirdPlanet.getyCoordinate());
			float areaABC = mathService.calculateTriangleArea(ab, bc, ac);

			if (locationService.triangleFormedByPlanetsContainsTheSun(firstPlanet, secondPlanet, thirdPlanet, ab, bc,
					ac, areaABC)) {
				dayWeather.setWeather(Weather.RAINY);
				dayWeather.setAreaTriangle(areaABC);
			}
		}

		return dayWeather;
	}

	@Override
	public DayWeather getDayWithPeakRainfall() {
		return weatherRepository.getDayWithRainPeakDayWeather();
	}
	
	@Override
	public DayWeather getDayWeatherById(Long dayWeatherId) {
		return weatherRepository.getDayWeatherById(dayWeatherId);
	}
	
	@Override
	public DayWeather getDayWeatherByDate(Date dayWeatherDate) {
		return weatherRepository.getDayWeatherByDate(dayWeatherDate);
	}
	
	@Override
	public DayWeather getDayWeatherByDay(Long day) {
		return weatherRepository.getDayWeatherByDay(day);
	}

	@Override
	public Long getAmountOfRainyDays() {
		return weatherRepository.getAmountOfRainyDays();
	}

	@Override
	public Long getAmountOfDryDays() {
		return weatherRepository.getAmountOfDryDays();
	}

	@Override
	public Long getAmountOfOptimalConditionDays() {
		return weatherRepository.getAmountOfOptimalConditionDays();
	}
}
