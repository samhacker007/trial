package trial.architect.designpattern.factory.abstractfactory.sample1;

import trial.architect.designpattern.factory.Food;

public class TestMain {

	public static void main(String[] args) {
		Factory factory = new ChinaFoodFactory();
		Food food = factory.create();
		
		food.eat();
	}

}
