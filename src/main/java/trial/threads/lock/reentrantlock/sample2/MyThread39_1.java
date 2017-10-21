package trial.threads.lock.reentrantlock.sample2;

public class MyThread39_1 extends Thread {
	private ThreadDomain39 td;

	public MyThread39_1(ThreadDomain39 td) {
		this.td = td;
	}

	public void run() {
		td.methodB();
	}
}
