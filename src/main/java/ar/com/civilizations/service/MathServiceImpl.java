package ar.com.civilizations.service;

import org.jvnet.hk2.annotations.Service;

/**
 * Implementation that does math calculation 
 * @author gaston.marchetta
 *
 */
@Service
public class MathServiceImpl implements MathService {
	/**
	 * Max allowed error for comparison purposes
	 */
	// TODO: move to properties file so this is configurable
	private static final Float EPSILON = 0.01f;

	/**
	 * Method that calculates the vector module using two points
	 */
	@Override
	public float calculateVectorModule(float ax, float ay, float bx, float by) {
		return (float) Math.sqrt(Math.pow(bx - ax, 2) + Math.pow(by - ay, 2));
	}

	private float calculateSemiPerimeter(float a, float b, float c) {
		return (a + b + c) / 2;
	}

	/**
	 * Method that calculates the triangle area using semiperimeter and triangle sides
	 */
	@Override
	public float calculateTriangleArea(float vectorA, float vectorB, float vectorC) {
		float semiperimeter = calculateSemiPerimeter(vectorA, vectorB, vectorC);
		return (float) Math.sqrt(
				semiperimeter * (semiperimeter - vectorA) * (semiperimeter - vectorB) * (semiperimeter - vectorC));
	}

	@Override
	public float getEpsilon() {
		return EPSILON;
	}
}
