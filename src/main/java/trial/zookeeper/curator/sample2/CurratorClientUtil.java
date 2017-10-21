package trial.zookeeper.curator.sample2;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

import trial.zookeeper.curator.sample1.ZkStateListener;

public class CurratorClientUtil {

	public static CuratorFramework createSimple(String connectionString) {
		// these are reasonable arguments for the ExponentialBackoffRetry. The
		// first
		// retry will wait 1 second - the second will wait up to 2 seconds - the
		// third will wait up to 4 seconds.
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);

		// The simplest way to get a CuratorFramework instance. This will use
		// default values.
		// The only required arguments are the connection string and the retry
		// policy
		CuratorFramework client = CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
		client.start();
//		initClient(client);

		return client;
	}

	public static void closeClient(CuratorFramework client){
		client.close();
		client = null;
	}
	
	private static CuratorFramework initClient(CuratorFramework client) {
		client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
			public void stateChanged(CuratorFramework client, ConnectionState state) {
				if (state == ConnectionState.LOST) {
					// 连接丢失
					System.out.println("lost session with zookeeper");
				} else if (state == ConnectionState.CONNECTED) {
					// 连接新建
					System.out.println("connected with zookeeper");
				} else if (state == ConnectionState.RECONNECTED) {
					System.out.println("reconnected with zookeeper");
					System.out.println("TODO: NOT completed...");
					// // 连接重连
					// for (ZkStateListener s : stateListeners) {
					// s.reconnected();
					// }
				}
			}
		});
		client.start();
		return client;

	}

	public static void create(CuratorFramework client, String path, byte[] payload, CreateMode createMode) throws Exception {
		// this will create the given ZNode with the given data
		client.create().withMode(createMode).forPath(path, payload);
	}
	
	public static void create(CuratorFramework client, String path, byte[] payload) throws Exception {
		create(client, path, payload, CreateMode.PERSISTENT);
	}

	public static String createEphemeralSequential(CuratorFramework client, String path, byte[] payload)
			throws Exception {
		// this will create the given EPHEMERAL-SEQUENTIAL ZNode with the given
		// data using Curator protection.

		/*
		 * Protection Mode:
		 * 
		 * It turns out there is an edge case that exists when creating
		 * sequential-ephemeral nodes. The creation can succeed on the server,
		 * but the server can crash before the created node name is returned to
		 * the client. However, the ZK session is still valid so the ephemeral
		 * node is not deleted. Thus, there is no way for the client to
		 * determine what node was created for them.
		 * 
		 * Even without sequential-ephemeral, however, the create can succeed on
		 * the sever but the client (for various reasons) will not know it.
		 * Putting the create builder into protection mode works around this.
		 * The name of the node that is created is prefixed with a GUID. If node
		 * creation fails the normal retry mechanism will occur. On the retry,
		 * the parent path is first searched for a node that has the GUID in it.
		 * If that node is found, it is assumed to be the lost node that was
		 * successfully created on the first try and is returned to the caller.
		 */
		return client.create().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, payload);
	}

	public static byte[] getData(CuratorFramework client, String path) throws Exception {
		// set data for the given node
		return client.getData().forPath(path);
	}

	public static void setData(CuratorFramework client, String path, byte[] payload) throws Exception {
		// set data for the given node
		client.setData().forPath(path, payload);
	}

	public static void delete(CuratorFramework client, String path) throws Exception {
		// delete the given node
		client.delete().forPath(path);
	}

}
