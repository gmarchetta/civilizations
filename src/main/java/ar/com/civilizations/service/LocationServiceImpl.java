package ar.com.civilizations.service;

import ar.com.civilizations.model.Planet;

public class LocationServiceImpl implements LocationService {

	/**
	 * Calculate cartesian x and y coordinates using Pitagoras
	 */
	@Override
	public void updatePlanetCartesianCoordinates(Planet planet) {
		planet.setxCoordinate((float) Math.cos(planet.getAngle()) * planet.getDistanceToSunInKm());
		planet.setyCoordinate((float) Math.sin(planet.getAngle()) * planet.getDistanceToSunInKm());
	}

	/**
	 * Calculate if vectors formed by the planet coordinates have the same
	 * direction, in which case planets are aligned
	 */
	@Override
	public boolean arePlanetsAligned(Planet firstPlanet, Planet secondPlanet, Planet thirdPlanet) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Calculate area of the triangle formed by the three planets
	 */
	@Override
	public float triangleArea(Planet firstPlanet, Planet secondPlanet, Planet thirdPlanet) {
		// TODO Auto-generated method stub
		return 0;
	}

}
