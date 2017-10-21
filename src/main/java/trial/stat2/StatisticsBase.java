package trial.stat2;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class StatisticsBase {

	// Internal sample instance
	private double[] sample;
	// Get a DescriptiveStatistics instance
	private DescriptiveStatistics stats = new DescriptiveStatistics();

	public StatisticsBase(double[] sample) {
		this.sample = sample;

		// Add the data from the sample array
		for (int i = 0; i < this.sample.length; i++) {
			stats.addValue(this.sample[i]);
		}
	}

	/**
	 * @return the size of the sample array
	 */
	public int getSize() {
		return sample.length;
	}

	/**
	 * @return the mean value of the sample array
	 */
	public double getMean() {
		return stats.getMean();
	}

	/**
	 * @return the variance value of the sample array
	 */
	public double getVariance() {
		return stats.getVariance();
	}

}
