package org.usfirst.frc.team4213.robot.commands;

import org.usfirst.frc.team4213.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class TurretZeroYaw extends Command{
	double startPoint = 490;

	public TurretZeroYaw() {
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		DriverStation.reportError("set turret angle", false);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		double error = startPoint - Robot.turret.getYawCount();
		Robot.turret.setYawSpeed(-error/100);

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return (Math.abs(Robot.turret.getYawCount() - startPoint) < 5);

	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.turret.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
			
	}

}
