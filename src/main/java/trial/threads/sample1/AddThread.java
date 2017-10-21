package trial.threads.sample1;

/**
 * 
 * 访问数据的线程
 * 
 */

public class AddThread extends Thread {

	Data data;

	String name;

	public AddThread(Data data, String name) {

		this.data = data;

		this.name = name;

		start();

	}

	public void run() {

		try {

			for (int i = 0; i < 100; i++) {
				
//				System.out.println(name + ":" + data.getN());
				
				data.addData1();

				Thread.sleep(10);

			}

		} catch (Exception e) {
		}

	}

}
