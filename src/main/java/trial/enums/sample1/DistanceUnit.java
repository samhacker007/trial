package trial.enums.sample1;

public enum DistanceUnit {
	
	MILE("mile") {
		@Override
		public int convert(int dis) {
			// TODO Auto-generated method stub
			return dis*2;
		}
	};
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private DistanceUnit(String name){
		this.name = name;
	}
	
	public abstract int convert(int dis);


}
