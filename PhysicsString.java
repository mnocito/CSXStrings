package stringsLKMN;

public class PhysicsString {
	int n; //number of sections of string cord
	double mass;//mass of the string cord
	double length; //length of the string cord
	double k; //k of the overall string cord
	Mass[] masses = new Mass[n]; //array of masses
	double g = -9.81;
	double tension = 0;
	double[] oldVelocities = new double[n]; 
	public double getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
		masses = new Mass[n];
	}
	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getTension() {
		return tension;
	}
	public void setTension(double tension) {
		this.tension = tension;
	}
	public double getInitialSpringLength() {
		return length / (double) n;
	}
	public double getInitialMassTension() {
		return tension / getInitialSpringLength();
	}
	public void update(double timestep) {

	}
	
}
