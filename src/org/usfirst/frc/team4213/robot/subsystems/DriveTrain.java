
package org.usfirst.frc.team4213.robot.subsystems;

import org.team4213.lib14.AIRFLOController;
import org.usfirst.frc.team4213.robot.commands.TankDriveWithJoystick;




import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    

	private RobotDrive drive;
	private SpeedController left_motor, right_motor;
	private double multiplier = 1;
	
	public DriveTrain(){
		super();
		left_motor = new Talon(1); //9
		right_motor = new Talon(0);  //8
		drive = new RobotDrive(left_motor, right_motor);
		

		// Let's show everything on the LiveWindow
		LiveWindow.addActuator("Drive Train", "Right Motor", (Talon) right_motor);
		LiveWindow.addActuator("Drive Train", "Left Motor", (Talon) left_motor);
	}

	/**
	 * When no other command is running let the operator drive around
	 * using the PS3 joystick.
	 */
	
	public void initDefaultCommand() {
		//setDefaultCommand(new TankDriveWithJoystick());
	}
	
	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void log() {
		SmartDashboard.putNumber("Left Speed", left_motor.get());
		SmartDashboard.putNumber("Right Speed", right_motor.get());
	}

	/**
	 * Tank style driving for the DriveTrain.
	 * @param left Speed in range [-1,1]
	 * @param right Speed in range [-1,1]
	 */

	public void drive(double leftStick, double rightStick, boolean squareUnits) {
		drive.arcadeDrive(leftStick, rightStick, squareUnits); //ArcadeMode
		
		//drive.tankDrive(leftStick, rightStick, squareUnits);
        //DriverStation.reportError("Tank Drive  [[ " + leftStick + " ]]  "+"  [[  " + rightStick + " ]]", false);

	}

	/**
	 * @param joy The ps3 style joystick to use to drive tank style.
	 */
	public void drive(Joystick driverController, boolean squareUnits) {
		drive(((AIRFLOController) driverController).getLY() * multiplier, ((AIRFLOController) driverController).getRX() * multiplier, squareUnits); //ArcadeMode
		
		//drive(((AIRFLOController) driverController).getLY(), ((AIRFLOController) driverController).getRY(), squareUnits); //TankMode
	}
	
	public void setMultiplier(double multiplier){
		this.multiplier = multiplier;
		
	}

	public double getMultiplier() {
		return multiplier;
	}

}

