package trial.stat2.hypothesestest;

import java.text.DecimalFormat;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.stat.interval.ConfidenceInterval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import trial.stat2.DistributionStatistics;
import trial.stat2.StatisticsConstants;
import trial.stat2.StatisticsUtil;

public class HypothesesTest implements StatisticsConstants {
	private DistributionStatistics a, b;
	private double alpha;
	private double beta;
	private boolean tailed;
	AbstractRealDistribution abstractRealDistribution = null;

	private static final Logger LOG = LoggerFactory
			.getLogger(HypothesesTest.class);

	public HypothesesTest(DistributionStatistics a_a,
			DistributionStatistics a_b, double a_alpha, double a_beta,
			boolean a_tailed,
			AbstractRealDistribution a_abstractRealDistribution) {
		a = a_a;
		b = a_b;
		alpha = a_alpha;
		beta = a_beta;
		tailed = a_tailed;
		abstractRealDistribution = a_abstractRealDistribution;
	}

	/**
	 * compute the pvalue, which could be get from HypothesesTestIndicator
	 * instance.
	 * 
	 * @return HypothesesTestIndicator
	 */
	protected HypothesesTestIndicator performHypothesesTest() {
		try {
			// compute the square root
			double testStep = Math.sqrt(a.getSampleVariance()
					/ a.getSampleSize() + b.getSampleVariance()
					/ b.getSampleSize());
			// compute the T-Test value
			double testValue = (a.getSampleMean() - b.getSampleMean())
					/ testStep;
			// compute the pvalue which means the possibility of no difference,
			// using the test value
			double pValue = 1 - abstractRealDistribution
					.cumulativeProbability(Math.abs(testValue));
			return new HypothesesTestIndicator(StatisticsUtil.formatDouble(pValue), StatisticsUtil.formatDouble(1.0 - alpha));
		} catch (MathIllegalArgumentException e) {
			LOG.error("compute p value has exception");
			return new HypothesesTestIndicator(StatisticsUtil.formatDouble(0.0), StatisticsUtil.formatDouble(1.0 - alpha));
		}
	}

	/**
	 * compute the confidence interval
	 * 
	 * @return ConfidenceInterval
	 */
	protected ConfidenceInterval computeConfidenceInterval() {
		try {
			// compute the square root
			double testStep = Math.sqrt(a.getSampleVariance()
					/ a.getSampleSize() + b.getSampleVariance()
					/ b.getSampleSize());
			double meanDiff = b.getSampleMean() - a.getSampleMean();
			double statTestValue = computeStatisticTestValueBySignificanceLevel();
			double delta = statTestValue * testStep;
			if (delta < 0.0)
				delta = 0.0;
			return new ConfidenceInterval(StatisticsUtil.formatDouble(meanDiff - delta), StatisticsUtil.formatDouble(meanDiff + delta),
					StatisticsUtil.formatDouble(1.0 - alpha));
		} catch (MathIllegalArgumentException e) {
			LOG.error("compute confidence has exception");
			return new ConfidenceInterval(StatisticsUtil.formatDouble(0.0), StatisticsUtil.formatDouble(0.0), StatisticsUtil.formatDouble(1.0 - alpha));
		}
	}

	/**
	 * compute the the t-test/z-test/other-test value using significance level
	 * 
	 * @return double
	 */
	private double computeStatisticTestValueBySignificanceLevel() {
		double interval = 0.0;
		if (tailed)
			// compute the t-test/z-test/other-test value, inversely using the
			// significance level
			interval = abstractRealDistribution
					.inverseCumulativeProbability(1.0 - 0.5 * alpha);
		else
			interval = abstractRealDistribution
					.inverseCumulativeProbability(1.0 - alpha);
		return StatisticsUtil.formatDouble(interval);
	}

	/**
	 * compute the the t-test/z-test/other-test value by pvalue
	 * 
	 * @return double
	 */
	protected double computeStatisticTestValueByPValue() {
		double interval = abstractRealDistribution
				.inverseCumulativeProbability(performHypothesesTest()
						.getPValue());
		return StatisticsUtil.formatDouble(interval);
	}

	/**
	 * Computing the confidence interval improvement ratio
	 * 
	 * @return
	 */
	protected ConfidenceInterval computeConfidenceIntervalImprovementRatio() {
		try {
			ConfidenceInterval confidenceInterval = computeConfidenceInterval();
			return new ConfidenceInterval(StatisticsUtil.formatDouble(confidenceInterval.getLowerBound()
					/ a.getSampleMean()), StatisticsUtil.formatDouble(confidenceInterval.getUpperBound()
					/ a.getSampleMean()),
					StatisticsUtil.formatDouble(confidenceInterval.getConfidenceLevel()));
		} catch (Exception e) {
			LOG.error("Encountered error when computing the confidence interval improvement ratio:"
					+ e);
			return new ConfidenceInterval(StatisticsUtil.formatDouble(0.0), StatisticsUtil.formatDouble(0.0), StatisticsUtil.formatDouble(1.0 - alpha));
		}
	}

	/**
	 * Compute power value
	 * 
	 * @return double
	 */
	protected double computePower() {
		try {
			double tStep = Math.sqrt(a.getSampleVariance() / a.getSampleSize()
					+ b.getSampleVariance() / b.getSampleSize());
			double tTest = Math.abs(b.getSampleMean() - a.getSampleMean())
					/ tStep;
			double c = computeStatisticTestValueBySignificanceLevel();
			double power = abstractRealDistribution.cumulativeProbability(tTest
					- c)
					+ abstractRealDistribution
							.cumulativeProbability(-c - tTest);
			return StatisticsUtil.formatDouble(power);
		} catch (Exception e) {
			LOG.error("compute power has exception！");
			return StatisticsUtil.formatDouble(0.0);
		}
	}
}
