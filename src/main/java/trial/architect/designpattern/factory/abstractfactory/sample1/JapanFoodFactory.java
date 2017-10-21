package trial.architect.designpattern.factory.abstractfactory.sample1;

import trial.architect.designpattern.factory.Food;
import trial.architect.designpattern.factory.JapanFood;

public class JapanFoodFactory extends Factory{
	@Override
	public Food create(){
		return new JapanFood();
	}
}
