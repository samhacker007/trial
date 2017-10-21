package trial.stat;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.interval.ConfidenceInterval;

public class HypothesesResult {
    private static final double ALPHA =  0.05, BETA = 0.2;
    private static final boolean NO_BINARY_METRIC = false;
    private static final boolean TWO_TAILED = true;
    private static final AbstractRealDistribution NORMAL_DISTRIBUTION = new NormalDistribution();
    private HypothesesTest hypothesesTest = null;
    private NormalStatistic normalSA = null;
    private NormalStatistic normalSB = null;

    public HypothesesResult(double[] a, double[] b) {
      normalSA = new NormalStatistic(new StatisticsBase(a), NO_BINARY_METRIC);
      normalSB = new NormalStatistic(new StatisticsBase(b), NO_BINARY_METRIC);
      this.hypothesesTest = new HypothesesTest(normalSA, normalSB, ALPHA, BETA, TWO_TAILED, NORMAL_DISTRIBUTION);
    }
    
    public double getVersionAMean() {
    	return normalSA.getSampleMean();
    }
    
    public double getVersionAVariance() {
    	return normalSA.getSampleVariance();
    }
    
    public int getVersionASize() {
    	return normalSA.getSampleSize();
    }
    
    public double getVersionBMean() {
    	return normalSB.getSampleMean();
    }
    
    public double getVersionBVariance() {
    	return normalSB.getSampleVariance();
    }
    
    public int getVersionBSize() {
    	return normalSB.getSampleSize();
    }

    public double getPValue() {
        return hypothesesTest.performHypothesesTest().getPValue();
    }

    public double getPower() {
        return hypothesesTest.computePower();
    }

    public ConfidenceInterval getConfidenceInterval() {
        return hypothesesTest.computeImproveRatio();
    }

    public double getAvgIncreaments() {
        ConfidenceInterval improveRatio = getConfidenceInterval();
        return (improveRatio.getLowerBound() + improveRatio.getUpperBound()) / 2.0;
    }

    public boolean isPValueSmallEnough() {
        return (getPValue() > (1.0 - ALPHA));
    }

    public boolean isPowerBigEnough() {
        return (getPower() > (1.0 - BETA));
    }
}
