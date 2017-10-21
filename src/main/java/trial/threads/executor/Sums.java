package trial.threads.executor;

import java.util.*;
import java.util.concurrent.*;
import static java.util.Arrays.asList;

public class Sums {
	// 提供预先配置的Executors和包装普通的java.util.Runnable对象为Callable实例。使用Callable比Runnable更优势的地方在于Callable可以有确切的返回值。
	static class Sum implements Callable<Long> {
		private final long from;

		private final long to;

		Sum(long from, long to) {

			this.from = from;

			this.to = to;

		}

		@Override

		public Long call() {

			long acc = 0;

			for (long i = from; i <= to; i++) {

				acc = acc + i;

			}

			return acc;

		}

	}

	public static void main(String[] args) throws Exception {

		/*
		 * 该例子使用executor分发工作给2个线程。ExecutorService.invokeAll()方法放入Callable实例的集合，
		 * 并且等待直到它们都返回。其返回Future对象列表，代表了计算的“未来”结果。如果我们想以异步的方式执行，
		 * 我们可以检测每个Future对象对应的Callable是否完成了它的工作和是否抛出了异常，甚至我们可以取消它。相比当使用普通的线程时，
		 * 你必须通过一个共享可变的布尔值来编码取消逻辑，并且通过定期检查该布尔值来破坏该代码。因为invokeAll()是阻塞的，
		 * 我们可以直接迭代Future实例来获取它们的计算和。
		 * 
		 * 另外要注意executor服务必须被关闭。如果它没有被关闭，主方法执行完后JVM就不会退出，因为仍然有激活线程存在。
		 */

		ExecutorService executor = Executors.newFixedThreadPool(2);

		List<Future<Long>> results = executor.invokeAll(asList(

				new Sum(0, 10), new Sum(100, 1_000), new Sum(10_000, 1_000_000)

		));

		executor.shutdown();

		for (Future<Long> result : results) {

			System.out.println(result.get());

		}

	}

}
