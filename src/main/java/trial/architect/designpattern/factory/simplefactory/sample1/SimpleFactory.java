package trial.architect.designpattern.factory.simplefactory.sample1;

import trial.architect.designpattern.factory.ChinaFood;
import trial.architect.designpattern.factory.Food;
import trial.architect.designpattern.factory.JapanFood;

/**
 * 普通工厂模式，就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建。
 * 
 * @author liuqiyun
 *
 */
public class SimpleFactory {

	/*
	 * 创建的方法有三种方式：
	 * 1、提供一个同一的方法，然后在这个方法中根据输入来生产不同的对象
	 * 2、提供不同的方法，每个方法产生不同的对象
	 * 3、提供静态方法来生产对象
	 * 
	 * 这里就展示方法1，不再编写2、3
	 * 
	 */
	public Food create(String foodType) {
		if (foodType.equals("china"))
			return new ChinaFood();
		else if (foodType.equals("japan"))
			return new JapanFood();

		return null;

	}

}
