package trial.stat2;

import java.text.DecimalFormat;

public class StatisticsUtil {
	
	public static double formatDouble(double orig){
		DecimalFormat df = new DecimalFormat("######0.000");
		return Double.valueOf(df.format(orig)).doubleValue();		
	}

}
