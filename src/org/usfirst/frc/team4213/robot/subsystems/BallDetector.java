package org.usfirst.frc.team4213.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;

public class BallDetector {

	private DigitalInput contact;

	public BallDetector() {
		super();
		//contact = new DigitalInput(4);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void log() {
	}

	public boolean ballContact() {
		return contact.get();
	}

}
