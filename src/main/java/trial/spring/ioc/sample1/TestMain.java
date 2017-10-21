package trial.spring.ioc.sample1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);

		MyService myService = context.getBean(B.class);

		System.out.println(myService.SayHello("Spring Wolrd"));

		context.close();

	}

}
