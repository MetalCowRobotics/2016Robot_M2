package org.usfirst.frc.team4213.robot.subsystems;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CannonWheels extends Subsystem {
	Jaguar cannonWheels;

	public CannonWheels() {
		cannonWheels = new Jaguar(4);

	}

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void log() {
	}

	public void shootBall() {
		cannonWheels.set(.6);
	}

	public void ejectBall() {
		cannonWheels.set(-0.6);
	}

	public void intakeBall() {
		cannonWheels.set(-0.7);
	}

	public void stopWheels() {
		cannonWheels.set(0);
	}

}
