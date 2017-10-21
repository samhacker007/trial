package trial.stat;

/**
 * Simple, fast, online normal statistics object using Welford's algorithm.
 */
public class StatisticsBase {

    private int _n = 0;
    private double _mean = 0;
    private double _sumSqDiff = 0;

    public StatisticsBase() {
    }

    public StatisticsBase(double[] initialValues) {
        for (double d : initialValues) {
            addValue(d);
        }
    }

    public void addValue(double value) {
        if (Double.isNaN(value))
            return;
        double old_mean = _mean;
        _n++;
        _mean += (value - old_mean) / _n;
        _sumSqDiff += (value - _mean) * (value - old_mean);
    }

    public int getN() {
        return _n;
    }

    public double getMean() {
        return (_n > 0) ? _mean : Double.NaN;
    }

    public double getSumSqDev() {
        return (_n > 0) ? _sumSqDiff : Double.NaN;
    }

    public double getVariance() {
        return (_n > 1) ? _sumSqDiff / (_n - 1) : Double.NaN;
    }

    public double getPopulationVariance() {
        return (_n > 0) ? _sumSqDiff / _n : Double.NaN;
    }

    public double getStandardScore(double value) {
        return (value - _mean) / Math.sqrt(getVariance());
    }

}
