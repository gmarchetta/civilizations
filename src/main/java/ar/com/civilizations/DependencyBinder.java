package ar.com.civilizations;

import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import ar.com.civilizations.prediction.PlanetAlignmentForecaster;
import ar.com.civilizations.prediction.WeatherForecaster;
import ar.com.civilizations.repository.WeatherRepository;
import ar.com.civilizations.repository.WeatherRepositoryImpl;
import ar.com.civilizations.service.GalaxyService;
import ar.com.civilizations.service.GalaxyServiceImpl;
import ar.com.civilizations.service.LocationService;
import ar.com.civilizations.service.LocationServiceImpl;
import ar.com.civilizations.service.MathService;
import ar.com.civilizations.service.MathServiceImpl;

@ApplicationPath("/api")
public class DependencyBinder extends AbstractBinder {
	@Override
	protected void configure() {
		bind(GalaxyServiceImpl.class).to(GalaxyService.class);
		bind(PlanetAlignmentForecaster.class).to(WeatherForecaster.class);
		bind(LocationServiceImpl.class).to(LocationService.class);
		bind(MathServiceImpl.class).to(MathService.class);
		bind(WeatherRepositoryImpl.class).to(WeatherRepository.class);
	}
}
