package trial.design.singleton;

public class Car {

	private Car() {
	}
	
	public static final Car CAR = new Car();
	
	public void driver(){
		System.out.println("drive the car!");
	}
}
