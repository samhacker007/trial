package trial.threads.sample1;

/**
 * 
 * 访问数据的线程
 * 
 */

public class DeleteThread extends Thread {

	Data data;

	String name;

	public DeleteThread(Data data, String name) {

		this.data = data;

		this.name = name;

		start();

	}

	public void run() {

		try {

			for (int i = 0; i < 100; i++) {

//				System.out.println(name + ":" + data.getN());

				data.removeData1();

//				Thread.sleep(200);
				Thread.sleep(10);

			}

		} catch (Exception e) {
		}

	}

}
