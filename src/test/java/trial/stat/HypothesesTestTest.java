package trial.stat;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.interval.ConfidenceInterval;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ONEAPM on 2016/10/23.
 */
public class HypothesesTestTest {

    public static final double ALPHA =  0.05, NO_BETA = 0.0, BETA = 0.2; // no_beta means that the test could not hava beta
    public static final double DEVIATION = 0.01;

    public static final boolean BINARY_METRIC = true;
    public static final boolean NO_BINARY_METRIC = false;
    public static final boolean TWO_TAILED = true;
    public static final boolean ONE_TAILED = false;

    public static final int SAMPLE_SIZE_A1 = 2000;
    public static final int SAMPLE_SIZE_B1 = 3000;
    public static final double SAMPLE_MEAN_A1 = 0.067;
    public static final double SAMPLE_MEAN_B1 = 0.055;
    public static final double SAMPLE_VARIANCE_A1 = 0.0056;
    public static final double SAMPLE_VARIANCE_B1 = 0.0042;
    public static final double EXPECTED_P_VALUE1 = 0.9574;

    public static final int SAMPLE_SIZE_A2 = 40725;
    public static final int SAMPLE_SIZE_B2 = 40436;
    public static final double SAMPLE_MEAN_A2 = 133.4119;
    public static final double SAMPLE_MEAN_B2 = 176.1126;
    public static final double SAMPLE_VARIANCE_A2 = 69.0861 * 69.0861;
    public static final double SAMPLE_VARIANCE_B2 = 91.6116 * 91.6116;
    public static final double EXPECTED_P_VALUE2 = 0.0;
    public static final double EXPECTED_CONFIDENCE_LOW_BOUND = 0.31;
    public static final double EXPECTED_CONFIDENCE_UPPER_BOUND = 0.32;
    public static final double EXPECTED_POWER = 1.0;

    public static final int SAMPLE_SIZE_3 = 1000;
    public static final int SAMPLE_MEAN_3 = 5;
    public static final int SAMPLE_VARIANCE_3 = 10 * 10;
    public static final double DIFF_RATIO = 1.0;
    public static final int DEVIATION_SIZE = 5;
    public static final int EXPECTED_SIZE = 63;
    public static final int SIZEB_SIZEA_RATIO = 1;

    public static final int SAMPLE_SIZE_A4 = 63;
    public static final int SAMPLE_SIZE_B4 = 63;
    public static final int SAMPLE_MEAN_A4 = 5;
    public static final int SAMPLE_MEAN_B4 = 10;
    public static final int SAMPLE_VARIANCE_A4 = 100;
    public static final int SAMPLE_VARIANCE_B4 = 100;
    public static final double EXPECTED_POWER4 = 0.80;

    public static final int SAMPLE_SIZE_A5 = 120;
    public static final int SAMPLE_SIZE_B5 = 120;
    public static final double SAMPLE_MEAN_A5 = 132.86;
    public static final double SAMPLE_MEAN_B5 = 127.44;
    public static final double SAMPLE_VARIANCE_A5 = 15.34 * 15.34;
    public static final double SAMPLE_VARIANCE_B5 = 18.23 * 18.23;
    public static final double EXPECTED_POWER5 = 0.80;

    public static final int SAMPLE_SIZE_A6 = 70;
    public static final int SAMPLE_SIZE_B6 = 70;
    public static final double SAMPLE_MEAN_A6 = 0.65;
    public static final double SAMPLE_MEAN_B6 = 0.85;
    public static final double SAMPLE_VARIANCE_A6 = 0;
    public static final double SAMPLE_VARIANCE_B6 = 0;
    public static final double EXPECTED_POWER6 = 0.80;


    @Test
    public void testPValueWhenInputAreBernoulliDistribution() {
        NormalStatistic a = new NormalStatistic(SAMPLE_SIZE_A1, SAMPLE_MEAN_A1, SAMPLE_VARIANCE_A1, BINARY_METRIC);
        NormalStatistic b = new NormalStatistic(SAMPLE_SIZE_B1, SAMPLE_MEAN_B1, SAMPLE_VARIANCE_B1, BINARY_METRIC);
        AbstractRealDistribution tDistribution = new TDistribution((double)a.getSampleSize() + b.getSampleSize() - 2);

        HypothesesTest hypothesesTest = new HypothesesTest(a, b, ALPHA, NO_BETA, TWO_TAILED, tDistribution);
        HypothesesTestIndicator hypothesesTestIndicator = hypothesesTest.performHypothesesTest();
        Assert.assertEquals(hypothesesTestIndicator.getPValue(), EXPECTED_P_VALUE1, DEVIATION);
    }

    @Test
    public void testConfidenceIntervalWhenInputAreNotBernoulliDistribution() {
        NormalStatistic a = new NormalStatistic(SAMPLE_SIZE_A2, SAMPLE_MEAN_A2, SAMPLE_VARIANCE_A2, NO_BINARY_METRIC);
        NormalStatistic b = new NormalStatistic(SAMPLE_SIZE_B2, SAMPLE_MEAN_B2, SAMPLE_VARIANCE_B2, NO_BINARY_METRIC);
        AbstractRealDistribution tDistribution = new TDistribution((double)a.getSampleSize() + b.getSampleSize() - 2);

        HypothesesTest hypothesesTest = new HypothesesTest(a, b, ALPHA, NO_BETA, TWO_TAILED, tDistribution);
        HypothesesTestIndicator hypothesesTestIndicator = hypothesesTest.performHypothesesTest();
        Assert.assertEquals(hypothesesTestIndicator.getPValue(), EXPECTED_P_VALUE2, DEVIATION);

        ConfidenceInterval improveRatio = hypothesesTest.computeImproveRatio();
        Assert.assertEquals(improveRatio.getLowerBound(), EXPECTED_CONFIDENCE_LOW_BOUND, DEVIATION);
        Assert.assertEquals(improveRatio.getUpperBound(), EXPECTED_CONFIDENCE_UPPER_BOUND, DEVIATION);
    }

