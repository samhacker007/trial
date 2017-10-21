package trial.transaction.sample1.api;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import lombok.Getter;
import lombok.Setter;

public abstract class Transaction {

	@Getter
	private List<Operation> operations;
	@Getter
	private Stack<Operation> startedOperations = new Stack<Operation>();
	@Getter
	@Setter
	private String name;
	private long timeout = -1;
	@Getter
	@Setter
	private long generationTime;
	@Getter
	@Setter
	private String nameWithGenerationTime;

	private static final long TRANSACTION_TIMEOUT_MS = 1000 * 60 * 10;
	
	public Transaction(Operation... operations) {
		this.operations = Arrays.asList(operations);
	}

	public Transaction(String name, Operation... operations) {
		this.name = name;
		this.operations = Arrays.asList(operations);
	}

	public Transaction(String name, long timeout, Operation... operations) {
		this.timeout = timeout;
		this.name = name;
		this.operations = Arrays.asList(operations);
	}

	public long getTimeout() {
		if (timeout == -1)
			return TRANSACTION_TIMEOUT_MS;
		return timeout;
	}
	
	public void constructNameWithGenerationTime(){
		this.setNameWithGenerationTime(this.getName() + "_"  + this.getGenerationTime());
	}
}
