package trial.threads.lock.redis.sample1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lock {

	private /*volatile*/ int lockTimes = 0;
	
}
