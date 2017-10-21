package trial.threads.volatiles;

public class VolatileTest {
	public volatile int inc = 0;

	/**
	 * volatile关键字能保证可见性，但不能够保证原子性：自增操作不是原子操作
	 * 
	 * volatile一般用于状态标志位：
	 * 
	 * volatile boolean flag = false;
	 * 
	 * while(!flag){ doSomething(); }
	 * 
	 * public void setFlag() { flag = true; }
	 * 
	 * 
	 */
	public void increase() {
		inc++;
	}

	public static void main(String[] args) {
		final VolatileTest test = new VolatileTest();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				};
			}.start();
		}

		while (Thread.activeCount() > 1) // 保证前面的线程都执行完
			Thread.yield();
		System.out.println(test.inc);
	}
}
