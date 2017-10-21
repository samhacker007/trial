package trial.stat;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.stat.interval.ConfidenceInterval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ONEAPM on 2016/10/23.
 */
public class HypothesesTest {
    private NormalStatistic a, b;
    private double alpha = 0.05;
    private double beta = 0.2;
    // false = one-tailed  true = two-tailed
    private boolean tailed = true;
    AbstractRealDistribution abstractRealDistribution = null;

    private static final Logger LOG = LoggerFactory.getLogger(HypothesesTest.class);

    public HypothesesTest(NormalStatistic a_a, NormalStatistic a_b, double a_alpha, double a_beta, boolean a_tailed, AbstractRealDistribution a_abstractRealDistribution) {
        a = a_a;
        b = a_b;
        alpha = a_alpha;
        beta = a_beta;
        tailed = a_tailed;
        abstractRealDistribution = a_abstractRealDistribution;
    }

    protected HypothesesTestIndicator performHypothesesTest() {
        try {
            double tStep = Math.sqrt(a.getSampleVariance() / a.getSampleSize() + b.getSampleVariance() / b.getSampleSize());
            double tTest = (a.getSampleMean() - b.getSampleMean()) / tStep;

            double pValue = abstractRealDistribution.cumulativeProbability(tTest);
            return new HypothesesTestIndicator(pValue, 1.0 - alpha);
        } catch (MathIllegalArgumentException e) {
            LOG.error("compute p value has exception");
            return new HypothesesTestIndicator(0.0, 1.0 - alpha);
        }
    }

    protected ConfidenceInterval computeConfidenceInterval() {
        try {
            double tStep = Math.sqrt(a.getSampleVariance() / a.getSampleSize() + b.getSampleVariance() / b.getSampleSize());
            double meanDiff = b.getSampleMean() - a.getSampleMean();
            double interval = getTailedInterval();
            double delta = interval * tStep;
            if (delta < 0.0)
                delta = 0.0;
            return new ConfidenceInterval(meanDiff - delta, meanDiff + delta, 0.5);
        } catch (MathIllegalArgumentException e) {
            LOG.error("compute confidence has exception");
            return new ConfidenceInterval(0.0, 0.0, 0.5);
        }
    }

    private double getTailedInterval() {
        double interval = 0.0;
        if (tailed)
            interval = abstractRealDistribution.inverseCumulativeProbability(1.0 - 0.5 * alpha);
        else
            interval = abstractRealDistribution.inverseCumulativeProbability(1.0 - alpha);
        return interval;
    }

    protected ConfidenceInterval computeImproveRatio() {
        try {
            ConfidenceInterval confidenceInterval = computeConfidenceInterval();
            return new ConfidenceInterval(confidenceInterval.getLowerBound() / a.getSampleMean(), confidenceInterval.getUpperBound() / a.getSampleMean(), confidenceInterval.getConfidenceLevel());
        } catch (Exception e) {
            LOG.error("compute improve ratio, the divisor is zero！");
            return new ConfidenceInterval(0.0, 0.0, 0.5);
        }
    }

    protected double computePower() {
        try {
            double tStep = Math.sqrt(a.getSampleVariance() / a.getSampleSize() + b.getSampleVariance() / b.getSampleSize());
            double tTest = Math.abs(b.getSampleMean() - a.getSampleMean()) / tStep;
            double c = getTailedInterval();
            double power = abstractRealDistribution.cumulativeProbability(tTest - c) + abstractRealDistribution.cumulativeProbability(-c - tTest);
            return power;
        } catch (Exception e) {
            LOG.error("compute power has exception！");
            return 0.0;
        }
    }

    protected int computerSampleSize(double c, int k) {
        try {
            double alphaInterval = getTailedInterval(), betaInterval = abstractRealDistribution.inverseCumulativeProbability(1.0 - beta);
            double interval = (alphaInterval + betaInterval)*(alphaInterval + betaInterval);
            int sampleSize = (int)(interval * (1.0 + 1.0 / k) * a.getSampleVariance() / ((c * a.getSampleMean())*(c * a.getSampleMean())));
            return sampleSize;
        } catch (Exception e) {
            LOG.error("compute sample size has exception！");
            return 0;
        }
    }
}
