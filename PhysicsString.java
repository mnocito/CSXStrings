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
	public double getInitialK() {
		return tension / getInitialSpringLength();
	}
	public void update(double timestep) {
		masses[0].setAx((masses[0].getFx(masses[1])) / masses[0].getMass());
		masses[0].setAy((masses[0].getFy(masses[1])) / masses[0].getMass());
		for (int i = 1; i < n - 1; i++) {
			masses[i].setAx((-masses[i].getFx(masses[i - 1]) + masses[i].getFx(masses[i + 1])) / masses[i].getMass());
			masses[i].setAy((-masses[i].getFy(masses[i - 1]) + masses[i].getFy(masses[i + 1])) / masses[i].getMass());
		}
		masses[masses.length - 1].setAx((-masses[masses.length - 1].getFx(masses[masses.length - 2])) / masses[masses.length - 1].getMass());
		masses[masses.length - 1].setAy((-masses[masses.length - 1].getFy(masses[masses.length - 2])) / masses[masses.length - 1].getMass());
		for (int i = 0; i < n; i++) {
			masses[i].setVx(masses[i].getVx() + masses[i].getAx() * timestep);	
			masses[i].setVy(masses[i].getVy() + masses[i].getAy() * timestep);	
		}
		for (int i = 0; i < n - 1; i++) {
			masses[i].setXY(masses[i].getX() + masses[i].getVx() * timestep, masses[i].getY() + masses[i].getVy() * timestep);
			if (masses[i].getX() >= masses[i + 1].getX()) {
				masses[i].setX(masses[i + 1].getX());
			}	
		}
		masses[masses.length - 1].setXY(masses[masses.length - 1].getX() + masses[masses.length - 1].getVx() * timestep, masses[masses.length - 1].getY() + masses[masses.length - 1].getVy() * timestep);

	}
	
}
