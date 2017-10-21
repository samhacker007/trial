package trial.architect.designpattern.factory.simplefactory.sample1;

import trial.architect.designpattern.factory.Food;

public class TestMain {

	public static void main(String[] args) {
		SimpleFactory factory = new SimpleFactory();
		Food food = factory.create("china");
		
		food.eat();
	}

}
