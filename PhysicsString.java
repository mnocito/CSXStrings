package stringsLKMN;

public class PhysicsString {
	int n; //number of sections of bungee cord
	double mass;//mass of the bungee cord
	double length; //length of the bungee cord
	double k; //k of the overall bungee cord
	Mass[] masses = new Mass[n]; //array of masses
	double g = -9.81;
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
	public double getK() {
		return k;
	}
	public void setK(double k) {
		this.k = k;
	}
	public double getInitialSpringLength() {
		return length / (double) n;
	}
	public void update(double timestep) {

		masses[0].setAx(((masses[0].getMass() * g) + (((double) n * k) * (Math.abs(masses[1].getY() - masses[0].getY()) - getInitialSpringLength()))) / masses[0].getMass());		
		masses[0].setAy(((masses[0].getMass() * g) + (((double) n * k) * (Math.abs(masses[1].getY() - masses[0].getY()) - getInitialSpringLength()))) / masses[0].getMass());		

		for (int i = 1; i < n - 1; i++) {
			masses[i].setAx(((masses[i].getMass() * g) - (((double) n * k) * (Math.abs(masses[i].getY() - masses[i - 1].getY()) - getInitialSpringLength())) + (((double) n * k) * (Math.abs(masses[i + 1].getY() - masses[i].getY()) - getInitialSpringLength()))) / masses[i].getMass());
			masses[i].setAy(((masses[i].getMass() * g) - (((double) n * k) * (Math.abs(masses[i].getY() - masses[i - 1].getY()) - getInitialSpringLength())) + (((double) n * k) * (Math.abs(masses[i + 1].getY() - masses[i].getY()) - getInitialSpringLength()))) / masses[i].getMass());

		}
		masses[0].setVx(masses[0].getVx() + masses[0].getAx() * timestep);	
		masses[0].setVy(masses[0].getVy() + masses[0].getAy() * timestep);	

		for (int i = 1; i < n - 1; i++) {
			masses[i].setVx(masses[i].getVx() + masses[i].getAx() * timestep);
			masses[i].setVy(masses[i].getVy() + masses[i].getAy() * timestep);	

		}
		masses[0].setX(masses[0].getX() + masses[0].getVx() * timestep);
		masses[0].setY(masses[0].getY() + masses[0].getVy() * timestep);

//		if (masses[0].getY() >= masses[1].getY()) {
//			masses[0].setY(masses[1].getY());
//		}
		for (int i = 1; i < n - 1; i++) {
			masses[i].setX(masses[i].getX() + masses[i].getVx() * timestep);
			masses[i].setY(masses[i].getY() + masses[i].getVy() * timestep);

//			if (masses[i].getY() >= masses[i + 1].getY()) {
//				masses[i].setY(masses[i + 1].getY());
//			}	
		}

	}
	public void updateVelocities(double timestep) {
		for (int i = 0; i < n; i++) {
			masses[i].setVx(masses[i].getVx() + masses[i].getAx() * timestep);
			masses[i].setVy(masses[i].getVy() + masses[i].getAy() * timestep);

		}
	}
	public void updatePositions(double timestep) {
		for (int i = 0; i < n; i++) {
			masses[i].setX(masses[i].getX() + masses[i].getVx() * timestep);
			masses[i].setY(masses[i].getY() + masses[i].getVy() * timestep);

		}
	}
	
}
