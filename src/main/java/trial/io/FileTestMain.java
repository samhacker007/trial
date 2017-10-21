package trial.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileTestMain {
	public static byte[] getFileBytes(String filePath) throws IOException{
		File file = new File(filePath);
		FileInputStream  fis = new FileInputStream(file);
		byte[] fileBytes = new byte[1000];
		fis.read(fileBytes);
		return fileBytes;
		
	}
	
	public static void main(String[] args) throws IOException {
		String filePath = "/Users/liuqiyun/tmp/config.txt";
		
		byte[] fileBytes = FileTestMain.getFileBytes(filePath);
		
		System.out.println(new String(fileBytes, "UTF-8"));

	}

}
