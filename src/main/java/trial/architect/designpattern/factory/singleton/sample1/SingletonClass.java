package trial.architect.designpattern.factory.singleton.sample1;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 单例对象（Singleton）是一种常用的设计模式。在Java应用中，单例对象能保证在一个JVM中，该对象只有一个实例存在。这样的模式有几个好处：
 * 
 * 1、某些类创建比较频繁，对于一些大型的对象，这是一笔很大的系统开销。
 * 
 * 2、省去了new操作符，降低了系统内存的使用频率，减轻GC压力。
 * 
 * 3、有些类如交易所的核心交易引擎，控制着交易流程，如果该类可以创建多个的话，系统完全乱了。
 * （比如一个军队出现了多个司令员同时指挥，肯定会乱成一团），所以只有使用单例模式， 才能保证核心交易服务器独立控制整个流程。
 * 
 * @author liuqiyun
 *
 */
@Slf4j
public class SingletonClass {

	private static SingletonClass instance = null;

	private SingletonClass() {

	}
	
	public void method(){
		log.info("method()");
	}

	/*
	 * 多线程的环境下，可以对getInstance方法加synchronized关键字,
	 * 但是，synchronized关键字锁住的是这个对象，这样的用法，在性能上会有所下降，
	 * 因为每次调用getInstance()，都要对对象上锁。所以，这个不是个好办法
	 * 
	 * 多线程的实现请看SingletonClass2
	 */
	public static SingletonClass getInstance() {
		if (instance == null)
			instance = new SingletonClass();
		return instance;
	}

}
