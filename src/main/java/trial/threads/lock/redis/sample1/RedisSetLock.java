package trial.threads.lock.redis.sample1;

public class RedisSetLock {
	

	public static void main(String[] args) {
		
		Lock lock = new Lock();
		
		RedisThread rt1 = new RedisThread(lock);
		Thread thread1 = new Thread(rt1);
		
		RedisThread rt2 = new RedisThread(lock);
		Thread thread2 = new Thread(rt2);
		
		RedisThread rt3 = new RedisThread(lock);
		Thread thread3 = new Thread(rt3);
		
		RedisThread rt4 = new RedisThread(lock);
		Thread thread4 = new Thread(rt4);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();

	}

}
