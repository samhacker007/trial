package trial.concurrency.executor;

import java.util.HashMap;

//测试线程类
public class MyThread implements Runnable {
	private int number;

	public MyThread(int num) {
		number = num;
		System.out.println("Create Thread:" + num);
	}

	public void run() {
		System.out.println("Thread:" + number + " is running");
		// 为了让实验效果更明显
		try {
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			for (int i = 0; i < 100000; i++) {
				map.put(i,
						"dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread:" + number + " end");

	}

}
