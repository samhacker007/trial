package trial.stat2;

public abstract class DistributionStatistics {
	protected int sampleSize = 0;
	protected double sampleMean = 0.0;
	protected double sampleVariance = 0.0;
	
	/**
	 * @return the size of the sample array
	 */
	public int getSampleSize() {
		return sampleSize;
	}

	/**
	 * @return the mean value of the sample array
	 */
	public double getSampleMean() {
		return sampleMean;
	}

	/**
	 * @return the variance value of the sample array
	 */
	public double getSampleVariance() {
		return sampleVariance;
	}
}
