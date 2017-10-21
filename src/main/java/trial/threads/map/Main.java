package trial.threads.map;

import java.util.HashMap;

public class Main {

	public static String KEY = "key";
	
	public static void main(String[] args) {
		HashMap hashMap = new HashMap();
		Thread thread1 = new Thread(new OperatorThread(hashMap));
		Thread thread2 = new Thread(new OperatorThread(hashMap));
		Thread thread3 = new Thread(new OperatorThread(hashMap));
		
		thread1.start();
		thread2.start();
		thread3.start();

	}

}
