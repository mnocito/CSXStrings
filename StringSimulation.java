package stringsLKMN;

import java.awt.Color;

import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.*;
import org.opensourcephysics.frames.*;

public class StringSimulation extends AbstractSimulation {
	PhysicsString bungee = new PhysicsString();
	Trail bungeetrail = new Trail();
	DisplayFrame frame = new DisplayFrame( "X", "Height","Bungee Jump");
	double positionY = 0;
	double time = 0;
	double timestep  = 0.001;
	double g = -9.81;
	double[] oldVelocities;

	//TODO: make all lengths absolute



	protected void doStep() {
		bungee.update(timestep);
		time+=timestep;

	}
	public void initialize() {
		frame.setVisible(true);;
		bungeetrail.addPoint(0, 0);
		bungee.setN((int) control.getDouble("n"));
		frame.setPreferredMinMax(-100, 100, -100, 100);
		bungee.setLength(control.getDouble("length"));
		bungee.setTension(control.getDouble("tension"));
		bungee.setMass(control.getDouble("mass"));
		bungee.oldVelocities = new double[(int) control.getDouble("n")];
		bungee.masses[0] = new Mass(bungee.mass / (double) bungee.n , 0, 0);
		frame.addDrawable(bungee.masses[0]);
		bungee.masses[0].setXY(0,  -bungee.getLength());
		for (int i = 1; i < bungee.getN(); i++) {
			bungee.masses[i] = new Mass(bungee.getMass()/(bungee.getN()), 0, 0);
			bungee.masses[i].pixRadius = 3;
			frame.addDrawable(bungee.masses[i]);
			bungee.masses[i].setXY(0,(-bungee.getLength()+((bungee.getLength()/(bungee.getN())) * (double) (i + 1))));
		//	bungee.masses[1].setV(Math.sqrt(2.0 * g * (double) i * (bungee.getLength()/(bungee.getN()))));
		//	bungee.masses[i].setV(Math.sqrt(2.0 * g * Math.abs(bungee.masses[i].getY())));
		}
		this.setDelayTime(1);
	}
	public void reset() {
		control.setAdjustableValue("n",0);
		control.setAdjustableValue("length", 0);
		control.setAdjustableValue("mass", 0);
		control.setAdjustableValue("tension", 0);
		control.setAdjustableValue("amplitude", 0);
	}
	public static void main (String[] args) {
		SimulationControl.createApp(new StringSimulation());
	}

}
