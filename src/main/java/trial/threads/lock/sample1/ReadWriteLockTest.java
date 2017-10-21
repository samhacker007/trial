package trial.threads.lock.sample1;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
	public static void main(String[] args) {
		final Queue3 q3 = new Queue3();
		for (int i = 0; i < 3; i++) {
			new Thread() {
				public void run() {
					while (true) {
						q3.get();
					}
				}

			}.start();
		}
		for (int i = 0; i < 3; i++) {
			new Thread() {
				public void run() {
					while (true) {
						q3.put(new Random().nextInt(10000));
					}
				}

			}.start();
		}
	}
}
