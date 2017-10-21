package trial.threads.join;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(){
			
			@Override
			public void run(){
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("fork thread finished");
			}
		};
		
		thread.start();
		// 通过调用join()方法，主线程将等待创建的(子)线程执行完成。
		thread.join();
		
		System.out.println("Main thread finished");
	}

}
