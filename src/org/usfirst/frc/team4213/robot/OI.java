package org.usfirst.frc.team4213.robot;

import org.team4213.lib14.AIRFLOController;
import org.usfirst.frc.team4213.robot.commands.CreepDrive;
import org.usfirst.frc.team4213.robot.commands.IntakeBall;
import org.usfirst.frc.team4213.robot.commands.NormalDrive;
import org.usfirst.frc.team4213.robot.commands.SprintDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	private Joystick driver = new AIRFLOController(0);
	private Joystick gunner = new AIRFLOController(1);
	
    public OI() {
    	setupDashboard();
        setupDriverController();
        setupGunnerController();
    }
    
    public Joystick getDriverJoystick(){
    	return driver;
    }
    
    public Joystick getGunnerJoystick(){
    	return gunner;
    }
    
    //setup the button mapping for the Driver's Controller
    private void setupDriverController(){
    	
    	// Create some buttons
        JoystickButton d_up = new JoystickButton(driver, 4);
        JoystickButton d_right= new JoystickButton(driver, 2);
        JoystickButton d_down= new JoystickButton(driver, 1);
        JoystickButton d_left = new JoystickButton(driver, 3);
        JoystickButton leftBumper = new JoystickButton(driver, 5);
        JoystickButton leftTrigger = new JoystickButton(driver, 7);
        JoystickButton rightBumper = new JoystickButton(driver, 6);
        JoystickButton rightTrigger = new JoystickButton(driver, 8);

        // Connect the buttons to commands
        
        /******These do nothing for Driver right now
         * Driver does not have any button commands
         * Other than CRAWL and SPRINT
         * These will be COMMANDS for the DRIVETRAIN
         * COMMANDS will map to the buttons
         * 
         */
        
        
       // leftBumper.whileHeld(new TankButtonTest());
       // rightBumper.whileHeld(new TankButtonTestReverse());;
        
//        d_up.whenPressed();  //may need whileHeld
//        d_down.whenPressed();
//        d_right.whenPressed();
//        d_left.whenPressed();
        
        leftBumper.whenPressed(new CreepDrive()); //crawl
        rightBumper.whenPressed(new CreepDrive()); //crawl
        
		leftTrigger.whenPressed(new SprintDrive()); //Sprint
        rightTrigger.whenPressed(new SprintDrive()); //Sprint
        
        leftBumper.whenReleased(new NormalDrive());
        rightBumper.whenReleased(new NormalDrive()); //crawl
        
		leftTrigger.whenReleased(new NormalDrive()); //Sprint
        rightTrigger.whenReleased(new NormalDrive()); //Sprint
        
        d_down.whileHeld(new IntakeBall());
        
        
        
        
    }
    
    //setup the button mapping for the Driver's Controller
    private void setupGunnerController(){
    	
    	// Create some buttons
        JoystickButton d_up = new JoystickButton(gunner, 4);
        JoystickButton d_right= new JoystickButton(gunner, 2);
        JoystickButton d_down= new JoystickButton(gunner, 1);
        JoystickButton d_left = new JoystickButton(gunner, 3);
        JoystickButton leftBumper = new JoystickButton(gunner, 5);
        JoystickButton leftTrigger = new JoystickButton(gunner, 7);
        JoystickButton rightBumper = new JoystickButton(gunner, 6);
        JoystickButton rightTrigger = new JoystickButton(gunner, 8);
        JoystickButton center_9 = new JoystickButton(gunner, 9);
        JoystickButton center_10 = new JoystickButton(gunner, 10);
        JoystickButton center_11 = new JoystickButton(gunner, 11);

        // Connect the buttons to COMMANDS
        //whenPressed, whenReleased, whenHeld
//        d_up.whenPressed(); //
//        d_down.whenPressed(); //
//        d_right.whenPressed(); //
//        d_left.whenPressed(); //
//
//        leftBumper.whenPressed(); //pressed rotate kicker to fire ball
//        leftTrigger.whenPressed(); //held=turret to firing minheight
//        
//        rightBumper.whenPressed(); //
//        rightTrigger.whenPressed(); //

        
    }
	
    //for each data element add it to the dashboard so we can see what it is doing.
    //Put buttons on the dashboard
	private void setupDashboard(){
		
		
		/*** Each button command can have a smartDash mapping here to see **/
		
        //SmartDashboard.putData("Elevator Bottom", new SetElevatorSetpoint(0));
        //SmartDashboard.putData("Elevator Platform", new SetElevatorSetpoint(0.2));
        //SmartDashboard.putData("Elevator Top", new SetElevatorSetpoint(0.3));
        
        //SmartDashboard.putData("Wrist Horizontal", new SetWristSetpoint(0));
        //SmartDashboard.putData("Raise Wrist", new SetWristSetpoint(-45));
        
        //SmartDashboard.putData("Open Claw", new OpenClaw());
        //SmartDashboard.putData("Close Claw", new CloseClaw());
        
        //SmartDashboard.putData("Deliver Soda", new Autonomous());
        
	
	}
	
	
	
	
}

