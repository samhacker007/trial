package trial.threads.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMain {

	public static void main(String[] args) throws InterruptedException {

		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					try {
						System.out.println("timestamp: " + System.currentTimeMillis() + ":" + index);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}

		Thread.sleep(6000);

		fixedThreadPool.shutdown();
		fixedThreadPool = null;

	}

}
