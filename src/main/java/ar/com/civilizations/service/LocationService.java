package ar.com.civilizations.service;

import ar.com.civilizations.model.Planet;

/**
 * Service providing logic to determine planets alignment and location in the galaxy
 * 
 * @author gmarchetta
 *
 */
public interface LocationService {
	/**
	 * 
	 * @param planet
	 */
	void updatePlanetCartesianCoordinates(Planet planet);
	
	boolean arePlanetsAligned(Planet firstPlanet, Planet secondPlanet, Planet thirdPlanet);
	
	boolean arePlanetsAlignedWithTheSun(Planet firstPlanet, Planet secondPlanet);
	
	boolean triangleFormedByPlanetsContainsTheSun(Planet firstPlanet, Planet secondPlanet, Planet thirdPlanet, float ab,
			float bc, float ac, float areaABC);
}
