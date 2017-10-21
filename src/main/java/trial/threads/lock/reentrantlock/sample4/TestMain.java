package trial.threads.lock.reentrantlock.sample4;

public class TestMain {

	public static void main(String[] args) throws Exception {
		ThreadDomain40 td = new ThreadDomain40();
		MyThread40 mt = new MyThread40(td);
		mt.start();
		Thread.sleep(3000);
		td.signal();
	}
}
