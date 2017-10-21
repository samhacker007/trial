package trial.transaction.sample1.iml.etl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import trial.transaction.sample1.api.Operation;
import trial.transaction.sample1.api.Transaction;
import trial.transaction.sample1.api.TransactionGenerator;
import trial.transaction.sample1.iml.TransactionGenerators;


public class ETLTransactionGenerator extends TransactionGenerator{
	
	static private Logger log = LoggerFactory.getLogger(ETLTransactionGenerator.class); 

	public ETLTransactionGenerator() {
		super(TransactionGenerators.ETLTransactionGenerator.getName());
	}

	@Override
	public boolean isUncompletedTransactionExist() {
		log.info(this.getName() + " : isUncompletedTransactionExist()");
		return false;
	}

	@Override
	public List<Transaction> getUncompletedTransactions() {
		log.info(this.getName() + " : getUncompletedTransactions()");
		return null;
	}

	@Override
	protected Transaction generateNewTransactions() {
		log.info(this.getName() + " : generateNewTransactions()");

		Operation[] operations = new Operation[5];
		
		operations[0] = new ETLOperationGetHdfsFiles();
		operations[1] = new ETLOperationGetNewSchema();
		operations[2] = new ETLOperationUpdateNewSchema();
		operations[3] = new ETLOperationLoadToHiveHDFS();
		operations[4] = new ETLOperationUpdateHdfsFilelist();
		
		return new ETLTransaction(operations);
	}

}
