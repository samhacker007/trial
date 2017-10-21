package trial.threads.map;

import java.util.HashMap;

public class OperatorThread implements Runnable{
	
	HashMap hashMap;
	
	public OperatorThread(HashMap hashMap){
		this.hashMap = hashMap;
	}

	@Override
	public void run() {
		
		for(int i= 0; i< 10000; i++){
			hashMap.put(i, i);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
