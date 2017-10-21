package trial.architect.designpattern.buiilder.sample1;

public class Director {
	
	public Car constructCar(CarBuilder carBuilder){
		carBuilder.buildEngine();
		carBuilder.buildSeats();
		carBuilder.buildWheel();
		
		return carBuilder.buildCar();
		
	}

}
