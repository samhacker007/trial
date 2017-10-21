package trial.threads.future.sample1;

/**
 * Client主要完成的功能包括：1. 返回一个FutureData；2.开启一个线程用于构造RealData Created by yulinfeng
 * on 6/18/17.
 */
public class Client {

	public Data request(final String string) {
		final FutureData futureData = new FutureData();

		/* 计算过程比较慢，单独放到一个线程中去 */
		new Thread(new Runnable() {

			public void run() {
				RealData realData = new RealData(string);
				futureData.setResultData(realData);
			}
		}).start();

		return futureData; // 先返回一个“假”的futureData
	}
}