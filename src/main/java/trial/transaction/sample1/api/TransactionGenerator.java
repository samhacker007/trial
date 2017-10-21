package trial.transaction.sample1.api;

import java.util.List;

import lombok.Getter;

/**
 * Generate a transaction for execution.
 * 
 * TODO: If we need the transaction to work in a multi-thread env, we should
 * refactor this class.
 * 
 * 
 * @author liuqiyun
 *
 */
public abstract class TransactionGenerator {

	@Getter
	String name;

	public TransactionGenerator(String name) {
		this.name = name;
	}

	/**
	 * Generate a transaction
	 * 
	 * @return
	 */
	public Transaction generateTransaction() {
		if (isUncompletedTransactionExist())
			return getUncompletedTransactions().get(0);
		
		Transaction trasaction = generateNewTransactions();
		trasaction.setGenerationTime(System.currentTimeMillis());
		trasaction.constructNameWithGenerationTime();
		
		return trasaction;
	}

	/**
	 * If a transaction execution stopped unexpectedly, its transaction log will
	 * remain in the transaction log, and it should be expired. Under the
	 * circumstance, this method should return true
	 * 
	 * @return Transaction
	 */
	public abstract boolean isUncompletedTransactionExist();

	public abstract List<Transaction> getUncompletedTransactions();

	/**
	 * Generate a transaction and construct it with default operations
	 * 
	 * @return
	 */
	protected abstract Transaction generateNewTransactions();

}
