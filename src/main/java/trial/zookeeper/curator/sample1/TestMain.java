package trial.zookeeper.curator.sample1;

public class TestMain {

	public static void main(String[] args) throws Exception {
		String zkServers = "localhost:2181";
		CuratorZookeeperClient client = CuratorZookeeperClient.getInstance(zkServers);
		String path = "/tmp1";
		String content = "This is test content of a path";
		client.write(path, content);
//		client.delete(path);

	}

}
