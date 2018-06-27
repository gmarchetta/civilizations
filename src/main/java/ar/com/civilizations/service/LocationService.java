package ar.com.civilizations.service;

import ar.com.civilizations.model.Planet;

/**
 * Service providing logic to determine planets alignment and location in the
 * galaxy
 * 
 * @author gmarchetta
 *
 */
public interface LocationService {
	/**
	 * Method that uses the planet angle and distance to sun to calculate its
	 * cartesian coordinates in the galaxy, and updates x and y coordinates of the
	 * planet
	 * 
	 * @param planet
	 */
	void updatePlanetCartesianCoordinates(Planet planet);

	/**
	 * Method that calculates if the three planets are aligned
	 * 
	 * @param firstPlanet
	 * @param secondPlanet
	 * @param thirdPlanet
	 * @return boolean value defining if planets are aligned
	 */
	boolean arePlanetsAligned(Planet firstPlanet, Planet secondPlanet, Planet thirdPlanet);

	/**
	 * Method that calculates if the two planets are aligned with the sun. It's
	 * expected to be used together with arePlanetsAligned to define if all three
	 * planets and sun are aligned
	 * 
	 * @param firstPlanet
	 * @param secondPlanet
	 * @return if planets are aligned with the sun
	 */
	boolean arePlanetsAlignedWithTheSun(Planet firstPlanet, Planet secondPlanet);

	/**
	 * Method that calculates if the sun is contained by the triangle formed by the planets
	 * @param firstPlanet
	 * @param secondPlanet
	 * @param thirdPlanet
	 * @param ab distance between first and second planet
	 * @param bc distance between second and third planet
	 * @param ac distance between first and third planet
	 * @param areaABC area of the triangle formed by firstPlanet, secondPlanet and thirdPlanet
	 * @return if triangle contains the sun
	 */
	boolean triangleFormedByPlanetsContainsTheSun(Planet firstPlanet, Planet secondPlanet, Planet thirdPlanet, float ab,
			float bc, float ac, float areaABC);
}
