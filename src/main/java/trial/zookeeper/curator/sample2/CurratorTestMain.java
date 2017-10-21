package trial.zookeeper.curator.sample2;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

public class CurratorTestMain {

	public void testCreatePath(String connectionString, String path) throws Exception {
		CuratorFramework client = CurratorClientUtil.createSimple(connectionString);
		String payload = "This is test content of a path";
		CurratorClientUtil.create(client, path, payload.getBytes(), CreateMode.PERSISTENT);
		System.out.println("Get the content of the path: " + new String(CurratorClientUtil.getData(client, path)));

		// delete the path
		CurratorClientUtil.delete(client, path);

		// close the client
		CurratorClientUtil.closeClient(client);
	}

	public static void main(String[] args) throws Exception {
		String connectionString = "localhost:2181";
		String path = "/tmp/test01";
		CurratorTestMain test = new CurratorTestMain();
		test.testCreatePath(connectionString, path);
	}

}
