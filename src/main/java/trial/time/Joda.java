package trial.time;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class Joda {

	public static void getLocalTimeUsingUTCTime() {
		System.out.println("========1. 得到统一的UTC时间值============");
		// 默认设置为日本时间
		DateTimeZone.setDefault(DateTimeZone.forID("Asia/Tokyo"));
		DateTime dt1 = new DateTime();
		System.out.println(DateTimeZone.getDefault().getID() + "时区的当地时间：" + dt1.toDateTime().toString());
		long utcTime = dt1.getMillis();
		// 因为Druid的时间戳到秒级别，所以取utcTime/1000的值
		System.out.println("无论应用跑在那个时区，都会返回同样的UTC时间值：" + utcTime/1000);

		// 伦敦时间
		DateTimeZone.setDefault(DateTimeZone.forID("Europe/London"));
		dt1 = new DateTime();
		System.out.println(DateTimeZone.getDefault().getID() + "时区的当地时间：" + dt1.toDateTime().toString());
		utcTime = dt1.getMillis();
		System.out.println("无论应用跑在那个时区，都会返回同样的UTC时间值：" + utcTime/1000);

		// 伦敦时间
		DateTimeZone.setDefault(DateTimeZone.forID("Asia/Shanghai"));
		dt1 = new DateTime();
		System.out.println(DateTimeZone.getDefault().getID() + "时区的当地时间：" + dt1.toDateTime().toString());
		utcTime = dt1.getMillis();
		System.out.println("无论应用跑在那个时区，都会返回同样的UTC时间值：" + utcTime/1000);
//		long utcTime = dt1.getMillis();

		System.out.println("========2. 根据UTC时间值，得到不同Timezone的当地时间============");
		// long utcTime = 1479104505299L;
		DateTime dt4 = new DateTime(utcTime, DateTimeZone.forID("America/Los_Angeles"));
		System.out.println("America/Los_Angeles 的当地时间为：" + dt4.toDateTime().toString());

		dt4 = new DateTime(utcTime, DateTimeZone.forID("UTC"));
		System.out.println("UTC 的当地时间为：" + dt4.toDateTime().toString());

		dt4 = new DateTime(utcTime, DateTimeZone.forID("Asia/Shanghai"));
		System.out.println("Asia/Shanghai 的当地时间为：" + dt4.toDateTime().toString());

		dt4 = new DateTime(utcTime, DateTimeZone.forID("Asia/Tokyo"));
		System.out.println("Asia/Tokyo 的当地时间为：" + dt4.toDateTime().toString());
	}

	public static void main(String[] args) {
		Joda.getLocalTimeUsingUTCTime();
		
		
	}

}
