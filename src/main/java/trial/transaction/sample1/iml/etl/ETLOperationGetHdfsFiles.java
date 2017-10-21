package trial.transaction.sample1.iml.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import trial.transaction.sample1.api.Operation;
import trial.transaction.sample1.api.RollbackException;
import trial.transaction.sample1.api.TransactionException;

public class ETLOperationGetHdfsFiles extends Operation {

	public ETLOperationGetHdfsFiles() {
		super(ETLOperations.GET_HDFS_FILES.getName());
	}

	static private Logger log = LoggerFactory.getLogger(ETLOperationGetHdfsFiles.class);

	@Override
	public boolean commit() throws TransactionException {
		log.info(this.getName() + " : commit()");
		return true;
	}

	@Override
	public boolean undo() throws RollbackException {
		log.info(this.getName() + " : undo()");
		return true;
	}

}
