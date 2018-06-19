package ar.com.civilizations.prediction;

import ar.com.civilizations.model.Planet;

public interface WeatherForecaster {
	boolean isDryDay(Planet ferengi, Planet betasoide, Planet vulcano);
	
	boolean isRainyDay(Planet ferengi, Planet betasoide, Planet vulcano);
	
	boolean isRainPeak(Planet ferengi, Planet betasoide, Planet vulcano);
	
	boolean hasOptimalPressureAndTemperature(Planet ferengi, Planet betasoide, Planet vulcano);
}
