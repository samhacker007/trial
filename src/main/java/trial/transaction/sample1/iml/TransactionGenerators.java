package trial.transaction.sample1.iml;

import lombok.Getter;

public enum TransactionGenerators {

	ETLTransactionGenerator("ETLTransactionGenerator");

	@Getter
	String name;

	TransactionGenerators(String name) {
		this.name = name;
	}

}
