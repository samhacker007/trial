package trial.stat;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by ruan on 16-4-22.
 */
public class OnlineNormalStatisticsTest {
    @Test
    public void simpleArray() {
        double[] testArray = {
                1, 2
        };
        testCorrectness(testArray);
    }

    @Test
    public void bigArray() {
        double[] testArray = new double[1000];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = Math.random();
        }
        testCorrectness(testArray);
    }

    private void testCorrectness(double[] arr) {
        StatisticsBase stat = new StatisticsBase(arr);
        DescriptiveStatistics ds = new DescriptiveStatistics(arr);
        Assert.assertEquals(stat.getMean(), ds.getMean(), 0.001);
        Assert.assertEquals(stat.getVariance(), ds.getVariance(), 0.001);
        Assert.assertEquals(stat.getPopulationVariance(), ds.getPopulationVariance(), 0.001);
    }
}
