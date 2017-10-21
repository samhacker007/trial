package trial.rpc.thrift.sample1;

import org.apache.thrift.TException;

public class HelloServiceImpl implements Hello.Iface {
    public String helloString(String para) throws TException {
    	System.out.println("HelloServiceImpl is called by client...");
        return "result:"+para;
    }
}
