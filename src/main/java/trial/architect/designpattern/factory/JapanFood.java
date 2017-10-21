package trial.architect.designpattern.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JapanFood implements Food{

	@Override
	public void eat() {
		log.info("This is JapanFood");
	}
}
