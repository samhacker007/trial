package trial.google.guice.prop;

import com.google.inject.ImplementedBy;  

//将这个接口和实现类HelloImpl绑定到一起  
@ImplementedBy(HelloImpl.class)  
public interface Hello {  
  void sayHello() ;  
}  
