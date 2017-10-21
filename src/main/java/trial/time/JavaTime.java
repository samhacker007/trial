package trial.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaTime {

	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String currentTime = df.format(new Date());
		System.out.println(currentTime);// new Date()为获取当前系统时间

	}

}
