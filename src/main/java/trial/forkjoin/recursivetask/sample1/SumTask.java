package trial.forkjoin.recursivetask.sample1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 这个例子算是使用ForkJoin的经典例子：能够有效地使用多核来进行并行计算
 * 
 * 然而，这个例子却不是最优的例子，因为它对多核并没有很好的进行利用。下面是更细的说明：
 * 
 * 很遗憾，这种写法是错！误！的！这样写没有正确理解Fork/Join模型的任务执行逻辑。
 * 
 * JDK用来执行Fork/Join任务的工作线程池大小等于CPU核心数。在一个4核CPU上，最多可以同时执行4个子任务。对400个元素的数组求和，执行时间应该为1秒。但是，换成上面的代码，执行时间却是两秒。
 * 
 * 这是因为执行compute()方法的线程本身也是一个Worker线程，当对两个子任务调用fork()时，这个Worker线程就会把任务分配给另外两个Worker，但是它自己却停下来等待不干活了！这样就白白浪费了Fork/Join线程池中的一个Worker线程，导致了4个子任务至少需要7个线程才能并发执行。
 * 
 * 打个比方，假设一个酒店有400个房间，一共有4名清洁工，每个工人每天可以打扫100个房间，这样，4个工人满负荷工作时，400个房间全部打扫完正好需要1天。
 * 
 * Fork/Join的工作模式就像这样：首先，工人甲被分配了400个房间的任务，他一看任务太多了自己一个人不行，所以先把400个房间拆成两个200，然后叫来乙，把其中一个200分给乙。
 * 
 * 紧接着，甲和乙再发现200也是个大任务，于是甲继续把200分成两个100，并把其中一个100分给丙，类似的，乙会把其中一个100分给丁，这样，最终4个人每人分到100个房间，并发执行正好是1天。
 * 
 * 
 * 正确的方法应该是：
 * 一次性地将任务分配完毕，算出总共需要多少个线程，然后创建ForkJoinPool并设置合适的并发数。
 * 
 * 
 * @author liuqiyun
 *
 */
class SumTask extends RecursiveTask<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static final int THRESHOLD = 5;
	long[] array;
	int start;
	int end;

	SumTask(long[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		if (end - start <= THRESHOLD) {
			// 如果任务足够小,直接计算:
			long sum = 0;
			for (int i = start; i < end; i++) {
				sum += array[i];
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			System.out.println(String.format("compute %d~%d = %d", start, end, sum));
			return sum;
		}
		// 任务太大,一分为二:
		int middle = (end + start) / 2;
		System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
		SumTask subtask1 = new SumTask(this.array, start, middle);
		SumTask subtask2 = new SumTask(this.array, middle, end);
		invokeAll(subtask1, subtask2);
		Long subresult1 = subtask1.join();
		Long subresult2 = subtask2.join();
		Long result = subresult1 + subresult2;
		System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
		return result;
	}

	public static void main(String[] args) throws Exception {
		// 创建随机数组成的数组:
		long[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		// fork/join task:
		ForkJoinPool fjp = new ForkJoinPool(4); // 最大并发数4
		ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
		long startTime = System.currentTimeMillis();
		Long result = fjp.invoke(task);
		long endTime = System.currentTimeMillis();
		System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
	}
}
