package trial.enums.sample1;

public class TestMain {

	public static void main(String[] args) {
		DistanceUnit du = DistanceUnit.MILE;
		
		System.out.println(du.getName() + ":" + du.convert(9));

	}

}
