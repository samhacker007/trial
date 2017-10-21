package trial.random.sample1;

import java.util.Random;

public class TestMain {

	// 使用相同的种子15，得到的相同的随机序列
	public void test() {

		for (int i = 0; i < 2; i++) {
//			Random r = new Random(15);
			Random r = new Random();
			for (int j = 0; j < 5; j++) {
				System.out.print(r.nextInt(20) + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		TestMain test = new TestMain();
		test.test();

	}

}
