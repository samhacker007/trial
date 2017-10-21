package trial.google.guice.func;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.AbstractModule;

public class TestMain {

	// 创建一个测试程序
	public static void main(String[] args) {

		Injector in = Guice.createInjector(new MyModule());
		// 得到HelloCaller的实例
		HelloCaller1 helloCaller = in.getInstance(HelloCaller1.class);
		// 调用sayHello方法
		helloCaller.sayHello();

	}

}
