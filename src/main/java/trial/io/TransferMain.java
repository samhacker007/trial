package trial.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TransferMain {

	public static void main(String[] args) throws IOException {
		String file5 = "/Users/liuqiyun/tmp/5.txt";

		// 创建文件
		File file = new File(file5);
		file.createNewFile();

		// 示例1-1：字节流System.in转为字符流BufferedReader
		// 字节流-->字符流
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new FileWriter(file5));
		String line = null;
		while ((line = br.readLine()) != null) {
			if ("over".equals(line)) {
				break;
			}
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();

		// 实例1-2 字符流BufferedReader转为字节流System.out
		br = new BufferedReader(new FileReader(file5));
		// 字符流-->字节流
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		line = null;
		while ((line = br.readLine()) != null) {
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		bw.close();
		br.close();

		// 示例2-1 采用指定的编码读取文本文件
		// 在IO流中，如果想指定编码读写数据，只能使用转换流。
		// 采用指定编码从文本文件中读取内容
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file5), "UTF-8"));
		line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();

		// 实例2-2 采用指定的编码输出文本文件
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file5), "UTF-8"));
		bw.write("I am 君山");
		bw.close();
	}

}