    @Test
    public void testPowerWhenInputAreNotBernoulliDistribution() {
        NormalStatistic a = new NormalStatistic(SAMPLE_SIZE_A2, SAMPLE_MEAN_A2, SAMPLE_VARIANCE_A2, NO_BINARY_METRIC);
        NormalStatistic b = new NormalStatistic(SAMPLE_SIZE_B2, SAMPLE_MEAN_B2, SAMPLE_VARIANCE_B2, NO_BINARY_METRIC);
        AbstractRealDistribution tDistribution = new TDistribution((double)a.getSampleSize() + b.getSampleSize() - 2);

        HypothesesTest hypothesesTest = new HypothesesTest(a, b, ALPHA, NO_BETA, TWO_TAILED, tDistribution);
        double power = hypothesesTest.computePower();
        Assert.assertEquals(power, EXPECTED_POWER, DEVIATION);
    }

    @Test
    public void testSampleSize() {
        NormalStatistic a = new NormalStatistic(SAMPLE_SIZE_3, SAMPLE_MEAN_3, SAMPLE_VARIANCE_3, NO_BINARY_METRIC);
        NormalDistribution normalDistribution = new NormalDistribution();
        HypothesesTest hypothesesTest = new HypothesesTest(a, null, ALPHA, BETA, TWO_TAILED, normalDistribution);
        double sampleSize = hypothesesTest.computerSampleSize(DIFF_RATIO, SIZEB_SIZEA_RATIO);
        Assert.assertEquals(sampleSize, EXPECTED_SIZE, DEVIATION_SIZE);
    }

    // MORE TEST
    @Test
    public void testPowerWhenNormalDistribution() {
        // two side no-binary metric
        NormalStatistic a = new NormalStatistic(SAMPLE_SIZE_A4, SAMPLE_MEAN_A4, SAMPLE_VARIANCE_A4, NO_BINARY_METRIC);
        NormalStatistic b = new NormalStatistic(SAMPLE_SIZE_B4, SAMPLE_MEAN_B4, SAMPLE_VARIANCE_B4, NO_BINARY_METRIC);
        AbstractRealDistribution normalDistribution = new org.apache.commons.math3.distribution.NormalDistribution();
        AbstractRealDistribution tDistribution = new TDistribution(a.getSampleSize() + b.getSampleSize() - 2);

        HypothesesTest hypothesesTest1 = new HypothesesTest(a, b, ALPHA, NO_BETA, TWO_TAILED, normalDistribution);
        double power1 = hypothesesTest1.computePower();
        Assert.assertEquals(power1, EXPECTED_POWER4, DEVIATION);

        hypothesesTest1 = new HypothesesTest(a, b, ALPHA, NO_BETA, TWO_TAILED, tDistribution);
        power1 = hypothesesTest1.computePower();
        Assert.assertEquals(power1, EXPECTED_POWER4, DEVIATION);

        // one side no-binary metric
        a = new NormalStatistic(SAMPLE_SIZE_A5, SAMPLE_MEAN_A5, SAMPLE_VARIANCE_A5, NO_BINARY_METRIC);
        b = new NormalStatistic(SAMPLE_SIZE_B5, SAMPLE_MEAN_B5, SAMPLE_VARIANCE_B5, NO_BINARY_METRIC);
        HypothesesTest hypothesesTest2 = new HypothesesTest(a, b, ALPHA, NO_BETA, ONE_TAILED, normalDistribution);
        double power2 = hypothesesTest2.computePower();
        Assert.assertEquals(power2, EXPECTED_POWER5, DEVIATION);
        hypothesesTest2 = new HypothesesTest(a, b, ALPHA, NO_BETA, ONE_TAILED, tDistribution);
        power2 = hypothesesTest2.computePower();
        Assert.assertEquals(power2, EXPECTED_POWER5, DEVIATION);

        // two side binary metric
        a = new NormalStatistic(SAMPLE_SIZE_A6, SAMPLE_MEAN_A6, SAMPLE_VARIANCE_A6, BINARY_METRIC);
        b = new NormalStatistic(SAMPLE_SIZE_B6, SAMPLE_MEAN_B6, SAMPLE_VARIANCE_B6, BINARY_METRIC);
        HypothesesTest hypothesesTest3 = new HypothesesTest(a, b, ALPHA, NO_BETA, TWO_TAILED, normalDistribution);
        double power3 = hypothesesTest3.computePower();
        Assert.assertEquals(power3, EXPECTED_POWER6, DEVIATION);
        hypothesesTest3 = new HypothesesTest(a, b, ALPHA, BETA, TWO_TAILED, tDistribution);
        power3 = hypothesesTest3.computePower();
        Assert.assertEquals(power3, EXPECTED_POWER6, DEVIATION);
    }
}
