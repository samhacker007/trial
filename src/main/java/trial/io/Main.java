package trial.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Main {

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		String str = "中国人";

		String file1 = "/Users/liuqiyun/tmp/1.txt";
		String file2 = "/Users/liuqiyun/tmp/2.txt";
		String file3 = "/Users/liuqiyun/tmp/3.txt";
		
		// 创建文件
		File file = new File(file1);
		file.createNewFile();
		file = new File(file2);
		file.createNewFile();
		file = new File(file3);
		file.createNewFile();
		file = null;

		// 方式一【使用字节流输入，字节流输出。使用了字节缓冲区】
		FileOutputStream fos = new FileOutputStream(file1);
		fos.write(str.getBytes("UTF-8"));
		fos.close();

		FileInputStream fr = new FileInputStream(file1);
		byte[] buf = new byte[1024];
		int len;
		String myStr = "";
		while ((len = fr.read(buf)) != -1) {
			myStr += new String(buf, 0, len, "UTF-8");
		}
		// String myStr = new String(buf, 0, len, "UTF-8"); //方式二
		System.out.println(myStr);

		// 方式二【使用字符流输入，字符流输出。使用了字符缓冲区】
		FileWriter fw = new FileWriter(file2);
		fw.write(str);
		fw.close();

		FileReader fr2 = new FileReader(file2);
		char[] buf2 = new char[1024];
		int len2 = fr2.read(buf2);
		String myStr2 = new String(buf2, 0, len2);
		System.out.println(myStr2);

		// 方式三【使用字符流输入，字符流输出，没有使用缓冲区，直接读行】
		PrintWriter pw = new PrintWriter(file3, "utf-8");
		pw.write(str);
		pw.close();

		// BufferedReader 主要为了可以一行一行地读取文件
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file3), "utf-8"));
		String myStr3 = br.readLine();
		while(myStr3 != null){
			System.out.println(myStr3);
			myStr3 = br.readLine();
		}
		br.close();
		

	}

}
