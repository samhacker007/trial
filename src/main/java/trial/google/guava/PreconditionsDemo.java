package trial.google.guava;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class PreconditionsDemo {
    public static void main(String[] args) {
//        new Car(null);//Exception in thread "main" java.lang.NullPointerException
        new Car("Audi");
        //Exception in thread "main" java.lang.IllegalArgumentException: speed (0.0) must be positive
        new Car("Audi").drive(0);
    }
}

class Car {
    private String name;

    public Car(String name) {
        this.name = checkNotNull(name);//NPE Null Pointer Exception
    }

    public void drive(double speed) {
        checkArgument(speed > 0.0, "speed (%s) must be positive", speed);
    }
}
