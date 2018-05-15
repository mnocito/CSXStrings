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
	double timestepcounter = 0;
	double up = 1.0;
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
//		this.length *= .75;
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
	public void setK(double k) {
		this.k = k;
	}
	public double getInitialK() {
		return tension / getInitialSpringLength();
	}
	public void update(double timestep, double time, double frequency, double amplitude) {
		for (int i = 1; i < n - 1; i++) {
			masses[i].setAx((-masses[i].getFx(masses[i - 1]) - masses[i].getFx(masses[i + 1])) / masses[i].getMass());
			masses[i].setAy((-masses[i].getFy(masses[i - 1]) - masses[i].getFy(masses[i + 1])) / masses[i].getMass());
				}
		for (int i = 0; i < n; i++) {
			masses[i].setVx(masses[i].getVx() + masses[i].getAx() * timestep);	
			masses[i].setVy(masses[i].getVy() + masses[i].getAy() * timestep);	
		}
		masses[0].setXY(0, Math.sin(frequency * time*2*Math.PI) * amplitude);	
		for (int i = 1; i < n - 1; i++) {
			masses[i].setXY(masses[i].getX() + masses[i].getVx() * timestep, masses[i].getY() + masses[i].getVy() * timestep);
			if (masses[i].getX() >= masses[i + 1].getX()) {
				masses[i].setX(masses[i + 1].getX());
			}	
		}
		/*timestepcounter+= up*timestep;
		if (timestepcounter/timestep >= 25*360) {
			up = -1;
		}
		if (up == -1 && timestepcounter/timestep <= -25*360) {
			up = 1;
		}*/
	}
	
}
