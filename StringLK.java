package stringsLKMN;

public class StringLK {
	int n; //number of sections of string cord
	double mass;//mass of the string cord
	double length; //length of the string cord
	double k; //k of the overall string cord
	MassLK[] masses = new MassLK[n]; //array of masses
	double g = -9.81;
	double tension = 0;
	double[] oldVelocities = new double[n]; 
	double timestepcounter = 0;
	double up = 1.0;
	double startlength = 0;
	double collision = 0;
	double currentlength =0;
	boolean collide = false;
	boolean nonzero = false;


	public double getCurrentlength() {
		currentlength=0;
		for (int i = 0; i < masses.length-1; i++) {
			currentlength+=masses[i].getDistance(masses[i+1]);
		}
		return currentlength;
	}
	public double getCollision() {
		return collision;
	}
	public void setCollision(double collision) {
		this.collision = collision;
	}
	public double getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
		masses = new MassLK[n];
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
	public double getStartLength() {
		return startlength;
	}
	public void setStartLength(double startlength) {
		this.startlength = startlength;
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
	public void update(double timestep, double time, double collision, double force, double paddy) {
		for (int i = 1; i < n-1; i++) {
			masses[i].setAx((-masses[i].getFx(masses[i - 1]) - masses[i].getFx(masses[i + 1])) / masses[i].getMass());
			masses[i].setAy((-masses[i].getFy(masses[i - 1]) - masses[i].getFy(masses[i + 1])+(masses[i].getMass()*g)) / masses[i].getMass());
		}
		masses[n-1].setAx(-masses[n-1].getFx(masses[n - 2])/masses[n-1].getMass());
		masses[n-1].setAy((-masses[n-1].getFy(masses[n - 2])+(masses[n-1].getMass()*g))/masses[n-1].getMass());



		for (int i = 0; i < n; i++) {
			masses[i].setVx(masses[i].getVx() + masses[i].getAx() * timestep);	
			masses[i].setVy(masses[i].getVy() + masses[i].getAy() * timestep);	
		}
		masses[0].setXY(0, 0);	
		//start
//		if (collide&&nonzero) {
//			System.out.println("true");
//			for (int i = 1; i < masses.length; i++) {
////				masses[i].setAy((-masses[i].getMass()*Math.sin(Math.toRadians(Math.atan(masses[n-1].getY()/masses[n-1].getX())))+(force*Math.sin(Math.toRadians(paddy)))) / masses[i].getMass());
////				masses[i].setAx(((-masses[i].getMass()*Math.cos(Math.toRadians(Math.atan(masses[n-1].getY()/masses[n-1].getX()))))+(force*Math.cos(Math.toRadians(paddy)))) / masses[i].getMass());
//				masses[i].setAy((-masses[i].getMass()*Math.sin(Math.toRadians(Math.atan(masses[n-1].getY()/masses[n-1].getX())))+(force)) / masses[i].getMass());
//				masses[i].setAx(((-masses[i].getMass()*Math.cos(Math.toRadians(Math.atan(masses[n-1].getY()/masses[n-1].getX()))))) / masses[i].getMass());
//			}
//			//			masses[n-1].setAx(-masses[n-1].getFx(masses[n - 2])/masses[n-1].getMass());
//			//			masses[n-1].setAy((-masses[n-1].getFy(masses[n - 2])+(masses[n-1].getMass()*g))/masses[n-1].getMass());
//
//			for (int i = 1; i < masses.length; i++) {
//				masses[i].setVy(masses[i].getVy()*Math.cos(Math.toRadians(paddy))+masses[i].getVx()*Math.sin(Math.toRadians(paddy)) + masses[i].getAy() * timestep*collision);
//				masses[i].setVx(masses[i].getVx()*Math.sin(Math.toRadians(paddy))-masses[i].getVy()*Math.cos(Math.toRadians(paddy)) + masses[i].getAx() * timestep*collision);
//				masses[i].setXY(masses[i].getX() + masses[i].getVx() * timestep, masses[i].getY() + masses[i].getVy() * timestep);
//			}
//		}else if (collide&&!nonzero) {
//			if(masses[n-1].getY() + masses[n-1].getVy() * timestep <= masses[0].getY()) {
//				//		masses[n-1].setVy(-masses[n-1].getVy()*collision);
//				masses[n-1].setAy(( masses[n-1].getFy(masses[n-2])+(masses[n-1].getMass()*g)+force) / masses[n-1].getMass());
//				masses[n-1].setVy(-masses[n-1].getVy() + masses[n-1].getAy() * timestep*collision);
//			}
//			for (int i = 1; i < n-1; i++) {
//				
//					masses[i].setAy((-masses[i].getFy(masses[i - 1]) - masses[i].getFy(masses[i + 1])+(masses[i].getMass()*g)+force) / masses[i].getMass());
//					masses[i].setVy(-masses[i].getVy() + masses[i].getAy() * timestep*collision);
//				masses[i].setXY(masses[i].getX() + masses[i].getVx() * timestep, masses[i].getY() + masses[i].getVy() * timestep);
//			}
//		}else {
//			for (int i = 0; i < masses.length; i++) {
//				masses[i].setXY(masses[i].getX() + masses[i].getVx() * timestep, masses[i].getY() + masses[i].getVy() * timestep);	
//			}
//
//		}
		//end
		//start
				if(masses[n-1].getY() + masses[n-1].getVy() * timestep <= masses[0].getY()) {
		//			masses[n-1].setVy(-masses[n-1].getVy()*collision);
					masses[n-1].setAy(( masses[n-1].getFy(masses[n-2])+(masses[n-1].getMass()*g)+force) / masses[n-1].getMass());
					masses[n-1].setVy(-masses[n-1].getVy() + masses[n-1].getAy() * timestep*collision);
				}
				for (int i = 1; i < n-1; i++) {
					if(masses[i].getY() + masses[i].getVy() * timestep <= 0) {
						masses[i].setAy((-masses[i].getFy(masses[i - 1]) - masses[i].getFy(masses[i + 1])+(masses[i].getMass()*g)+force) / masses[i].getMass());
						masses[i].setVy(-masses[i].getVy() + masses[i].getAy() * timestep*collision);
					}
					masses[i].setXY(masses[i].getX() + masses[i].getVx() * timestep, masses[i].getY() + masses[i].getVy() * timestep);
				}
				masses[n-1].setXY(masses[n-1].getX() + masses[n-1].getVx() * timestep, masses[n-1].getY() + masses[n-1].getVy() * timestep);
					//end

		//			if (masses[i].getY() >= masses[i + 1].getY()&& masses[i].getVy()>0) {
		//				masses[i].setY(masses[i + 1].getY());
		//			}
		//			if (masses[i].getX() >= masses[i + 1].getX()&&masses[i].getVx()>0) {
		//				masses[i].setX(masses[i + 1].getX());
		//			}
		//			if (masses[i].getVy()<0 && masses[i].getY()<masses[i-1].getY()) {
		//				masses[i].setX(masses[i - 1].getX());
		//			}
		//			if (masses[i].getVx()<0 && masses[i].getX()<masses[i-1].getX()) {
		//				masses[i].setX(masses[i - 1].getX());
		//			}

		//		}
		//		masses[n-1].setXY(masses[n-1].getX()+masses[n-1].getVx()*timestep, masses[n-1].getY()+masses[n-1].getVy()*timestep);
		/*timestepcounter+= up*timestep;
		if (timestepcounter/timestep >= 25*360) {
			up = -1;
		}
		if (up == -1 && timestepcounter/timestep <= -25*360) {
			up = 1;
		}*/
	}
}
