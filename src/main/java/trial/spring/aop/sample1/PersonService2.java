package trial.spring.aop.sample1;

import org.springframework.stereotype.Service;

@Service
public class PersonService2 {

	public void addPerson(String personName) {
		System.out.println("add person " + personName);
	}

	public boolean deletePerson(String personName) {
		System.out.println("delete person " + personName);
		return true;
	}

	public void editPerson(String personName) {
		System.out.println("edit person " + personName);
		throw new RuntimeException("edit person throw exception");
	}

}
