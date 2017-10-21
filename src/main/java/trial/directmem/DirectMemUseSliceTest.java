package trial.directmem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import io.airlift.slice.Slice;
import io.airlift.slice.Slices;
import sun.nio.ch.DirectBuffer;

public class DirectMemUseSliceTest {

	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {

		}
		return null;
	}

	public static void test() {
		// 创建要被缓存的对象
		Set<String> set = new HashSet<String>();
		set.add("hello");
		set.add("world");

		// 将对象序列化为byte数组
		byte[] serializedSet = serialize(set);

		// 创建直接内存，即off-heap，同时将对象存入直接内存
		Slice slice = Slices.wrappedBuffer(serializedSet);

		// 删除原对象，释放heap空间
		set = null;
		serializedSet = null;
		System.out.println("wrote object into direct buffer");

		// 取出对象
		byte[] bytes = slice.getBytes();
		
		// 反序列化
		Set<String> set1 = (Set<String>) unserialize(bytes);
		
		// 使用对象
		Iterator itr1 = set1.iterator();
		while(itr1.hasNext()){
			System.out.println("read from deserialized object: " + itr1.next());
		}
		
		// 释放空间
		slice.clear();
		slice = null;
		System.out.println("free the diect buffer resource");

	}

	public static void main(String[] args) {
		DirectMemUseSliceTest.test();
	}

}
