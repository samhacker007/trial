package trial.threads.lock.redission.sample1;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * 
 * 在集群中不同节点上做过实验，证明锁的确有效
 * 
 * @author liuqiyun
 *
 */
public class RedissionLockTest {
	final static String LOCK = "lock";

	class RedisVisitor implements Runnable {

		RedissonClient client;

		public RedisVisitor() {
			System.out.println(Thread.currentThread().getId() + " will create a client");
			Config config = new Config();
//			config.useSingleServer().setAddress("redis://127.0.0.1:6379");
			config.useSingleServer().setAddress("redis://10.10.1.28:6379");
			client = Redisson.create(config);
		}

		@Override
		public void run() {
			int tryTimes = 10;

			for (int i = 0; i < tryTimes; i++) {
				System.out.println("&&&&&&" + Thread.currentThread().getId() + " will create a lock");
				RLock lock = client.getLock(LOCK);
				System.out.println("&&&&&&" + Thread.currentThread().getId() + " will lock the lock");
				// 当其它进程获得这个锁的时候，本进程将会被阻塞在这一行
				lock.lock();
				// perform some long operations...
				System.out.println("&&&&&&" + Thread.currentThread().getId() + " got the lock and will do some work at "
						+ System.currentTimeMillis());
				try {
					Thread.sleep(200000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				lock.unlock();
				System.out.println(
						Thread.currentThread().getId() + " just released the lock at " + System.currentTimeMillis());
			}
			
			System.exit(0);

		}
	}

	public void test() {
		RedisVisitor rv1 = new RedisVisitor();
		Thread thread1 = new Thread(rv1);

//		RedisVisitor rv2 = new RedisVisitor();
//		Thread thread2 = new Thread(rv2);
//
//		RedisVisitor rv3 = new RedisVisitor();
//		Thread thread3 = new Thread(rv3);
//
//		RedisVisitor rv4 = new RedisVisitor();
//		Thread thread4 = new Thread(rv4);

		thread1.start();
//		thread2.start();
//		thread3.start();
//		thread4.start();
	}

	public static void main(String[] args) {
		RedissionLockTest test = new RedissionLockTest();
		test.test();

	}

}
