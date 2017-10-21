package trial.transaction.sample1.iml;

import lombok.Getter;

public enum TransactionBuilders {

	ETLTransactionBuilder("ETLTransactionBuilder");

	@Getter
	String name;

	TransactionBuilders(String name) {
		this.name = name;
	}

}
