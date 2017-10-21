package trial.threads.lock.reentrantlock.sample1;

public class TestMain {

	public static void main(String[] args) {
		ThreadDomain38 td = new ThreadDomain38();
		MyThread38 mt0 = new MyThread38(td);
		MyThread38 mt1 = new MyThread38(td);
		MyThread38 mt2 = new MyThread38(td);
		mt0.start();
		mt1.start();
		mt2.start();
	}
}
