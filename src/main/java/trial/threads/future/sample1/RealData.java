package trial.threads.future.sample1;

/**
 * 结果的真实计算过程 Created by yulinfeng on 6/18/17.
 */
public class RealData implements Data {
	protected String data;

	public RealData(String data) {
		try {
			System.out.println("正在计算结果");
			Thread.sleep(3000); // 模拟计算
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.data = data + "world";
	}

	public String getResult() throws InterruptedException {
		return data;
	}
}