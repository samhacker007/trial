package trial.google.guice.func;

import com.google.inject.Inject;  

public class HelloCaller1 {  
    //包含一个Hello属性  
    private Hello hello ;  
    //@Inject注解写在构造方法上，通过构造方法的方式注入属性hello  
    @Inject  
    public HelloCaller1(Hello hello){  
        this.hello = hello ;  
    }  
      
    public void sayHello(){  
        hello.sayHello() ;  
    }  
} 
