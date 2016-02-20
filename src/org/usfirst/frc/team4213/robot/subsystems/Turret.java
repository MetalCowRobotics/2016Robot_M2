package org.usfirst.frc.team4213.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.DigitalInput;


public class Turret extends PIDSubsystem {
	
	public enum TurretState {
		RAISED,DOWN;
	}

	private final CANTalon motorPitch;
	private final CANTalon motorYaw;
	private final DigitalInput limitSwitch;
	private final Potentiometer stringPot;
	private final double startYawCount;
	private TurretState state = TurretState.DOWN;
	private boolean yawZero = false;
	
	private final static int GEARSGR = 3;
	private final static int PLANETARYGR = 71;
	private final static int PPR = 5;

	
	private final static int COUNT_PER_DEG_PITCH = (GEARSGR * PLANETARYGR * PPR) / 360;
	
	private final static int COUNT_PER_DEG_YAW = 100 / 360;
	
	private final static int MID = 38;
	private final static int PITCH_MIN_ANGLE = 20 + MID;
	private final static int PITCH_MAX_ANGLE = 85 + MID;
	
	private final static int OFFSET_YAW = 0;
	private final static int YAW_START_COUNT = 500 + OFFSET_YAW;
	private final static int YAW_MAX_COUNT = 600 + OFFSET_YAW;
	private final static int YAW_MIN_COUNT = 400 + OFFSET_YAW;

	public Turret() {
		super("turret", 3, 0, 0);
		
		motorYaw = new CANTalon(1);
		motorPitch = new CANTalon(3);
		stringPot = new AnalogPotentiometer(0, 1000 , 0);
		startYawCount = stringPot.get();
		limitSwitch = new DigitalInput(8);
		
		motorPitch.setControlMode(TalonControlMode.Position.value);
		motorPitch.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		motorPitch.setPID(3, 0, 0);
		motorPitch.enableControl();
		
		motorPitch.setEncPosition(0);
		motorPitch.setPosition(0);
		getPIDController().setContinuous(false);
		getPIDController().setAbsoluteTolerance(2);
		getPIDController().setInputRange(YAW_MIN_COUNT, YAW_MAX_COUNT);
	}

	@Override
	protected void initDefaultCommand() {
		// ADD SAFETY MOVE BACK TO 0 POSITION.
	}

	public void setYawAngle(int angle) {
		setSetpoint((angle*COUNT_PER_DEG_YAW) + YAW_START_COUNT);
	}
	
	public void setPitchAngle(int angle) {
		motorPitch.set((COUNT_PER_DEG_PITCH * angle) + MID);
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
	
	public void setPitchSpeed(int speed){
		motorPitch.setControlMode(TalonControlMode.PercentVbus.value);
		motorPitch.set(speed);
		motorPitch.setControlMode(TalonControlMode.Position.value);
	}
	
	public void setYawSpeed(double speed){
		motorYaw.set(speed);
	}
	
	public void stop() {
		setYawSpeed(0);
		setPitchSpeed(0);
	}
	
	public void zeroYaw(){
		setSetpoint(startYawCount);
	}
	
	
	@Override
	protected double returnPIDInput() {
		DriverStation.reportError("\n PID is Set to" + stringPot.pidGet() ,false);
		return stringPot.pidGet();
	}

	@Override
	protected void usePIDOutput(double output) {
			DriverStation.reportError("\n Motor Set to " + output, false);
			motorYaw.set(output);
	}

	public void raise() {
		setPitchAngle(PITCH_MIN_ANGLE);
	}
	
	public void drop() {
		setPitchAngle(-MID);
	}
	
	public boolean yawOnTarget(){
		return getPIDController().onTarget();
	}

}
