public class Circulo {
	private double x;
	private double y;
	private double r;
	int hasFlag;
	//Constructor
	public Circulo(double x, double y, double r, int hasFlag) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.hasFlag = hasFlag;
	}

public double getX() {
	return x;
}

public void setX(double x) {
	this.x = x;
}

public double getR() {
	return r;
}

public void setR(double r) {
	this.r = r;
}

public double getY() {
	return y;
}

public void setY(double y) {
	this.y = y;
}

public int getHasFlag() {
	return hasFlag;
}

public void setHasFlag(int hasFlag) {
	this.hasFlag = hasFlag;
}
}
