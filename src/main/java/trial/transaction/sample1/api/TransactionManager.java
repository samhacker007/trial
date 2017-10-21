package trial.transaction.sample1.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import trial.transaction.sample1.iml.ZookeeperTransactionManagerLogger;

public abstract class TransactionManager {

	protected static Logger log = LoggerFactory.getLogger(TransactionManager.class);

	@Getter
	String name;
	Transaction transaction;
	private TransactionManagerLogger transactionManagerlogger;

	public TransactionManager(String name, Transaction transaction, TransactionManagerLogger transactionManagerlogger) {
		this.name = name;
		this.transaction = transaction;
		this.transactionManagerlogger = transactionManagerlogger;
	}

	public void commit() {
		log.info(this.getName() + " : commit() " + transaction.getNameWithGenerationTime());
		try {
			preTransaction();
			// 挨个执行Transaction里的所有Operation
			for (Operation operation : transaction.getOperations()) {
				transaction.getStartedOperations().push(operation);
				// TODO: Add log info into the log

				operation.commit();
			}
		} catch (TransactionException e) {
			System.out.println(transaction.getName() + " encountered exception during commit and will be rollback");
			rollback();
		} finally {
			afterTransaction();
		}

	}

	public void rollback() {
		log.info(this.getName() + " : rollback()");
		try {
			while (!transaction.getStartedOperations().isEmpty())
				transaction.getStartedOperations().pop().undo();
		} catch (RollbackException e) {
			System.out.println(transaction.getName() + " encountered exception during rollback!");
		}

	}

	/*
	 * 比如纪录一下事务信息到Zookeeper上
	 */
	public abstract boolean preTransaction();

	/*
	 * 比如清除一下事务信息到Zookeeper上
	 */
	public abstract boolean afterTransaction();

}
