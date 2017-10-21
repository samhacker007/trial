package trial.fsm.sample1;

public class MyThread {

	MyThreadState myThreadState;

	public MyThread(MyThreadState myThreadState) {
		this.myThreadState = myThreadState;
	}
	
	public void go() {
		myThreadState.go(this);

	}

	public void suspend() {
		myThreadState.suspend(this);
	}

	public void die() {
		myThreadState.die(this);
	}
	
	public static void main(String args[]){
		MyThreadState initState = MyThreadState.NEW;
		
		MyThread testThread = new MyThread(initState);
		
		System.out.println("-----Expected state is new, and actual state is " + testThread.myThreadState.getName());
		testThread.go();
		System.out.println("-----Expected state is ready, and actual state is " + testThread.myThreadState.getName());
		testThread.go();
		System.out.println("-----Expected state is running, and actual state is " + testThread.myThreadState.getName());
		testThread.suspend();
		System.out.println("-----Expected state is ready, and actual state is " + testThread.myThreadState.getName());
		testThread.go();
		System.out.println("-----Expected state is running, and actual state is " + testThread.myThreadState.getName());
		testThread.die();
		System.out.println("-----Expected state is dead, and actual state is " + testThread.myThreadState.getName());
		testThread.go();;
		System.out.println("-----Expected state is dead, and actual state is " + testThread.myThreadState.getName());
	}

}
