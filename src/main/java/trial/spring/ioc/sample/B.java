package trial.spring.ioc.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class B implements MyService{
	
	@Autowired
	A a;
	
	public String SayHello(String word){
		return a.sayHello(word);
	}
	

}
