package ar.com.civilizations.service;

import org.junit.Test;

import junit.framework.Assert;

public class MathServiceImplTest {
	private MathService mathService = new MathServiceImpl();
	
	/**
	 * Vector module formulae: sqrt(pow(x2 - x1, 2) + (pow(y2 - y1, 2))
	 * 
	 * sqrt(pow(-3 - 2, 2) + (pow(2 - 1, 2)) = sqrt(26)
	 */
	@Test
	public void testCalculateVectorModule() {
		float ax = 2;
		float ay = 1;
		float bx = -3;
		float by = 2;
		
		float vectorModule = mathService.calculateVectorModule(ax, ay, bx, by);
		
		Assert.assertEquals(Math.sqrt(26), vectorModule, 0.00001);
	}
	
	/**
	 * Having semiperimeter: s = (3 + 2 + 4) / 2 = 4.5
	 * 
	 * Area = sqrt(s * (s-a) * (s-b) * (s-c)) = sqrt(4.5 * (4.5-3) * (4.5-2) * (4.5-4)
	 * 
	 * Area = 2.9
	 */
	@Test
	public void calculateTriangleArea() {
		float vectorA = 3;
		float vectorB = 2;
		float vectorC = 4;
		
		float triangleArea = mathService.calculateTriangleArea(vectorA, vectorB, vectorC);
		Assert.assertEquals(2.9, triangleArea, 0.01);
	}
}
