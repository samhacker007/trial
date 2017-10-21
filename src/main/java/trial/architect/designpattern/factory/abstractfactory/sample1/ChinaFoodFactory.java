package trial.architect.designpattern.factory.abstractfactory.sample1;

import trial.architect.designpattern.factory.ChinaFood;
import trial.architect.designpattern.factory.Food;

public class ChinaFoodFactory extends Factory{
	@Override
	public Food create(){
		return new ChinaFood();
	}
}
