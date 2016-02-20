package org.usfirst.frc.team4213.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {

	double setpoint;
	Encoder enc;
	CANTalon intakeMotor;

	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void log() {
	}

	public Intake() {
		super();
		intakeMotor = new CANTalon(2);
	}

	public void intake(double speed) {
		intakeMotor.set(speed);
	}

	public void intakeStop() {
		intakeMotor.set(0);
	}
}
