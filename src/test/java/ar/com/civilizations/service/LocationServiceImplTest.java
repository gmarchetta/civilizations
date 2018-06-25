package ar.com.civilizations.service;

import org.junit.Before;
import org.junit.Test;

import ar.com.civilizations.model.Planet;
import junit.framework.Assert;

public class LocationServiceImplTest {
	private LocationService locationService;

	// @Mock
	private MathService mathService;

	@Before
	public void setUp() {
		locationService = new LocationServiceImpl(mathService);
	}

	/**
	 * Testing update cartesian coordinates for a planet. If angle is 0, point
	 * is on the x axis (y = 0), and x is equals to the distance to the sun
	 */
	@Test
	public void testUpdatePlanetCartesianCoordinatesPlanetOnXAxis() {
		Planet planet = new Planet();
		planet.setAngle(0);
		planet.setDailyAngleVariation(5);
		planet.setDistanceToSunInKm(500);

		locationService.updatePlanetCartesianCoordinates(planet);

		Assert.assertEquals(0f, planet.getyCoordinate());
		Assert.assertEquals(500f, planet.getxCoordinate());
	}

	/**
	 * Testing update cartesian coordinates for a planet. If angle is 90, point
	 * is on the y axis (x = 0), and y is equals to the distance to the sun
	 */
	@Test
	public void testUpdatePlanetCartesianCoordinatesPlanetOnYAxis() {
		Planet planet = new Planet();
		planet.setAngle(90);
		planet.setDailyAngleVariation(5);
		planet.setDistanceToSunInKm(500);

		locationService.updatePlanetCartesianCoordinates(planet);

		Assert.assertEquals(500f, planet.getyCoordinate());
		Assert.assertEquals(0f, planet.getxCoordinate());
	}

	@Test
	public void testArePlanetsAligned() {
		locationService.arePlanetsAligned(firstPlanet, secondPlanet, thirdPlanet);
	}

	@Test
	public void testTriangleFormedByPlanetsContainTheSun() {
		locationService.triangleFormedByPlanetsContainsTheSun(firstPlanet, secondPlanet, thirdPlanet, ab, bc, ac, areaABC);
	}

	@Test
	public void testArePlanetsAlignedWithTheSun() {
		locationService.arePlanetsAlignedWithTheSun(firstPlanet, secondPlanet);
	}
}