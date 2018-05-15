package stringsLKMN;

import org.opensourcephysics.display.Circle;

public class Mass extends Circle {
	private double mass;
	private double vx;
	private double vy;
	
	private double ax;
	private double ay;
	
	private double fx;
	private double fy;
	
	private double length;

	
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
		this.length = 0;
	}
	private double k;


	public Mass (double mass, double vx, double vy, double ax, double ay) {
		this.mass = mass;
		this.vx = vx;
		this.vy = vy;
		this.ax = ax;
		this.ay = ay;
	}
	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public double getVx() {
		return vx;
	}
	public void setVx(double vx) {
		this.vx = vx;
	}
	public double getVy() {
		return vy;
	}
	public void setVy(double vy) {
		this.vy = vy;
	}
	public double getAx() {
		return ax;
	}
	public void setAx(double ax) {
		this.ax = ax;
	}
	public double getAy() {
		return ay;
	}
	public void setAy(double ay) {
		this.ay = ay;
	}
	public double getK() {
		return k;
	}
	public void setK(double k) {
		this.k = k;
	}
	public double getDistance (Mass mass2) {
		double distance = Math.sqrt(Math.pow((getX()-mass2.getX()), 2) + Math.pow((getY()-mass2.getY()), 2));
		return distance;
	}
	public double getFx (Mass i) {
		fx = 0;
		fx=k	*(getDistance(i)-length)*((getX()-i.getX())/getDistance(i));

		return fx;	
	}
	public double getFy (Mass i) {
		fy = 0;
		fy=k	*(getDistance(i)-length)*((getY()-i.getY())/getDistance(i));

		return fy;	
	}
	
}
