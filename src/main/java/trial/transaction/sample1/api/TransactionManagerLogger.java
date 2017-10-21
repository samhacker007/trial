package trial.transaction.sample1.api;

public interface TransactionManagerLogger {
	
	public void writeStartTime();
	
	public void writeSuccessRecord();
	
	public void writeFailRecord();
	
	public void writeCompleteTime();
	
	public void writeTransactionMetaInfo();

}
