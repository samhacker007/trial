package trial.threads.threadlocal.sample2;

public class NoThreadlocalTest {
	long longLocal = -1L;
	
	String stringLocal = "default";

	public void set() {
		longLocal = Thread.currentThread().getId();
		stringLocal = Thread.currentThread().getName();
	}

	public long getLong() {
		return longLocal;
	}

	public String getString() {
		return stringLocal;
	}

	public static void main(String[] args) throws InterruptedException {
		final NoThreadlocalTest test = new NoThreadlocalTest();

		test.set();
		System.out.println(test.getLong());
		System.out.println(test.getString());

		Thread thread1 = new Thread() {
			public void run() {
				test.set();
				System.out.println(test.getLong());
				System.out.println(test.getString());
			};
		};
		thread1.start();
		thread1.join();

		System.out.println(test.getLong());
		System.out.println(test.getString());
	}
}