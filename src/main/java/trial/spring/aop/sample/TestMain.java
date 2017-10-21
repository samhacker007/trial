package trial.spring.aop.sample;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

		DemoAnnotationService demoAnnotationService = context.getBean(DemoAnnotationService.class);
		
		DemoMethodService demoMethodService = context.getBean(DemoMethodService.class);
		
		demoAnnotationService.add();
		
		demoMethodService.add();

		context.close();

	}

}
