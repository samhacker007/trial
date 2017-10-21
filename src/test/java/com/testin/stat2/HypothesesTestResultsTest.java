package com.testin.stat2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import trial.stat2.hypothesestest.HypothesesTestResults;

public class HypothesesTestResultsTest {

	@Test
	public void testHypothesesResultTest() {
		double[] a = { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1,
				2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
		double[] b = { 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1,
				1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1,
				1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1,
				1.9, 1.1, 1.9, 1.1, 1.9 };

		HypothesesTestResults hypothesesResult = new HypothesesTestResults(a, b);

		double pValue = hypothesesResult.getPValue();
		double power = hypothesesResult.getPower();
		double[] confidenceIntervalBounds = hypothesesResult.getConfidenceIntervalBounds();
		double improveRatioLowBound = confidenceIntervalBounds[0];
		double improveRatioUpperBound = confidenceIntervalBounds[1];
		double avgIncrement = hypothesesResult.getAvgIncreaments();
		boolean smallEnough = hypothesesResult.isPValueSmallEnough();
		boolean bigEnough = hypothesesResult.isPowerBigEnough();
		double versionAMean = hypothesesResult.getVersionAMean();
		double versionAVariance = hypothesesResult.getVersionAVariance();
		int versionASize = hypothesesResult.getVersionASize();
		double versionBMean = hypothesesResult.getVersionBMean();
		double versionBVariance = hypothesesResult.getVersionBVariance();
		int versionBSize = hypothesesResult.getVersionBSize();

		assertEquals(versionAMean, 1.5, 0.01);
		assertEquals(versionAVariance, 0.256, 0.01);
		assertEquals(versionASize, 40, 0);
		assertEquals(versionBMean, 1.5, 0.01);
		assertEquals(versionBVariance, 0.164, 0.01);
		assertEquals(versionBSize, 40, 0.01);
		assertEquals(pValue, 0.5, 0.01);
		assertEquals(power, 0.05, 0.01);
		assertEquals(improveRatioLowBound, -0.134, 0.01);
		assertEquals(improveRatioUpperBound, 0.134, 0.01);
		assertEquals(avgIncrement, 0, 0.01);
		assertFalse("统计不显著，接受原假设，认为没有区别", smallEnough);
		assertFalse(
				"当前试验统计功效不够高，因此无足够统计支持以推翻原假设（即两个样本集无明显差异的假设）。仅当计算得到的P-Value足够推翻原假设时，我们才会进一步计算统计功效以观测目前是否有足够的统计支持以推翻原假设。",
				bigEnough);
	}

	@Test
	public void testNonObvious() {
		// from sample6 in
		// http://wenku.baidu.com/link?url=Wi_Yi-waJ2YrrVrDY2suQxVAIeoyG293vDCNYheSJffwOaNS_a-F79tpRxv6okdJpGwECeDdd3_28S74qZ-McHpya6r7EXzLvng2UYsfg0S
		double[] a = { 140, 138, 143, 142, 144, 137, 141 };
		double[] b = { 135, 140, 142, 136, 138, 140 };

		HypothesesTestResults hypothesesResult = new HypothesesTestResults(a, b);

		double pValue = hypothesesResult.getPValue();
		double testValue = hypothesesResult.getTestValue();
		double power = hypothesesResult.getPower();
		double[] confidenceIntervalBounds = hypothesesResult.getConfidenceIntervalBounds();
		double improveRatioLowBound = confidenceIntervalBounds[0];
		double improveRatioUpperBound = confidenceIntervalBounds[1];
		double avgIncrement = hypothesesResult.getAvgIncreaments();
		boolean smallEnough = hypothesesResult.isPValueSmallEnough();
		boolean bigEnough = hypothesesResult.isPowerBigEnough();
		double versionAMean = hypothesesResult.getVersionAMean();
		double versionAVariance = hypothesesResult.getVersionAVariance();
		int versionASize = hypothesesResult.getVersionASize();
		double versionBMean = hypothesesResult.getVersionBMean();
		double versionBVariance = hypothesesResult.getVersionBVariance();
		int versionBSize = hypothesesResult.getVersionBSize();

		assertEquals(versionAMean, 140.71, 0.01);
		assertEquals(versionAVariance, 6.57, 0.01);
		assertEquals(versionASize, 7, 0);
		assertEquals(versionBMean, 138.5, 0.01);
		assertEquals(versionBVariance, 7.1, 0.01);
		assertEquals(versionBSize, 6, 0);
		assertEquals(pValue, 0.064, 0.01);
		assertEquals(testValue, -1.522, 0.01);
		assertEquals(power, 0.33, 0.01);
		assertEquals(improveRatioLowBound, -0.036, 0.01);
		assertEquals(improveRatioUpperBound, 0.005, 0.01);
		assertEquals(avgIncrement, -0.015, 0.01);
		assertFalse("统计不显著，接受原假设，认为没有区别", smallEnough);
		assertFalse(
				"当前试验统计功效不够高，因此无足够统计支持以推翻原假设（即两个样本集无明显差异的假设）。仅当计算得到的P-Value足够推翻原假设时，我们才会进一步计算统计功效以观测目前是否有足够的统计支持以推翻原假设。",
				bigEnough);
	}

	@Test
	public void testObvious() {
		// from sample5.2 in
		// http://wenku.baidu.com/link?url=myCRMI8KmmSUq04l0VQnOcudskZEI62fjK6lTs3YyrRBqcfWtxYOqhBiY5bhtpGqZlr_3vnHmn2pBdvZMrRwwo2iGjxVYiC_PR-N4ORZW33
		double[] a = { 8.4, 10.5, 12.0, 12.0, 13.9, 15.3, 16.7, 18.0, 18.7,
				20.7, 21.1, 15.2 };
		double[] b = { 5.4, 6.4, 6.4, 7.5, 7.6, 8.1, 11.6, 12.0, 13.4, 13.5,
				14.8, 15.6, 18.7 };

		HypothesesTestResults hypothesesResult = new HypothesesTestResults(a, b);

		double pValue = hypothesesResult.getPValue();
		double testValue = hypothesesResult.getTestValue();
		double power = hypothesesResult.getPower();
		double[] confidenceIntervalBounds = hypothesesResult.getConfidenceIntervalBounds();
		double improveRatioLowBound = confidenceIntervalBounds[0];
		double improveRatioUpperBound = confidenceIntervalBounds[1];
		double avgIncrement = hypothesesResult.getAvgIncreaments();
		boolean smallEnough = hypothesesResult.isPValueSmallEnough();
		boolean bigEnough = hypothesesResult.isPowerBigEnough();
		double versionAMean = hypothesesResult.getVersionAMean();
		double versionAVariance = hypothesesResult.getVersionAVariance();
		int versionASize = hypothesesResult.getVersionASize();
		double versionBMean = hypothesesResult.getVersionBMean();
		double versionBVariance = hypothesesResult.getVersionBVariance();
		int versionBSize = hypothesesResult.getVersionBSize();

		assertEquals(versionAMean, 15.20, 0.01);
		assertEquals(versionAVariance, 16.17, 0.01);
		assertEquals(versionASize, 12, 0);
		assertEquals(versionBMean, 10.84, 0.01);
		assertEquals(versionBVariance, 17.82, 0.01);
		assertEquals(versionBSize, 13, 0);
		assertEquals(pValue, 0.004, 0.01);
		assertEquals(testValue, -2.652, 0.01);
		assertEquals(power, 0.754, 0.01);
		assertEquals(improveRatioLowBound, -0.499, 0.01);
		assertEquals(improveRatioUpperBound, -0.074, 0.01);
		assertEquals(avgIncrement, -0.286, 0.01);
		assertTrue("统计显著，推翻接受原假设，认为有显著区别", smallEnough);
		assertFalse(
				"当前试验统计功效不够高，因此无足够统计支持以推翻原假设（即两个样本集无明显差异的假设）。仅当计算得到的P-Value足够推翻原假设时，我们才会进一步计算统计功效以观测目前是否有足够的统计支持以推翻原假设。",
				bigEnough);
	}

}
