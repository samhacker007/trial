package trial.rpc.thrift.sample1;

import org.apache.thrift.TException;

public class PayServiceImpl implements Pay.Iface{

	@Override
	public String pay(String para) throws TException {
		System.out.println("PayServiceImpl pay is called by client...");
		return "payed in 0";
	}

	@Override
	public String pay1(String para) throws TException {
		System.out.println("PayServiceImpl pay1 is called by client...");
		return "payed in 1";
	}

}
