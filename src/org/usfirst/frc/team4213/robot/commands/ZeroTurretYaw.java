package org.usfirst.frc.team4213.robot.commands;

import org.usfirst.frc.team4213.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ZeroTurretYaw extends Command {

	public ZeroTurretYaw() {

	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.turret.zeroYaw();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.turret.yawOnTarget();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
			
	}
}
