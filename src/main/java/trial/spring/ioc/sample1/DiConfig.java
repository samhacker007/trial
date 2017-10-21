package trial.spring.ioc.sample1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiConfig {
	
	@Bean
	public A a(){
		return new A();
	}
	
	@Bean
	public B b(){
		B b = new B();
		b.setA(a());
		return b;
	}

}
