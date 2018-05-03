package stringsLKMN;

import java.awt.Color;

import org.opensourcephysics.controls.*;
import org.opensourcephysics.display.*;
import org.opensourcephysics.frames.*;

public class StringSimulation extends AbstractSimulation {
	PhysicsString bungee = new PhysicsString();
	Trail bungeetrail = new Trail();
	DisplayFrame frame = new DisplayFrame( "X", "Height","Bungee Jump");
	Circle boy  = new Circle();
	double positionY = 0;
	double time = 0;
	double timestep  = 0.001;
	double g = -9.81;
	double[] oldVelocities;

	//TODO: make all lengths absolute



	protected void doStep() {
//		if (positionY <=-40) {
//			boy.color = Color.green;
//
//		} else {
			
			bungee.update(timestep);
		//	boy.setY(positionY);
			positionY += g*time;
			bungeetrail.addPoint(0, positionY);
//		}

		time+=timestep;

	}
	public void initialize() {
		frame.setVisible(true);
		frame.addDrawable(bungeetrail);
	//	frame.addDrawable(boy);
		bungeetrail.addPoint(0, 0);
		bungee.setN((int) control.getDouble("n") + 1);
		frame.setPreferredMinMax(-100, 100, -100, 100);
		bungee.setLength(control.getDouble("Length"));
		bungee.setK(control.getDouble("K (bungee cord)"));
		bungee.setMass(control.getDouble("Mass (bungee cord)"));
		bungee.oldVelocities = new double[(int) control.getDouble("n") + 1];
//		Mass m = new Mass ( 4, 0 ,g);
//		frame.addDrawable(m);
//		m.setXY(0, 0);
		bungee.masses[0] = new Mass(control.getDouble("boy mass"), 0, 0);
	//	bungee.masses[0].setV(Math.sqrt(2 * g * bungee.getLength()));
		frame.addDrawable(bungee.masses[0]);
		bungee.masses[0].setXY(0,  -bungee.getLength());
	//	bungee.masses[0].setV(Math.sqrt(2.0 * g * Math.abs(bungee.masses[0].getY())));
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
		control.setAdjustableValue("Length", 0);
		control.setAdjustableValue("Mass (bungee cord)", 0);
		control.setAdjustableValue("K (bungee cord)", 0);
		control.setAdjustableValue("boy mass",0);
		
	}
	public static void main (String[] args) {
		SimulationControl.createApp(new StringSimulation());
	}

}
