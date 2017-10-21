package trial.transaction.sample1.iml.etl;


import trial.transaction.sample1.api.Transaction;
import trial.transaction.sample1.api.TransactionManager;
import trial.transaction.sample1.api.TransactionManagerLogger;
import trial.transaction.sample1.iml.TransactionManagers;

public class ETLTransactionManager extends TransactionManager{

	public ETLTransactionManager(Transaction transaction,
			TransactionManagerLogger transactionManagerlogger) {
		super(TransactionManagers.ETLTransactionManager.getName(), transaction, transactionManagerlogger);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean preTransaction() {
		log.info(this.getName() + " : preTransaction()");
		return true;
	}

	@Override
	public boolean afterTransaction() {
		log.info(this.getName() + " : afterTransaction()");
		return true;
	}

}
