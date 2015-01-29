package apiit.nibras.studentms.model.types;


public enum Grade {
	A(100.0, 70.1), B(70.0, 60.1), C(60.0, 50.1), D(50.0, 40.1), E(40.0, 30.1), W(
			30.0, 0.0);

	private final double MAX;
	private final double MIN;

	private Grade(double mAX, double mIN) {
		this.MAX = mAX;
		this.MIN = mIN;
	}

	public static Grade create(double score) {
		if (score > 70.0)
			return A;
		else if (score > 60.0)
			return B;
		else if (score > 50.0)
			return C;
		else if (score > 40.0)
			return D;
		else if (score > 30.0)
			return E;
		else
			return W;
	}

	public double getMAX() {
		return this.MAX;
	}

	public double getMIN() {
		return this.MIN;
	}

}
