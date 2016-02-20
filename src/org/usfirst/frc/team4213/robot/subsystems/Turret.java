package org.usfirst.frc.team4213.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.DigitalInput;


public class Turret extends PIDSubsystem {

	private final CANTalon motorPitch;
	private final CANTalon motorYaw;
	private final DigitalInput limitSwitch;
	private final Potentiometer stringPot;
	
	private final static int GEARSGR = 3;
	private final static int PLANETARYGR = 71;
	private final static int PPR = 7;

	private final static int COUNT_PER_DEG_PITCH = (GEARSGR * PLANETARYGR * PPR) / 360;

	private final static int COUNT_PER_DEG_YAW = 100 / 360;
	
	final static int MID = 38;
	final static int PITCH_MIN_ANGLE = 20 + MID;
	final static int PITCH_MAX_ANGLE = 85 + MID;
	
	public Turret() {
		super("turret", 3, 0, 0);
		
		motorYaw = new CANTalon(1);
		motorPitch = new CANTalon(3);
		stringPot = new AnalogPotentiometer(0, 1000 , 0);
		limitSwitch = new DigitalInput(8);
		
		motorPitch.setControlMode(TalonControlMode.Position.value);
		motorPitch.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		motorPitch.setPID(3, 0, 0);
		motorPitch.enableControl();
		
		motorPitch.setEncPosition(0);
		motorPitch.setPosition(0);
		
		getPIDController().setContinuous(false);
	}

	@Override
	protected void initDefaultCommand() {
		// ADD SAFETY MOVE BACK TO 0 POSITION.
	}

	public void setYawAngle(int angle) {
		setSetpoint(angle * COUNT_PER_DEG_YAW);
	}
	
	public void setPitchAngle(int angle) {
		motorPitch.set(COUNT_PER_DEG_PITCH * angle + MID);
	}

	public boolean getLimitTripped(){
		return limitSwitch.get();
	}
	
	public double getPitchAngle(){
		return motorPitch.getEncPosition() / COUNT_PER_DEG_PITCH;
	}
	
	public double getYawAngle(){
		return stringPot.get() / COUNT_PER_DEG_YAW;
	}
	
	public void stop() {
		// TODO
	}



	@Override
	protected double returnPIDInput() {
		return stringPot.pidGet();
	}



	@Override
	protected void usePIDOutput(double output) {
		if (!Double.isNaN(output)){
			motorYaw.set(output);
		}
	}

}
