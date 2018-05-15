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
	double timestep  = 0.0000001;
	double g = -9.81;
	double[] oldVelocities;
	double frequency =0;
	double amplitude =0;

	//TODO: make all lengths absolute



	protected void doStep() {
		for (int i = 0; i < 100; i++) {
			bungee.update(timestep, time, frequency, amplitude);
			time+=timestep;
		}
		
	}
	public void initialize() {
		frame.setSquareAspect(false);
		frame.clearDrawables();
		frame.setVisible(true);
		bungeetrail.addPoint(0, 0);
		bungee.setN((int) control.getDouble("n"));
		frame.setPreferredMinMax(-.05, 1.05, -.08, .08);
		bungee.setLength(control.getDouble("length"));
//		bungee.setTension(control.getDouble("tension"));
		bungee.setK(control.getDouble("total k"));
		bungee.setMass(control.getDouble("mass"));
		bungee.oldVelocities = new double[(int) control.getDouble("n")];
		 frequency = control.getDouble("frequency");
		 amplitude = control.getDouble("amplitude");
		for (int i = 0; i < bungee.getN(); i++) {
			bungee.masses[i] = new Mass(bungee.getMass()/(bungee.getN()), 0, 0, 0, 0);
			bungee.masses[i].pixRadius = 3;
			frame.addDrawable(bungee.masses[i]);
			bungee.masses[i].setXY((((bungee.getLength()/(bungee.getN())) * (double) (i))), 0);
			bungee.masses[i].setK(bungee.k);
			bungee.masses[i].setLength(bungee.getInitialSpringLength());
		}
		bungee.masses[0].setVy(10);
		this.setDelayTime(1);
	}
	public void reset() {
		control.setAdjustableValue("n",100);
		control.setAdjustableValue("length", 1);
		control.setAdjustableValue("mass", .01);
		control.setAdjustableValue("total k", 100000);
		control.setAdjustableValue("amplitude", .01);
		control.setAdjustableValue("frequency", 100);
	}
	public static void main (String[] args) {
		SimulationControl.createApp(new StringSimulation());
	}

}
