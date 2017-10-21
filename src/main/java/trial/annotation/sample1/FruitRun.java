package trial.annotation.sample1;

/***********输出结果***************/
public class FruitRun {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        FruitInfoUtil.getFruitInfo(Apple.class);
        
       Apple apple = new Apple();
       System.out.println(apple.getAppleName());
        
    }

}
