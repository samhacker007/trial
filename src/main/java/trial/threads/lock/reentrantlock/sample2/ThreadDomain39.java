package trial.threads.lock.reentrantlock.sample2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * 即便多个线程同时访问ThreadDomain39对象，且访问不同的方法methodA或methodB，但某一个时刻只能有一个线程能访问某一个方法
 * 
 * @author liuqiyun
 *
 */
public class ThreadDomain39 {
	private Lock lock = new ReentrantLock();

	public void methodA() {
		try {
			lock.lock();
			System.out.println("MethodA begin ThreadName = " + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("MethodA end ThreadName = " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}

	public void methodB() {
		lock.lock();
		System.out.println("MethodB begin ThreadName = " + Thread.currentThread().getName());
		System.out.println("MethodB begin ThreadName = " + Thread.currentThread().getName());
		lock.unlock();
	}
}
