package trial.architect.designpattern.buiilder.sample1;

public class TestMain {

	public static void main(String[] args) {
		CarBuilder carBuilder = new BenzCarBuilder();
		Director director = new Director();
		Car car = director.constructCar(carBuilder);
	}

}
