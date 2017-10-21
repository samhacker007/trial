package trial.spring.ioc.sample1;


public class B implements MyService{
	
	A a;
	
	public void setA(A a){
		this.a = a;
	}
	
	public String SayHello(String word){
		return a.sayHello(word);
	}
	

}
