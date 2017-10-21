package trial.transaction.sample1.iml.etl;

import trial.transaction.sample1.api.Operation;
import trial.transaction.sample1.api.Transaction;
import trial.transaction.sample1.iml.Transactions;

public class ETLTransaction extends Transaction{
	
	
	public ETLTransaction(Operation... operations) {
		super(operations);
		this.setName(Transactions.ETL.getName());
	}
	

}
