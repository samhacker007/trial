package trial.architect.designpattern.factory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChinaFood implements Food{

	@Override
	public void eat() {
		log.info("This is ChinaFood");
	}

}
