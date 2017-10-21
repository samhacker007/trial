package trial.fsm.sample1;


public enum MyThreadState {

	NEW("new") {

		@Override
		public void go(MyThread myThread) {
			super.exit(myThread, READY);
			myThread.myThreadState = READY;

		}

		@Override
		public void suspend(MyThread myThread) {
			nothingTodo();

		}

		@Override
		public void die(MyThread myThread) {
			nothingTodo();

		}
	},

	READY("ready") {

		@Override
		public void go(MyThread myThread) {
			super.exit(myThread, RUNNING);
			myThread.myThreadState = RUNNING;

		}

		@Override
		public void suspend(MyThread myThread) {
			nothingTodo();

		}

		@Override
		public void die(MyThread myThread) {
			super.exit(myThread, DEAD);
			myThread.myThreadState = DEAD;

		}
	},

	RUNNING("running") {

		@Override
		public void go(MyThread myThread) {
			nothingTodo();

		}

		@Override
		public void suspend(MyThread myThread) {
			super.exit(myThread, READY);
			myThread.myThreadState = READY;

		}

		@Override
		public void die(MyThread myThread) {
			super.exit(myThread, DEAD);
			myThread.myThreadState = DEAD;

		}
	},

	DEAD("dead") {

		@Override
		public void go(MyThread myThread) {
			nothingTodo();

		}

		@Override
		public void suspend(MyThread myThread) {
			nothingTodo();

		}

		@Override
		public void die(MyThread myThread) {
			nothingTodo();
		}
	};

	private String name;

	MyThreadState(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public abstract void go(MyThread myThread);

	public abstract void suspend(MyThread myThread);

	public abstract void die(MyThread myThread);
	
	public void nothingTodo(){
		System.out.println("Nothing to do");
	}

	public void exit(MyThread myThread, MyThreadState nextState) {
		System.out.println(this.getName() + " will switch to new state: " + nextState.getName());
	}
}
