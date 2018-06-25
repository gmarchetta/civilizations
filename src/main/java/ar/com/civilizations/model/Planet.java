package ar.com.civilizations.model;

public class Planet implements Comparable<Planet> {
	private String name;
	private float dailyAngleVariation;
	private float distanceToSunInKm;
	private float angle;
	private Float xCoordinate;
	private Float yCoordinate;

	public float getDistanceToSunInKm() {
		return distanceToSunInKm;
	}

	public void setDistanceToSunInKm(float distanceToSunInKm) {
		this.distanceToSunInKm = distanceToSunInKm;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
		this.xCoordinate = null;
		this.yCoordinate = null;
	}

	public Float getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(Float xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public Float getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(Float yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDailyAngleVariation() {
		return dailyAngleVariation;
	}

	public void setDailyAngleVariation(float dailyAngleVariation) {
		this.dailyAngleVariation = dailyAngleVariation;
	}
	
	@Override
	public int compareTo(Planet otherPlanet) {
	    return Float.compare(this.getDistanceToSunInKm(), otherPlanet.getDistanceToSunInKm());
	}
}
