package trial.threads.sample1;

/**
 * 
 * 测试多线程访问时的问题
 * 
 */

public class TestMulThread1 {

	public static void main(String[] args) throws InterruptedException {

		Data data = new Data();

		AddThread d1 = new AddThread(data, "线程1");

		DeleteThread d2 = new DeleteThread(data, "线程2");
		
		Thread.sleep(6000);
		System.out.println(data.n);

	}

}
