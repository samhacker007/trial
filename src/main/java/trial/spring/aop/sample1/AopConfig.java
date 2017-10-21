package trial.spring.aop.sample1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.testin.spring.aop.sample1")
@EnableAspectJAutoProxy
public class AopConfig {
	

}
