package trial.spring.aop.sample1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
		PersonService personService = context.getBean(PersonService.class);
		String personName = "Jim";
		personService.addPerson(personName);
//		personService.deletePerson(personName);
//		personService.editPerson(personName);
		context.close();
	}
}
