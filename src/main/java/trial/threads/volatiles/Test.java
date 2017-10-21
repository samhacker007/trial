package trial.threads.volatiles;

import java.util.concurrent.*;

/**
 * 
 * 当想要停止线程时，调用线程的某个方法来设置某个标志变量，线程运行时会不断地检查这个变量，当它的值为true时就退出。需要注意的是这个标志变量必须设置为volatile，这应该属于常识了。书上都讲到，如果不设置为volatile，则该线程可能永远都不会退出
 * 
 * Volatile修饰的成员变量在每次被线程访问时，都强迫从共享内存中重读该成员变量的值。而且，当成员变量发生变化时，强迫线程将变化值回写到共享内存。这样在任何时刻，两个不同的线程总是看到某个成员变量的同一个值。
 * 
 * Java语言规范中指出：为了获得最佳速度，允许线程保存共享成员变量的私有拷贝，而且只当线程进入或者离开同步代码块时才与共享成员变量的原始值对比。
 * 
 * 这样当多个线程同时与某个对象交互时，就必须要注意到要让线程及时的得到共享成员变量的变化。
 * 
 * 而volatile关键字就是提示VM：对于这个成员变量不能保存它的私有拷贝，而应直接与共享成员变量交互。
 * 
 * 使用建议：在两个或者更多的线程访问的成员变量上使用volatile。当要访问的变量已在synchronized代码块中，或者为常量时，不必使用。
 * 
 * @author liuqiyun
 *
 */
public class Test {
	// Thread NOT stop
	// private static /* volatile */ boolean stop = false;

	// Thread will stop
	private static volatile boolean stop = false;

	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while (!stop) {
					i++;
					// System.out.println("hello");
				}
			}
		});
		t.start();

		Thread.sleep(1000);
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Stop Thread");
		stop = true;
	}
}
