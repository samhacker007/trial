package trial.transaction.sample1.iml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import trial.transaction.sample1.api.TransactionManagerLogger;

//public class ZookeeperTransactionLogger implements TransactionLogger{
public class ZookeeperTransactionManagerLogger implements TransactionManagerLogger {

	static private Logger log = LoggerFactory.getLogger(ZookeeperTransactionManagerLogger.class);

	@Override
	public void writeStartTime() {
		log.info("write start log");

	}

	@Override
	public void writeSuccessRecord() {
		log.info("write success log");

	}

	@Override
	public void writeFailRecord() {
		log.info("write fail log");

	}

	@Override
	public void writeCompleteTime() {
		log.info("write complete log");

	}

	@Override
	public void writeTransactionMetaInfo() {
		log.info("write transaction meta log");

	}

}
