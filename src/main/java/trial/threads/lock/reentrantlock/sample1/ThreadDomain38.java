package trial.threads.lock.reentrantlock.sample1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDomain38 {
	private Lock lock = new ReentrantLock();

	public void testMethod() {
		try {
			System.out.println("ThreadName = " + Thread.currentThread().getName() + " is trying to get the lock");
			lock.lock();
			System.out.println("!!! ThreadName = " + Thread.currentThread().getName() + " got the lock");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			for (int i = 0; i < 2; i++) {
//				System.out.println("ThreadName = " + Thread.currentThread().getName() + ", i  = " + i);
//			}
		} finally {
			lock.unlock();
		}
	}
}
