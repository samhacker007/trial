package trial.architect.designpattern.buiilder.sample1;

/**
 * 
 * 建造者模式是在当创建复杂对象的复杂算法一个独立与该对象的组成部分以及它们的装配方式时适用的模式。
 * 
 * 建造者模式，顾名思义的就是类似建房子，有一个固定的流程。在大话设计模式中，作者举了一个例子大概意思是同一道菜在中国的每一个地方都有不同的味道（LZ印象最深的是鱼香肉丝，来北方最之后印象最深的是宫保鸡丁。哈哈），而肯德基的鸡腿、汉堡在每一个城市都是一样的味道。我觉的这一个例子可以清楚的认识到建造者模式有一个固定的建造过程。建造者模式实现了依赖倒转原则，抽象不应该依赖细节，细节应该依赖与抽象。建造者模式的定义是：将一个复杂对象的构造与它的表示分离，使同样的构建过程可以创建不同的表示，这样的设计模式被称为建造者模式。

　　建造者模式的角色定义，在建造者模式中存在以下4个角色：

　　1 builder:为创建一个产品对象的各个部件指定抽象接口。

　　2 ConcreteBuilder:实现Builder的接口以构造和装配该产品的各个部件，定义并明确它所创建的表示，并提供一个检索产品的接口。

　　3 Director:构造一个使用Builder接口的对象。

　　4 Product:表示被构造的复杂对象。ConcreteBuilder创建该产品的内部表示并定义它的装配过程，包含定义组成部件的类，包括将这些部件装配成最终产品的接口。

与工程模式的区别就是：工厂模式关注的是创建单个产品，而建造者模式则关注创建复合对象（产品的组合）；或者，工厂模式关注的是创建单个零件，而建造者模式则关注创建整个产品（零件的组合）。
建造者模式的尺度比幾種工廠樣式的尺度大一些，建造者樣式常常用來組裝零件，工廠樣式常常用來建構零件。 
因此，是选择工厂模式还是建造者模式，依实际情况而定。
 * 
 * @author liuqiyun
 *
 */
public interface CarBuilder {

	public void buildWheel();

	public void buildSeats();

	public void buildEngine();
	
	public Car buildCar();
}
