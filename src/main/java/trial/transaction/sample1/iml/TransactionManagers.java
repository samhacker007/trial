package trial.transaction.sample1.iml;

import lombok.Getter;

public enum TransactionManagers {
	
	ETLTransactionManager("ETLTransactionManager");
	
	@Getter
	String name;
	
	TransactionManagers(String name){
		this.name = name;
	}

}
