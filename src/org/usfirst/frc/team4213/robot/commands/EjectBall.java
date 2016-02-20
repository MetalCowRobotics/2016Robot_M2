package org.usfirst.frc.team4213.robot.commands;

import org.usfirst.frc.team4213.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class EjectBall extends Command {

	public EjectBall() {
		requires(Robot.intake);
		requires(Robot.cannonWheels);
		// TODO: add turret
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.intake.intakeStop();
		Robot.cannonWheels.stopWheels();
		// TODO: zero the turret
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.intake.reverseIntake();
		Robot.cannonWheels.ejectBall();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		// TODO: add when button is released return is finished.
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		// NOTE: Doesn't stop in simulation due to lower friction causing the
		// can to fall out
		// + there is no need to worry about stalling the motor or crushing the
		// can.
		Robot.intake.intakeStop();
		Robot.cannonWheels.stopWheels();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
