package trial.threads.lock.reentrantlock.sample5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDomain42 {
	// 使用了公平锁
	private Lock lock = new ReentrantLock(true);

	public void testMethod() {
		try {
			lock.lock();
			System.out.println("ThreadName" + Thread.currentThread().getName() + "获得锁");
		} finally {
			lock.unlock();
		}
	}
}
