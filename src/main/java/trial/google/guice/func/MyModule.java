package trial.google.guice.func;

import com.google.inject.AbstractModule;

public class MyModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Hello.class).to(HelloImpl.class); 
		
	}  

}
