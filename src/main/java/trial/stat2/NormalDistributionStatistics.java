package trial.stat2;

public class NormalDistributionStatistics extends DistributionStatistics{

	public NormalDistributionStatistics(int aSampleSize, double aSampleMean,
			double aSampleVariance, boolean binaryMetric) {
//		if (aSampleSize < 30) {
//			throw new IllegalArgumentException(
//					"the number of sampled elements must bigger than 30");
//		}
		sampleSize = aSampleSize;
		sampleMean = aSampleMean;
		if (binaryMetric)
			// for Bernoulli 0-1 distribution
			sampleVariance = aSampleMean * (1.0 - aSampleMean);
		else
			// for others
			sampleVariance = aSampleVariance;
	}

	public NormalDistributionStatistics(StatisticsBase sb1, boolean NO_BINARY_METRIC) {
		this(sb1.getSize(), sb1.getMean(), sb1.getVariance(), NO_BINARY_METRIC);
	}
}
