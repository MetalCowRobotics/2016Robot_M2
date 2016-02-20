package org.usfirst.frc.team4213.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class Kicker extends PIDSubsystem {

	private final Encoder encoder;
	private final CANTalon motor;
	private final static int GEARSGR = 3;
	private final static int PLANETARYGR = 27;
	private final static int PPR = 7;
	private int count180 = 0;
	private int RIGHT_ANGLE;

	private final static int COUNT_PER_DEG = (GEARSGR * PLANETARYGR * PPR) / 360;

	public Kicker() {
		super("kicker", 3, 0, 0);
		encoder = new Encoder(4, 5);
		motor = new CANTalon(0);
		getPIDController().setContinuous(false);
		RIGHT_ANGLE = encoder.get() * COUNT_PER_DEG;

	}

	@Override
	protected double returnPIDInput() {
		return encoder.pidGet();
	}

	@Override
	protected void usePIDOutput(double output) {
		if (!Double.isNaN(output)) {
			motor.set(output);
		}
	}

	@Override
	protected void initDefaultCommand() {
	}

	protected void setAngle(int angle) {
		setSetpoint(COUNT_PER_DEG * angle);
	}

	public void move360() {
		RIGHT_ANGLE += 360;
		setAngle(RIGHT_ANGLE);
	}

	public void move180() {
		setAngle(180 + (encoder.get() / COUNT_PER_DEG));
		if(count180++ > 1){
			RIGHT_ANGLE += 360;
			count180 = 0;
		}
	}

	public void stop() {
		motor.set(0);
	}
}
