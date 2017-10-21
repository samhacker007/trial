package trial.threads.future.sample2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureDemo {
	public static void main(String[] args) {
		Callable<int[]> primeCallable = new PrimeCallable(1000);
		FutureTask<int[]> primeTask = new FutureTask<int[]>(primeCallable);
		Thread t = new Thread(primeTask);
		t.start();
		try {
			// 假设现在做其他事情
//			Thread.sleep(5000);
			
			while(!primeTask.isDone()){
				System.out.println("Asy task is not finish now, so I can do other works for now...");
			}
			
			// 回来看看质数找好了吗
			if (primeTask.isDone()) {
				int[] primes = primeTask.get();
				for (int prime : primes) {
					System.out.print(prime + " ");
				}
				System.out.println();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}