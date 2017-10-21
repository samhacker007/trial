package trial.annotation.lombok.sample;

public class TestMain {
	
	public void test(){
		User user = User.builder().name("Jim").age(18).build();
		System.out.println(user.getAge() + " " + user.getName());
	}

	public static void main(String[] args) {
		TestMain test = new TestMain();
		test.test();

	}

}
