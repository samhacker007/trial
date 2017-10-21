package trial.transaction.sample1.api;

/**
 * Use Builder design pattern to build the transaction
 * 
 * @author liuqiyun
 *
 */
public interface TransactionBuilderDriver {
	
	public Transaction build(TransactionBuilder builder);

}
