package trial.stat2.hypothesestest;

public class HypothesesTestIndicator {
	// The result of the hypothesis test compared to the critical value
	private double pValue;
	// The critical value used to compare the hypotheses tests
	private double criticalValue;

	/**
	 * Creates a new HypothesesTestIndicator object.
	 * 
	 * @param a_pValue
	 *            The computed pValue.
	 * @param aCriticalValue
	 *            The critical value used in the T-Test to check for statistical
	 *            evidence.
	 */
	public HypothesesTestIndicator(double a_pValue, double aCriticalValue) {
		pValue = a_pValue;
		criticalValue = aCriticalValue;
	}

	/**
	 * @return The computed pValue of the HypothesesTest computation
	 */
	public double getPValue() {
		return pValue;
	}

	/**
	 * @return The critical value used to compare the hypotheses results.
	 */
	public double getCriticalValue() {
		return criticalValue;
	}

	@Override
	public String toString() {
		return "Hypotheses-Test";
	}

}
