package trial.threads.lock.reentrantlock.sample1;

public class MyThread38 extends Thread {
	private ThreadDomain38 td;

	public MyThread38(ThreadDomain38 td) {
		this.td = td;
	}

	public void run() {
		td.testMethod();
	}
}
