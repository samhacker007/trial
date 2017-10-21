package trial.google.guava;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class StopWatchDemo {
    public static void main(String[] args) {

        final Stopwatch stopwatch = Stopwatch.createUnstarted().start();
        //start
//        stopwatch.start();
        try {
            System.out.println("You can do something!");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopwatch.stop();
        long nanos = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(nanos);//1000076976
    }
}
