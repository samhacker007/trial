package trial.threads.lock.reentrantlock.sample6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestTryLock {

	private List<Object> list = new ArrayList<Object>();
	private ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		final TestTryLock test = new TestTryLock();
		new Thread("第一个线程  ") {

			@Override
			public void run() {
				test.doSomething(Thread.currentThread());
			}
		}.start();

		new Thread("第二个线程  ") {

			@Override
			public void run() {
				test.doSomething(Thread.currentThread());
			}
		}.start();
		
		new Thread("第三个线程  ") {

			@Override
			public void run() {
				test.doOtherthing(Thread.currentThread());
			}
		}.start();
	}

	public void doSomething(Thread thread) {
		if (lock.tryLock()) {
			try {
				System.out.println(thread.getName() + "得到了锁? " + lock.isHeldByCurrentThread());
				// System.out.println("是否有其它线程在等待锁? " +
				// lock.hasQueuedThreads());
				for (int i = 0; i < 10; i++) {
					list.add(i);
				}
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println(thread.getName() + "释放了锁.");
				lock.unlock();
			}
		} else {
			System.out.println(thread.getName() + "获取锁失败.");
		}
	}

	public void doOtherthing(Thread thread) {
		try {
			lock.lock();
			System.out.println(thread.getName() + "得到了锁? " + lock.isHeldByCurrentThread());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(thread.getName() + "释放了锁.");
			lock.unlock();
		}

	}
}
