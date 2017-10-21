package trial.transaction.sample1.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public abstract class TransactionBuilder {

	Transaction transaction = null;

	static private Logger log = LoggerFactory.getLogger(TransactionBuilder.class);

	@Getter
	String name;

	public TransactionBuilder(String name) {
		this.name = name;
	}

	/**
	 * Generate a transaction
	 * 
	 * @return
	 */
	public void buildTransactionBody() {
		log.info(this.getName() + " : buildTransactionBody()");
		if (isUncompletedTransactionExist())
			transaction = getUncompletedTransactions().get(0);

		transaction = generateNewTransactions();
	}

	public void buildTransactionNameWithGT() {
		log.info(this.getName() + " : buildTransactionNameWithGT()");
		if (isUncompletedTransactionExist())
			return;

		transaction.setGenerationTime(System.currentTimeMillis());
		transaction.constructNameWithGenerationTime();
	}

	public Transaction buildTransaction() {
		log.info(this.getName() + " : buildTransaction()");
		return transaction;
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
