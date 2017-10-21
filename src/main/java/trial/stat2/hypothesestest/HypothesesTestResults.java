package trial.stat2.hypothesestest;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.interval.ConfidenceInterval;

import trial.stat2.DistributionStatistics;
import trial.stat2.NormalDistributionStatistics;
import trial.stat2.StatisticsBase;
import trial.stat2.StatisticsConstants;
import trial.stat2.StatisticsUtil;

public class HypothesesTestResults implements StatisticsConstants {

	// Default we use NormalDistribution for using Z-Test. We also could use
	// other distribution, like using TDistribution for T-Test
	private static final AbstractRealDistribution DISTRIBUTION = new NormalDistribution();
	private HypothesesTest hypothesesTest = null;
	private DistributionStatistics disStatA = null;
	private DistributionStatistics disStatB = null;
	private double alpha;
	private double beta;

	// Use default alpha, beta value
	public HypothesesTestResults(double[] a, double[] b) {
		// Use NormalDistributionStatistics for Z-Test default
		disStatA = new NormalDistributionStatistics(new StatisticsBase(a),
				BINARY_METRIC_DEFAULT);
		disStatB = new NormalDistributionStatistics(new StatisticsBase(b),
				BINARY_METRIC_DEFAULT);
		this.hypothesesTest = new HypothesesTest(disStatA, disStatB,
				ALPHA_DEFAULT, BETA_DEFAULT, TWO_TAILED_DEFAULT, DISTRIBUTION);
		this.alpha = ALPHA_DEFAULT;
		this.beta = BETA_DEFAULT;
	}

	public double getAlpha() {
		return this.alpha;
	}

	public double getBeta() {
		return this.beta;
	}

	public double getVersionAMean() {
		return disStatA.getSampleMean();
	}

	public double getVersionAVariance() {
		return disStatA.getSampleVariance();
	}

	public int getVersionASize() {
		return disStatA.getSampleSize();
	}

	public double getVersionBMean() {
		return disStatB.getSampleMean();
	}

	public double getVersionBVariance() {
		return disStatB.getSampleVariance();
	}

	public int getVersionBSize() {
		return disStatB.getSampleSize();
	}

	public double getPValue() {
		return hypothesesTest.performHypothesesTest().getPValue();
	}

	public double getTestValue() {
		return hypothesesTest.computeStatisticTestValueByPValue();
	}

	public double getPower() {
		return hypothesesTest.computePower();
	}

	public double[] getConfidenceIntervalBounds() {
		ConfidenceInterval confidenceInterval;
		double[] bounds = { StatisticsUtil.formatDouble(0.00),
				StatisticsUtil.formatDouble(0.00) };
		try {
			confidenceInterval = hypothesesTest
					.computeConfidenceIntervalImprovementRatio();
			bounds[0] = StatisticsUtil.formatDouble(confidenceInterval
					.getLowerBound());
			bounds[1] = StatisticsUtil.formatDouble(confidenceInterval
					.getUpperBound());
		} catch (Exception e) {
			// TODO Add log here
		} finally {
			return bounds;
		}
	}

	// avg increaments of confidence interval
	public double getAvgIncreaments() {
		try {
			double[] confidenceIntervalBounds = getConfidenceIntervalBounds();
			double improveRatioLowBound = confidenceIntervalBounds[0];
			double improveRatioUpperBound = confidenceIntervalBounds[1];
			return StatisticsUtil
					.formatDouble((improveRatioLowBound + improveRatioUpperBound) / 2.0);
		} catch (Exception e) {
			return StatisticsUtil.formatDouble(0.00);
		}
	}

	public boolean isPValueSmallEnough() {
		return (getPValue() < this.alpha);
	}

	public boolean isPowerBigEnough() {
		return (getPower() > (1.0 - this.beta));
	}
}
