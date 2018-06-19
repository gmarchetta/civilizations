package ar.com.civilizations.service;

import ar.com.civilizations.model.Planet;

public interface LocationService {
	void updatePlanetCartesianCoordinates(Planet planet);
	
	boolean arePlanetsAligned(Planet firstPlanet, Planet secondPlanet, Planet thirdPlanet);
	
	float triangleArea(Planet firstPlanet, Planet secondPlanet, Planet thirdPlanet);
}
