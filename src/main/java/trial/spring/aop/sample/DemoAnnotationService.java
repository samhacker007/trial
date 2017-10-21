package trial.spring.aop.sample;

import org.springframework.stereotype.Service;

@Service
public class DemoAnnotationService {
	
	@Action(name="Add() Annotation Operation ")
	public void add(){System.out.println("This is the method of DemoAnnotationService");};

}
