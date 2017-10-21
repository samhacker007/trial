package trial.google.guava;

import com.google.common.base.MoreObjects;

public class MoreObjectsDemo {
    private String name;
    private String userId;
    private String petName;
    private String sex;

    @Override
    public String toString() {
        //prints:MoreObjectsDemo{name=testName, userId=NO1, petName=PIG}
        return MoreObjects.toStringHelper(this).add("name", "testName").add("userId", "NO1").add("petName", "PIG").omitNullValues().toString();
        //prints:MoreObjectsDemo{name=testName, userId=NO1, petName=PIG}
        //return MoreObjects.toStringHelper(this).add("name", "testName").add("userId", "NO1").add("petName", "PIG").toString();
    }
    public static void main(String[] args) {
        System.out.println(new MoreObjectsDemo());
    }
}
