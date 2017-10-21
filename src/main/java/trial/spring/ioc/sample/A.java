package trial.spring.ioc.sample;

import org.springframework.stereotype.Service;

@Service
public class A {

	public String sayHello(String word){
		return "Hello " + word + " !";
	}
	
}
