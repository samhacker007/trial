package trial.transaction.sample1;

import trial.transaction.sample1.api.Transaction;
import trial.transaction.sample1.api.TransactionManager;
import trial.transaction.sample1.iml.ZookeeperTransactionManagerLogger;
import trial.transaction.sample1.iml.etl.ETLTransactionBuilder;
import trial.transaction.sample1.iml.etl.ETLTransactionBuilderDriver;
import trial.transaction.sample1.iml.etl.ETLTransactionManager;

public class TestMain {

	public void testExecuteNewTransaction() {
		// 1. Get a Transaction
		Transaction etlTransaction = new ETLTransactionBuilderDriver().build(new ETLTransactionBuilder());

		// 2. Get a TransactionManager
		TransactionManager etlTransactionManager = new ETLTransactionManager(etlTransaction,
				new ZookeeperTransactionManagerLogger());

		// 3. Execute the transaction by TransactionManager
		etlTransactionManager.commit();
	}

	public static void main(String[] args) {
		new TestMain().testExecuteNewTransaction();
	}

}
