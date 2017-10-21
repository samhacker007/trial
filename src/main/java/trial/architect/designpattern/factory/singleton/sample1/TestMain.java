package trial.architect.designpattern.factory.singleton.sample1;

public class TestMain {

	public static void main(String[] args) {
		SingletonClass instance = SingletonClass.getInstance();
		instance.method();
	}

}
