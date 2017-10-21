package trial.threads.sample2;

public class DataHandler {
	
	Data data;
	
	public DataHandler(Data data){
		this.data = data;
	}
	
	synchronized public void addData() throws InterruptedException {
		System.out.println("Thread " + Thread.currentThread().getId() + " entered addData");
		System.out.println(Thread.currentThread().getId() + ":" + data.getN());
		int tmp = data.getN();
		Thread.sleep(10);
		data.setN(tmp + 1);
		System.out.println("Thread " + Thread.currentThread().getId() + " out addData");
	}

	synchronized public void removeData() throws InterruptedException {
		System.out.println("Thread " + Thread.currentThread().getId() + " entered removeData");
		System.out.println(Thread.currentThread().getId() + ":" + data.getN());
		int tmp = data.getN();
		Thread.sleep(10);
		data.setN(tmp - 1);
		System.out.println("Thread " + Thread.currentThread().getId() + " out removeData");
	}

}
