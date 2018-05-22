package stringsLKMN;

import java.awt.Color;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.DisplayFrame;

public class PaddleLK extends AbstractSimulation {
	StringLK bungee = new StringLK();
	Trail bungeetrail = new Trail();
	DisplayFrame frame = new DisplayFrame( "X", "Height","String Simulation");
	DrawableShape rect = DrawableShape.createRectangle(0,-0.025,5,.05);
	double positionY = 0;
	double time = 0;
	double timestep  = 0.00001;
	double g = -9.81;
	double[] oldVelocities;
	double frequency =0;
	double amplitude =0;
	double force = 0;

	//TODO: make all lengths absolute



	protected void doStep() {
		for (int i = 0; i < 10; i++) {
			bungee.update(timestep, time, bungee.collision, force);
			time+=timestep;
		}
		
	}
	public void initialize() {
		frame.setSquareAspect(false);
		frame.clearDrawables();
		frame.setVisible(true);
		
		force = control.getDouble("paddle force");
		bungeetrail.addPoint(0, 0);
		bungee.setN((int) control.getDouble("n"));
		frame.setPreferredMinMax(-15, 15, -1.5, 1.5);
		bungee.setLength(control.getDouble("rest length"));
		bungee.setStartLength(control.getDouble("start length"));
//		bungee.setTension(control.getDouble("tension"));
		bungee.setK(control.getDouble("total k"));
		bungee.setMass(control.getDouble("mass"));
		bungee.oldVelocities = new double[(int) control.getDouble("n")];
		bungee.setCollision(control.getDouble("collision constant"));
//		 frequency = control.getDouble("frequency");
//		 amplitude = control.getDouble("amplitude");
		for (int i = 0; i < bungee.getN(); i++) {
			bungee.masses[i] = new MassLK(bungee.getMass()/(bungee.getN()), 0, 0, 0, 0);
			bungee.masses[i].pixRadius = 3;
			frame.addDrawable(bungee.masses[i]);
			bungee.masses[i].setXY((((bungee.getStartLength()/(bungee.getN())) * (double) (i)))*Math.cos(Math.toRadians(control.getDouble("launch angle"))),(((bungee.getStartLength()/(bungee.getN()))*(double) (i)))*Math.sin(Math.toRadians(control.getDouble("launch angle"))));
			bungee.masses[i].setK(bungee.k);
			bungee.masses[i].setLength(bungee.getInitialSpringLength());
			bungee.masses[0].color = Color.GREEN;
		}
		bungee.masses[(int)bungee.getN()-1].pixRadius = 6;
		bungee.masses[(int)bungee.getN()-1].color = Color.GREEN;
		bungee.masses[(int) bungee.getN()-1].setMass(0.05);
		bungee.masses[(int) bungee.getN()-1].setXY(bungee.getStartLength()*Math.cos(Math.toRadians(control.getDouble("launch angle"))), bungee.getStartLength()*Math.sin(Math.toRadians(control.getDouble("launch angle"))));
//		bungee.masses[0].setVy(10);
		this.setDelayTime(1);
		rect.color = Color.BLUE;
		frame.addDrawable(rect);
	}
	public void reset() {
		control.setAdjustableValue("n",100);
		control.setAdjustableValue("rest length", .5);
		control.setAdjustableValue("start length", .75);
		control.setAdjustableValue("mass", .01);
		control.setAdjustableValue("total k", 100000);
		control.setAdjustableValue("launch angle",90);
		control.setAdjustableValue("collision constant", .5);
		control.setAdjustableValue("paddle force", 10);
//		control.setAdjustableValue("amplitude", .01);
//		control.setAdjustableValue("frequency", 158);
	}
	public static void main (String[] args) {
		SimulationControl.createApp(new PaddleLK());
	}

}
