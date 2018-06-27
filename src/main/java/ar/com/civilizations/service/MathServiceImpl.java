package ar.com.civilizations.service;

import org.jvnet.hk2.annotations.Service;

@Service
public class MathServiceImpl implements MathService {
	private static final Float EPSILON = 0.01f;

	@Override
	public float calculateVectorModule(float ax, float ay, float bx, float by) {
		return (float) Math.sqrt(Math.pow(bx - ax, 2) + Math.pow(by - ay, 2));
	}

	private float calculateSemiPerimeter(float a, float b, float c) {
		return (a + b + c) / 2;
	}

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
