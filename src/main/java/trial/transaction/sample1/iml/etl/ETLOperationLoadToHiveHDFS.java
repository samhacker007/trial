package trial.transaction.sample1.iml.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import trial.transaction.sample1.api.Operation;
import trial.transaction.sample1.api.RollbackException;
import trial.transaction.sample1.api.TransactionException;

public class ETLOperationLoadToHiveHDFS extends Operation {

	public ETLOperationLoadToHiveHDFS() {
		super(ETLOperations.LOAD_TO_HIVEHDFS.getName());
	}

	static private Logger log = LoggerFactory.getLogger(ETLOperationLoadToHiveHDFS.class);

	@Override
	public boolean commit() throws TransactionException {
		log.info(this.getName() + " : commit()");
//		throw new TransactionException();
		return true;
	}

	@Override
	public boolean undo() throws RollbackException {
		log.info(this.getName() + " : undo()");
		return true;
	}

}
