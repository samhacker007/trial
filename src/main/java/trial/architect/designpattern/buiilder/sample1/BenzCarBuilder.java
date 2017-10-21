package trial.architect.designpattern.buiilder.sample1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BenzCarBuilder implements CarBuilder{
	
	Car car;
	
	public BenzCarBuilder(){
		car = new Car();
	}

	@Override
	public void buildWheel() {
		log.info("Benz buildWheel");
		car.setWheel("Benz buildWheel");
		
	}

	@Override
	public void buildSeats() {
		log.info("Benz buildSeats");
		car.setSeats("Benz buildSeats");
		
	}

	@Override
	public void buildEngine() {
		log.info("Benz buildEngine");
		car.setEngine("Benz buildEngine");
		
	}

	@Override
	public Car buildCar() {
		log.info("Benz buildCar");
		return car;
	}

}
