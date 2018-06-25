package ar.com.civilizations.service;

public interface MathService {
	float calculateVectorModule(float ax, float ay, float bx, float by);
	
	float calculateTriangleArea(float vectorA, float vectorB, float vectorC);

	float getEpsilon();
	
}
