package trial.transaction.sample1.iml.etl;

import trial.transaction.sample1.api.Transaction;
import trial.transaction.sample1.api.TransactionBuilder;
import trial.transaction.sample1.api.TransactionBuilderDriver;

public class ETLTransactionBuilderDriver implements TransactionBuilderDriver{

	@Override
	public Transaction build(TransactionBuilder builder) {
		builder.buildTransactionBody();
		builder.buildTransactionNameWithGT();
		return builder.buildTransaction();
	}

}
