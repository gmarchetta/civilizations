package ar.com.civilizations.model;

public class Planet {
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

}
