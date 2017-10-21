package trial.threads.future.sample1;

/**
 * 负责调用Client发起请求，并使用返回的数据。 Created by yulinfeng on 6/18/17.
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		Client client = new Client();
		System.out.println("准备计算结果");
		Data data = client.request("hello"); // 立即返回一个“假”的futureData，可以不用阻塞的等待数据返回，转而执行其它任务
		System.out.println("数据立即得到的计算结果为：" + data.getResult());
		System.out.println("执行其它任务");
		Thread.sleep(3000); // 模拟执行其它任务
		System.out.println("数据最后得到的计算结果为：" + data.getResult());
	}
}