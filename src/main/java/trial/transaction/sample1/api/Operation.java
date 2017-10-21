package trial.transaction.sample1.api;

import lombok.Getter;
import lombok.Setter;

public abstract class Operation {

	@Getter
	@Setter
	private String name;

	public Operation(String name) {
		this.name = name;
	}

	public abstract boolean commit() throws TransactionException;

	public abstract boolean undo() throws RollbackException;

}
