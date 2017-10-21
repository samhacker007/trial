package trial.transaction.sample1.iml;

import lombok.Getter;

public enum Transactions {

	ETL("ETL");

	@Getter
	String name;

	Transactions(String name) {
		this.name = name;
	}

}
