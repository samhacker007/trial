package trial.threads.safe.redission.sample1;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import lombok.Getter;
import lombok.Setter;

public class RedissionSafeTestMain {
	final static int tryTimes = 1000;
	final static String SHARED_DATA_NAME = "myAtomicLong";

	@Getter
	@Setter
	class Data {
		// Unsafe type
		long unsafeLong = 0L;
		// int unsafeLong = 0;

		// Safe type
		// AtomicInteger unsafeLong = new AtomicInteger(0);
	}

	class UnsafeThread extends Thread {

		Data data;

		public UnsafeThread(Data data) {
			this.data = data;
		}

		@Override
		public void run() {
			for (int i = 0; i < tryTimes; i++) {
				this.data.setUnsafeLong(data.getUnsafeLong() + 1);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void testUnsafe() throws InterruptedException {
		Data data = new Data();

		UnsafeThread thread1 = new UnsafeThread(data);
		thread1.start();

		UnsafeThread thread2 = new UnsafeThread(data);
		thread2.start();

		UnsafeThread thread3 = new UnsafeThread(data);
		thread3.start();

		thread1.join();
		thread2.join();
		thread3.join();

		assert data.getUnsafeLong() == tryTimes * 3 : "Expected result = " + tryTimes * 3 + ", but actual result = "
				+ data.getUnsafeLong();

		// assert data.getUnsafeLong() == tryTimes * 3 : "Expected result = " +
		// tryTimes * 3 + ", but actual result = "
		// + data.getUnsafeLong();

	}

	class SafeThread extends Thread {

		RAtomicLong sharedData;
		RedissonClient client;

		public SafeThread(RAtomicLong sharedData) {
			Config config = new Config();
			config.useSingleServer().setAddress("redis://127.0.0.1:6379");
			client = Redisson.create(config);
			this.sharedData = sharedData;
			this.sharedData = client.getAtomicLong(SHARED_DATA_NAME);
		}

		@Override
		public void run() {
			for (int i = 0; i < tryTimes; i++) {

				sharedData.addAndGet(1);

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			System.out.println("Current sharedData = " + sharedData.get());
		}
	}

	public void testSafe() throws InterruptedException {
		RAtomicLong sharedData = null;
		SafeThread thread1 = new SafeThread(sharedData);
		thread1.start();

		SafeThread thread2 = new SafeThread(sharedData);
		thread2.start();

		SafeThread thread3 = new SafeThread(sharedData);
		thread3.start();

		thread1.join();
		thread2.join();
		thread3.join();

//		assert sharedData.get() == tryTimes * 3 : "Expected result = " + tryTimes * 3 + ", but actual result = "
//				+ sharedData.get();
//		System.out.println("Final sharedData = " + sharedData.get());
	}

	public static void main(String[] args) throws InterruptedException {
		RedissionSafeTestMain test = new RedissionSafeTestMain();
		// test.testUnsafe();
		test.testSafe();

	}

}
