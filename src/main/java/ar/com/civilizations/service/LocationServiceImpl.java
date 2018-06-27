package ar.com.civilizations.service;

import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

import ar.com.civilizations.model.Planet;

@Service
public class LocationServiceImpl implements LocationService {
	private MathService mathService;
	
	@Inject
	public LocationServiceImpl(MathService mathService) {
		this.mathService = mathService;
	}

	/**
	 * Calculate cartesian x and y coordinates using Pitagoras
	 */
	@Override
	public void updatePlanetCartesianCoordinates(Planet planet) {
		planet.setxCoordinate((float) Math.cos(Math.toRadians(planet.getAngle())) * planet.getDistanceToSunInKm());
		planet.setyCoordinate((float) Math.sin(Math.toRadians(planet.getAngle())) * planet.getDistanceToSunInKm());
	}

	/**
	 * 
	 * 
	 * @param firstPlanet
	 * @param secondPlanet
	 * @param thirdPlanet
	 */
	@Override
	public boolean arePlanetsAligned(Planet firstPlanet, Planet secondPlanet, Planet thirdPlanet) {
		float distanceAB = mathService.calculateVectorModule(firstPlanet.getxCoordinate(), firstPlanet.getyCoordinate(),
				secondPlanet.getxCoordinate(), secondPlanet.getyCoordinate());
		float distanceBC = mathService.calculateVectorModule(secondPlanet.getxCoordinate(), secondPlanet.getyCoordinate(),
				thirdPlanet.getxCoordinate(), thirdPlanet.getyCoordinate());
		float distanceAC = mathService.calculateVectorModule(firstPlanet.getxCoordinate(), firstPlanet.getyCoordinate(),
				thirdPlanet.getxCoordinate(), thirdPlanet.getyCoordinate());
		return Math.abs(distanceAB + distanceBC - distanceAC) <= mathService.getEpsilon();
	}

	/**
	 * 
	 * @param firstPlanet
	 * @param secondPlanet
	 * @param thirdPlanet
	 * @param a vector formed by the first planet and the second planet
	 * @param b vector formed by the second planet and the third planet
	 * @param c vector formed by the third planet and the first planet
	 * @return
	 */
	@Override
	public boolean triangleFormedByPlanetsContainsTheSun(Planet firstPlanet, Planet secondPlanet, Planet thirdPlanet,
			float ab, float bc, float ac, float areaABC) {
		// Point 0,0 is the Sun. Calculating vectors from each planet to Sun
		float as = mathService.calculateVectorModule(firstPlanet.getxCoordinate(), firstPlanet.getyCoordinate(), 0, 0);
		float bs = mathService.calculateVectorModule(secondPlanet.getxCoordinate(), secondPlanet.getyCoordinate(), 0, 0);
		float cs = mathService.calculateVectorModule(thirdPlanet.getxCoordinate(), thirdPlanet.getyCoordinate(), 0, 0);
		
		// Calculating area of triangles formed by Sun and planets
		float areaFSS = mathService.calculateTriangleArea(as, bs, ab);
		float areaFTS = mathService.calculateTriangleArea(as, cs, ac);
		float areaSTS = mathService.calculateTriangleArea(bs, cs, bc);
		
		// If total area is equals to the three new areas, sun is contained in the triangle
		float areaSUM = areaFSS + areaFTS + areaSTS;
		
		return Math.abs(areaSUM - areaABC) <= mathService.getEpsilon();
	}

	/**
	 * First, considering that the formulae for a line is: y = ax + b, where a is the slope, and b 
	 * is the y-intercept. 
	 * 
	 * We calculate the slope: a = YFP - YSP / XFP - XSP
	 * 
	 * Then the y-intercept: YV = a*XV + b => YV - a*XV = b
	 * 
	 * @param
	 * @param
	 */
	@Override
	public boolean arePlanetsAlignedWithTheSun(Planet firstPlanet, Planet secondPlanet) {
		float slope = (firstPlanet.getyCoordinate() - secondPlanet.getyCoordinate())
				/ (firstPlanet.getxCoordinate() - secondPlanet.getxCoordinate());
		float yIntercept = firstPlanet.getyCoordinate() - slope * firstPlanet.getxCoordinate();
		return Math.abs(yIntercept) <= mathService.getEpsilon();
	}

}
