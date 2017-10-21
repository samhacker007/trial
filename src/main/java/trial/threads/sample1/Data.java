package trial.threads.sample1;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 模拟临界资源的类
 * 
 */

@Getter
@Setter
public class Data {

	public Integer n;

	public Object lock = new Object();

	public Data() {

		n = 60;

	}

	synchronized public void addData() throws InterruptedException {
		System.out.println("Thread " + Thread.currentThread().getId() + " entered addData");
		System.out.println(Thread.currentThread().getId() + ":" + this.getN());
		int tmp = this.getN();
		Thread.sleep(10);
		this.setN(tmp + 1);
		System.out.println("Thread " + Thread.currentThread().getId() + " out addData");
	}

	synchronized public void removeData() throws InterruptedException {
		System.out.println("Thread " + Thread.currentThread().getId() + " entered removeData");
		System.out.println(Thread.currentThread().getId() + ":" + this.getN());
		int tmp = this.getN();
		Thread.sleep(10);
		this.setN(tmp - 1);
		System.out.println("Thread " + Thread.currentThread().getId() + " out removeData");
	}

	public void addData1() throws InterruptedException {
		// 在对象上加锁，比在方法中加锁，对性能的影响更小
		// 另外，只要在一个多线程都共享的对象上加锁就可以，比如n、lock或this
		
		// synchronized (n) {
		// synchronized (lock) {
		synchronized (this) {
			System.out.println("Thread " + Thread.currentThread().getId() + " entered addData1");
			System.out.println(Thread.currentThread().getId() + ":" + this.getN());
			ThreadLocal<Integer> tmp = new ThreadLocal<Integer>();
			tmp.set(n);
			Thread.sleep(10);
			tmp.set(n + 1);
			n = tmp.get();
			this.setN(n);
			System.out.println("Thread " + Thread.currentThread().getId() + " out addData1");
		}

		// 这部分可以不被加锁，可以并发地执行
		for (int i = 0; i < 10; i++)
			System.out.println("Thread " + Thread.currentThread().getId() + " doing works in addData1");

	}

	public void removeData1() throws InterruptedException {
		// 在对象上加锁，比在方法中加锁，对性能的影响更小
		// 另外，只要在一个多线程都共享的对象上加锁就可以，比如n、lock或this
		
		// synchronized (n) {
		// synchronized (lock) {
		synchronized (this) {
			System.out.println("Thread " + Thread.currentThread().getId() + " entered removeData1");
			System.out.println(Thread.currentThread().getId() + ":" + this.getN());
			ThreadLocal<Integer> tmp = new ThreadLocal<Integer>();
			tmp.set(n);
			Thread.sleep(10);
			tmp.set(n - 1);
			n = tmp.get();
			this.setN(n);
			System.out.println("Thread " + Thread.currentThread().getId() + " out removeData1");
		}

		// 这部分可以不被加锁，可以并发地执行
		for (int i = 0; i < 10; i++)
			System.out.println("Thread " + Thread.currentThread().getId() + " doing works in removeData1");
	}

}
