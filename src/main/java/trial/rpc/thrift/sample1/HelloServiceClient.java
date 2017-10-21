package trial.rpc.thrift.sample1;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author yogo.wang
 * @date 2017/02/21-下午2:35.
 */
public class HelloServiceClient {

	public static void main(String[] args) {
		System.out.println("客户端启动....");
		TTransport transport = null;
		try {
			transport = new TSocket("localhost", 9898, 30000);
			// 协议要和服务端一致
			TProtocol protocol = new TBinaryProtocol(transport);
//			Hello.Client client = new Hello.Client(protocol);
//			transport.open();
//			String result = client.helloString("哈哈");
//			System.out.println(result);
//			transport.close();
//			transport = null;
			
			transport = new TSocket("localhost", 9898, 30000);
			// 协议要和服务端一致
			TProtocol protocol1 = new TBinaryProtocol(transport);
			Pay.Client client1 = new Pay.Client(protocol1);
			transport.open();
			String result = client1.pay("嘻嘻");
			System.out.println(result);
			result = client1.pay1("哈哈");
			System.out.println(result);
			
			
//			transport = new TSocket("localhost", 9898, 30000);
//			// 协议要和服务端一致
//			TProtocol protocol = new TBinaryProtocol(transport);
//			Hello.Client client = new Hello.Client(protocol);
//			Pay.Client client1 = new Pay.Client(protocol);
//			transport.open();
//			String result = client.helloString("哈哈");
//			System.out.println(result);
//			
//			result = client1.pay("嘻嘻");
//			System.out.println(result);
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} finally {
			if (null != transport) {
				transport.close();
			}
		}
	}
}
