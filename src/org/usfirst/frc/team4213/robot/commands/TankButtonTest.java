package org.usfirst.frc.team4213.robot.commands;

import org.usfirst.frc.team4213.robot.Robot;

//import org.usfirst.frc.team4213.robot.Robot;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class TankButtonTest extends Command {
    
    public TankButtonTest() {
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the f iirst time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //Robot.drivetrain.drive(Robot.oi.getDriverJoystick(), true);

        
        Robot.drivetrain.drive(.25,.25, true);
        DriverStation.reportError("Button FORWARD>>>>>>>", false);
        
       
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.drive(0, 0, true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
