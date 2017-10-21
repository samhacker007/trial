package trial.stat;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HypothesesResultTest {
    @Test
    public void testHypothesesResultTest() {
        double[] a = {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
        double[] b = {1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9, 1.1, 1.9};

        HypothesesResult hypothesesResult = new HypothesesResult(a, b);

        double pValue = hypothesesResult.getPValue();
        double power = hypothesesResult.getPower();
        double improveRatioLowBound = hypothesesResult.getConfidenceInterval().getLowerBound();
        double improveRatioUpperBound = hypothesesResult.getConfidenceInterval().getUpperBound();
        double avgIncrement = hypothesesResult.getAvgIncreaments();
        boolean smallEnough = hypothesesResult.isPValueSmallEnough();
        boolean bigEnough = hypothesesResult.isPowerBigEnough();
        double versionAMean = hypothesesResult.getVersionAMean();
        double versionAVariance = hypothesesResult.getVersionAVariance();
        int versionASize = hypothesesResult.getVersionASize();
        double versionBMean = hypothesesResult.getVersionBMean();
        double versionBVariance = hypothesesResult.getVersionBVariance();
        int versionBSize = hypothesesResult.getVersionBSize();
        
        Assert.assertEquals(versionAMean, 1.5, 0.01);
        Assert.assertEquals(versionAVariance, 0.256, 0.01);
        Assert.assertEquals(versionASize, 40, 0);
        Assert.assertEquals(versionBMean, 1.5, 0.01);
        Assert.assertEquals(versionBVariance, 0.164, 0.01);
        Assert.assertEquals(versionBSize, 40, 0.01);
        Assert.assertEquals(pValue, 0.5, 0.01);
        Assert.assertEquals(power, 0.05, 0.01);
        Assert.assertEquals(improveRatioLowBound, -0.134, 0.01);
        Assert.assertEquals(improveRatioUpperBound, 0.134, 0.01);
        Assert.assertEquals(avgIncrement, 0, 0.01);
        Assert.assertEquals(smallEnough, false, "统计不显著，接受原假设，认为没有区别");
        Assert.assertEquals(bigEnough, false, "当前试验统计功效不够高，因此无足够统计支持以推翻原假设（即两个样本集无明显差异的假设）。仅当计算得到的P-Value足够推翻原假设时，我们才会进一步计算统计功效以观测目前是否有足够的统计支持以推翻原假设。");
    }
}
