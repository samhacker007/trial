package trial;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import jline.internal.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMain {

	class User implements Cloneable {
		String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Object clone() {
			User o = null;
			try {
				o = (User) super.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return o;
		}

	}

	public void test3() {
		User user1 = new User();
		user1.setName("Jim");

		User user2 = user1;
		User user3 = user1;

		System.out.println(user2 == user3);
		System.out.println(user2.equals(user3));

		user1 = null;
		System.out.println(user2.getName());
	}

	public void test() {
		// String obj1 = "a";
		// String obj2 = requireNonNull(obj1, "Can not be null!");

		User user1 = new User();
		user1.setName("sam");

		Optional<User> op = Optional.ofNullable(user1);

		System.out.println(op.isPresent());
		System.out.println(op.map(user -> user.getName()).get());

		User user2 = op.get();
		user2.setName("Jim");

		System.out.println(op.map(user -> user.getName()).get());

		User user3 = (User) user2.clone();
		user3.setName("Tom");

		System.out.println(op.map(user -> user.getName()).get());
		System.out.println(user3.getName());

	}

	public void test1() {
		Set<String> set = new HashSet<String>();
		set.add("c");
		set.add("b");
		set.add("a");
		set.add("d");
		set.add("e");
		set.add("e");

		Iterator itr1 = set.iterator();

		while (itr1.hasNext()) {
			System.out.println(itr1.next());
		}

		Set<Integer> set1 = new TreeSet<Integer>();
		set1.add(3);
		set1.add(5);
		set1.add(4);
		set1.add(1);
		set1.add(2);

		Iterator itr2 = set1.iterator();

		while (itr2.hasNext()) {
			System.out.println(itr2.next());
		}

		Vector<Integer> vec = new Vector<Integer>();
		vec.add(3);
		vec.add(5);
		vec.add(4);
		vec.add(1);
		vec.add(2);

		Iterator itr3 = vec.iterator();

		while (itr3.hasNext()) {
			System.out.println(itr3.next());
		}

	}

	public static void test2() {
		byte a = 'a';
		byte b = 127;
		char c = 200;
		char d = 'd';
		char e = '中';
		short f = 10000;
		int g = 1000000000;
		long h = 1000000000000000000L;
		float i = 0.19f;
		double j = 0.19;
		boolean k = true;

		byte a1 = (byte) 0xA5;
		System.out.println(a1);

		char c1 = (char) 0xA5;
		System.out.println(c1);

		char c2 = '&';
		System.out.println(c2);

	}

	public int addNums(Integer... num) {
		int result = 0;

		for (Integer current : num)
			result += current;

		return result;
	}
	
	public void testLogger(){
		log.info("This is from logger");
		
	}

	public static void main(String[] args) throws InterruptedException {
//		String oldValue = "15_1900890";
//		int start = oldValue.indexOf('_') + 1;
//		int end = oldValue.length();
//		System.out.println(oldValue.substring(start, end));
//		long lockTimestamp = Long.valueOf(oldValue.substring(oldValue.indexOf('_'), oldValue.length()));
//		long currentTimestamp = System.currentTimeMillis();
		// return ((currentTimestamp - lockTimestamp) > 1000);

		// new TestMain().test3();
		
//		byte[] bytes = {};
//		
//		System.out.println(new TestMain().addNums(1, 2, 3));
//		System.out.println(new TestMain().addNums());
		
//		List<String> list = new ArrayList<String>();
//		list.add("a");
//		list.add("c");
//		list.add("b");
//		
//		for(String str : list)
//			System.out.println(str);
		
//		System.out.println(ProcessUtil.getProcessID());
//		
//		Thread.sleep(50000);
		
//		 long generationTime = System.currentTimeMillis();
//		 String nameWithGenerationTime = "ETL";
//		 String x = nameWithGenerationTime + "_" + generationTime;
//		 Log.info(x);
		
		new TestMain().testLogger();

		
	}

}
