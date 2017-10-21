package trial.transaction.sample1.api;

public class TransactionException extends Exception {

	private static final long serialVersionUID = 994798122053539237L;

	public TransactionException() {
		super();
	}

	public TransactionException(String msg) {
		super(msg);
	}

	public TransactionException(String message, Throwable cause) {
		super(message, cause);
	}

	public TransactionException(Throwable cause) {
		super(cause);
	}

}
