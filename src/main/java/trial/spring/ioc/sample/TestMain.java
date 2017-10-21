package trial.spring.ioc.sample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);

		MyService myService = context.getBean(B.class);

		System.out.println(myService.SayHello("Spring"));
		
		MyService myService1 = context.getBean(B.class);
		
		// 默认就是单例模式
		System.out.println(myService == myService1);
		System.out.println(myService.equals(myService1));

		context.close();

	}

}
