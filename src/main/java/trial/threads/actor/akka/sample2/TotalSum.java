package trial.threads.actor.akka.sample2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;


public class TotalSum {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("actor-demo-java");
        ActorRef bob = system.actorOf(Greeter.props("Bob", "Howya doing"));
        ActorRef alice = system.actorOf(Greeter.props("Alice", "Happy to meet you"));
        // Actor 使用标准 tell 操作来发送消息
        bob.tell(new Greet(alice), ActorRef.noSender());
        alice.tell(new Greet(bob), ActorRef.noSender());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { /* ignore */ }
        system.shutdown();
    }

    // 不同的消息就是不同的自定义类
    // message
    private static class Greet {
        public final ActorRef target;
        
        public Greet(ActorRef actor) {
            target = actor;
        }
    }

    // message
    private static Object AskName = new Object();

    // messages
    private static class TellName {
        public final String name;
        
        public TellName(String name) {
            this.name = name;
        }
    }

    // actor implementation
    private static class Greeter extends UntypedActor {
        private final String myName;
        private final String greeting;
        
        Greeter(String name, String greeting) {
            myName = name;
            this.greeting = greeting;
        }

            // Akka 使用 Props 对象将各种配置属性传递给 actor。每个 Props 实例包装 actor 类所需的构造函数参数的一个副本，以及对该类的引用
        public static Props props(String name, String greeting) {
            return Props.create(Greeter.class, name, greeting);
          }

        // Actor处理的message实际是一个对象，而且它可以是任何类的对象。我们可以在Actor的onReceive方法里面定义对每一类对象的处理逻辑
        public void onReceive(Object message) throws Exception {
            if (message instanceof Greet) {
                ((Greet)message).target.tell(AskName, self());
                // 由于需要返回结果，所以不可以设置返回对象为 null 或者 ActorRef.noSender()
                // ((Greet) message).target.tell(AskName, ActorRef.noSender());
                System.out.println(getSelf().toString() + " is sending msg AskName to other actor " +  ((Greet)message).target.toString());
            } else if (message == AskName) {
                // sender().tell(new TellName(myName), self());
                // 由于不需要返回结果，所以可以设置返回对象为 null 或者 ActorRef.noSender()
                sender().tell(new TellName(myName), ActorRef.noSender());
                System.out.println(getSelf().toString() + " is telling other actor myname = " + myName);
            } else if (message instanceof TellName) {
                System.out.println(getSelf().toString() + " is saying: " + greeting + ", " + ((TellName)message).name);
            }
        }
    }
}
