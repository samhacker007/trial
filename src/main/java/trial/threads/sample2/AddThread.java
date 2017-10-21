package trial.threads.sample2;

/**
 * 
 * 访问数据的线程
 * 
 */

public class AddThread extends Thread {

	Data data;

	String name;
	
	DataHandler dataHandler;

	public AddThread(Data data, String name, DataHandler dataHandler) {

		this.data = data;

		this.name = name;
		
		this.dataHandler = dataHandler;

		start();

	}

	public void run() {

		try {

			for (int i = 0; i < 100; i++) {
				
//				System.out.println(name + ":" + data.getN());
				
				dataHandler.addData();

				Thread.sleep(10);

			}

		} catch (Exception e) {
		}

	}

}
