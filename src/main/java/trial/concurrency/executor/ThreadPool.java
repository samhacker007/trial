package trial.concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//主程序
public class ThreadPool {
  public static void main(String[] args) {
      ExecutorService exec = Executors.newFixedThreadPool(10);
      for(int i = 0; i < 10;i++){
      //execute方法接受一个runnable对象作为参数，以异步的形式去执行他
          exec.execute(new MyThread(i));
      }
      exec.shutdown();
  }
}
