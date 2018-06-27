package ar.com.civilizations.service;

/**
 * Class containing logic to solve math problems
 * @author gaston.marchetta
 *
 */
public interface MathService {
	/**
	 * Calculates the vector module formed by two points
	 * @param ax
	 * @param ay
	 * @param bx
	 * @param by
	 * @return vector module
	 */
	float calculateVectorModule(float ax, float ay, float bx, float by);
	
	/**
	 * Calculates the triangle area based on its three sides
	 * 
	 * @param vectorA
	 * @param vectorB
	 * @param vectorC
	 * @return triangle area
	 */
	float calculateTriangleArea(float vectorA, float vectorB, float vectorC);

	/**
	 * Returns the max allowed error for comparison purposes
	 * @return epsilon
	 */
	float getEpsilon();
	
}
