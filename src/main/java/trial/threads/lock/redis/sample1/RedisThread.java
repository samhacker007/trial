package trial.threads.lock.redis.sample1;

import java.util.Random;

import redis.clients.jedis.Jedis;

public class RedisThread implements Runnable {
	final static long SETNX_SUCCESS = 1L;
	final static long SETNX_FAIL = 0L;
	final static String LOCK_KEY = "lock";
	final static char SEPERATOR = '_';
	final static long LOCK_DURATION = 2000;

	Jedis jedis;
	Lock lock;

	public RedisThread(Lock lock) {
		jedis = new Jedis("localhost");
		this.lock = lock;
	}

	private boolean isExpired(String oldValue) {
		long lockTimestamp = Long.valueOf(oldValue.substring(oldValue.indexOf(SEPERATOR) + 1, oldValue.length()));
		long currentTimestamp = System.currentTimeMillis();
		return ((currentTimestamp - lockTimestamp) > LOCK_DURATION);
	}

	private boolean tryGetLock(String value, String oldValue) {

		// 通过Redis的GETSET来解决race condition的问题
		// GETSET: 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
		String latestOldValue = jedis.getSet(LOCK_KEY, value);

		System.out.println("******" + Thread.currentThread().getId() + " will set value = " + value + "; oldValue = "
				+ oldValue + "; latestOldValue=" + latestOldValue);

		// 当原来的锁不存在时，实际上有可能多个进程都同时觉得自己得到了锁（经测试证明的确会如此）
		// 因此要做进一步的排除race condition
		if (oldValue == null) {
			oldValue = value;

			try {
				// 休眠一个随机时间，以错开各个进程之间抢锁的时刻
				Random random = new Random();
				int sleepTime = random.nextInt(100);
				System.out.println("******" + Thread.currentThread().getId() + " will sleep " + sleepTime + "ms");
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
			}
			latestOldValue = jedis.getSet(LOCK_KEY, value);

		}

		// 如果设置锁前锁内容不变，则成功获取锁
		if (oldValue.equals(latestOldValue)) {
			System.out.println("******" + Thread.currentThread().getId() + " will get the lock ");
			return true;
		} else {
			System.out.println("******" + Thread.currentThread().getId() + " can not get the lock as a litter late");
			// 如果设置锁前锁内容已经改变，说明在上次获取锁后有其它进程已经得到了锁，因此则不能成功获取锁，还需要将锁内容写回去
			jedis.set(LOCK_KEY, latestOldValue);
			return false;
		}

	}

	@Override
	public void run() {
		int times = 10;

		for (int i = 0; i < times; i++) {
			String currentTimestamp = String.valueOf(System.currentTimeMillis());
			long currentThreadId = Thread.currentThread().getId();
			String value = String.valueOf(currentThreadId) + SEPERATOR + currentTimestamp;

			// 通过Redis的SETNX命令（SET if Not eXists）来获取锁
			// SETNX命令：当且仅当 key 不存在，将 key 的值设为 value ，并返回1；若给定的 key 已经存在，则 SETNX
			// 不做任何动作，并返回0。
			// 当没有得到锁的时候，持续等待并重试
			while (jedis.setnx(LOCK_KEY, value) == SETNX_FAIL) {
				System.out.println(currentThreadId + " failed to get the lock");

				try {
					String oldValue = jedis.get(LOCK_KEY);

					// 如果oldValue为null，说明这个时刻锁已经被释放了，可以立即进入抢锁环节
					if (oldValue == null) {
						System.out.println(currentThreadId + " get null value of lock, and will try to get the lock..");
						// try to delete and get the lock。这一步主要防止race
						// condition，即多个进程同时获得了锁
						if (tryGetLock(value, oldValue))
							// if success, break the while loop
							break;
					} else {
						// 防止锁过期（持有锁的进程挂掉未来得及释放锁等原因）
						if (isExpired(oldValue)) {
							// 锁过期时，try to delete and get the lock。这一步主要防止race
							// condition，即多个进程同时获得了锁
							if (tryGetLock(value, oldValue))
								// if success, break the while loop
								break;
						}
					}

					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 重新生成新的值
				currentTimestamp = String.valueOf(System.currentTimeMillis());
				value = String.valueOf(currentThreadId) + SEPERATOR + currentTimestamp;
			}
			lock.setLockTimes(lock.getLockTimes() + 1);

			System.out.println("#####################" + currentThreadId + " " + lock.getLockTimes()
					+ "th got the lock at " + System.currentTimeMillis() + ", and will do some work...");

			// do some work
			try {
				Thread.sleep(1000);

				// 释放锁
				jedis.del(LOCK_KEY);
				System.out.println(currentThreadId + " deleted the lock at " + System.currentTimeMillis());

				// 为了实验效果，再等待一下，让别人更有机会抢到锁
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
