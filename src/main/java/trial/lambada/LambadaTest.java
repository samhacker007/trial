package trial.lambada;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * lambda表达式的一般语法
 * 
 * (Type1 param1, Type2 param2, ..., TypeN paramN) -> { statment1; statment2;
 * //............. return statmentM; }
 * 
 * 这是lambda表达式的完全式语法，后面几种语法是对它的简化。
 * 
 * 
 * 开始使用 Java 8 的第一件事情是在实践中使用 lambda
 * 表达式和流。但是请记住：它确实非常好，好到可能会让你上瘾！但是，我们也看到了，使用传统迭代器和 for-each 循环的 Java 编程风格比 Java
 * 8 中的新方式性能高很多。
 * 
 * 当然，这也不是绝对的。但这确实是一个相当常见的例子，它显示可能会有大约 5
 * 倍的性能差距。如果这影响到系统的核心功能或成为系统一个新的瓶颈，那就相当可怕了。
 * 
 * @author liuqiyun
 *
 */
public class LambadaTest {

	private void test(int x, int y, String s) {
		// <1> 使用lambda表达式替换匿名类
		// 比如：用lambda表达式实现Runnable
		// Java 8之前：
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Before Java8, too much code for too little to do");
			}
		}).start();

		// Java 8方式：
		new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();

		// <2> 使用lambda表达式对列表进行迭代
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		features.forEach(n -> System.out.println(n));

		// <3> 使用lambda表达式和函数式接口Predicate
		List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

		System.out.println("Languages which starts with J :");
		filter(languages, (str) -> ((String) str).startsWith("J"));

		System.out.println("Languages which ends with a ");
		filter(languages, (str) -> ((String) str).endsWith("a"));

		System.out.println("Print all languages :");
		filter(languages, (str) -> true);

		System.out.println("Print no language : ");
		filter(languages, (str) -> false);

		System.out.println("Print language whose length greater than 4:");
		filter(languages, (str) -> ((String) str).length() > 4);

		// <4> 如何在lambda表达式中加入Predicate
		// 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
		// 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
		Predicate<String> startsWithJ = (n) -> n.startsWith("J");
		Predicate<String> fourLetterLong = (n) -> n.length() == 4;
		languages.stream().filter(startsWithJ.and(fourLetterLong))
				.forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long = " + n));

		// <5> Java 8中使用lambda表达式的Map和Reduce示例
		// 为每个订单加上12%的税
		// 老方法：
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		double total = 0;
		for (Integer cost : costBeforeTax) {
			double price = cost + .12 * cost;
			total = total + price;
		}
		System.out.println("Total : " + total);

		// 新方法：结合stream
		/*
		 * 两句话理解Stream：
		 * 
		 * 1.Stream是元素的集合，这点让Stream看起来用些类似Iterator； 2.可以支持顺序和并行的对原Stream进行汇聚的操作；
		 * 
		 * 大家可以把Stream当成一个装饰后的Iterator。原始版本的Iterator，用户只能逐个遍历元素并对其执行某些操作；
		 * 包装后的Stream，用户只要给出需要对其包含的元素执行什么操作，比如“过滤掉长度大于10的字符串”、“获取每个字符串的首字母”等，
		 * 具体这些操作如何应用到每个元素上，就给Stream就好了！原先是人告诉计算机一步一步怎么做，现在是告诉计算机做什么，计算机自己决定怎么做。
		 * 当然这个“怎么做”还是比较弱的。
		 */
		double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
		System.out.println("Total : " + bill);

		// <6> 多执行语句
		List<Integer> results = new ArrayList<Integer>();
		costBeforeTax.forEach((value) -> {
			int result = value * 2;
			result++;
			results.add(result);
		});
		results.forEach((value) -> {
			System.out.println("one result = " + value);
		});

		// features.forEach(() -> System.getProperty("user.name"));

		// String user = () -> System.getProperty("user.name");
		// System.out.println( () -> System.getProperty("user.name"));
		// int value = (int x, int y) -> x + y;
		// System.out.println("value=" + value);

	}

	public static void filter(List<String> names, Predicate condition) {
		for (String name : names) {
			if (condition.test(name)) {
				System.out.println(name + " ");
			}
		}
	}

	public static void main(String[] args) {
		// (int x, int y) -> x + y;
		// () -> 42;
		// (String s) -> { System.out.println(s); };

		LambadaTest obj = new LambadaTest();
		obj.test(10, 8, "HelloWorld");

	}

}
