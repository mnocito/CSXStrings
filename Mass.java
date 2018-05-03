package stringsLKMN;

import org.opensourcephysics.display.Circle;

public class Mass extends Circle {
	private double mass;
	private double v;
	private double a;


	public Mass (double mass, double v, double a) {
		this.mass = mass;
		this.v = v;
		this.a = a;
	}
	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public double getV() {
		return v;
	}
	public void setV(double v) {
		this.v = v;
	}
	public double getA() {
		return a;
	}
	public void setA(double a) {
		this.a = a;
	}
	public double getDistance (Mass mass2) {
		double distance = Math.sqrt(Math.pow((getX()-mass2.getX()), 2) + Math.pow((getY()-mass2.getY()), 2));
		return distance;
	}
	
}
