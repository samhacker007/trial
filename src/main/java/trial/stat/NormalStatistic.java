package trial.stat;

public class NormalStatistic {
	private int sampleSize = 0;
	private double sampleMean = 0.0;
	private double sampleVariance = 0.0;

	public NormalStatistic(int aSampleSize, double aSampleMean, double aSampleVariance, boolean binaryMetric) {
		if (aSampleSize < 30) {
			throw new IllegalArgumentException("the number of sampled elements must bigger than 30");
		}
		sampleSize = aSampleSize;
		sampleMean = aSampleMean;
		if (binaryMetric)
			sampleVariance = aSampleMean * (1.0 - aSampleMean);
		else
			sampleVariance = aSampleVariance;
	}

	public NormalStatistic(StatisticsBase sb1, boolean NO_BINARY_METRIC) {
		this(sb1.getN(), sb1.getMean(), sb1.getVariance(), NO_BINARY_METRIC);
	}

	public int getSampleSize() {
		return sampleSize;
	}

	public double getSampleMean() {
		return sampleMean;
	}

	public double getSampleVariance() {
		return sampleVariance;
	}
}
