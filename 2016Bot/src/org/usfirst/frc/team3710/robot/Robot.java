
package org.usfirst.frc.team3710.robot;

import edu.wpi.first.wpilibj.*;

public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    
	//Initialize system classes as instances of the classes
	Drivetrain drive;
	Armsystem arm;
	
	// Controller and Sensor
	Talon driveRightTalon, driveLeftTalon, armPivot, armObstacleRoller1,armObstacleRoller2, armIntake;
	Controller driverControl, operatorControl;
	Encoder encDriveLeft, encDriveRight;
	PIDController driveLeftPID, driveRightPID, armPID;
	DigitalInput armLimitForward, armLimitBackward, armBallIn;
	AnalogInput armPotPivot;
	PowerDistributionPanel pdp;
	Timer timer;
	
	
    public void robotInit() {
    	//INPUT DEVICES
    	driverControl = new JoystickControllerWrapper(0, 1);
		operatorControl = new XBoxControllerWrapper(2);
    	
    	//DRIVETRAIN
        driveLeftTalon=new Talon(VariableMap.PWM_DRIVE_LEFT);
        driveRightTalon=new Talon(VariableMap.PWM_DRIVE_RIGHT);     
		
		encDriveLeft = new Encoder(VariableMap.DIO_DRIVE_ENC_LEFT_A,VariableMap. 	 DIO_DRIVE_ENC_LEFT_B, false,Encoder.EncodingType.k4X);
		encDriveRight = new Encoder(VariableMap.DIO_DRIVE_ENC_RIGHT_A,VariableMap. DIO_DRIVE_ENC_RIGHT_B, false,Encoder.EncodingType.k4X);
		
		driveLeftPID = new PIDController(VariableMap.DRIVE_PID_P,VariableMap.DRIVE_PID_I, VariableMap.DRIVE_PID_D, encDriveLeft,driveLeftTalon);
		driveRightPID = new PIDController(VariableMap.DRIVE_PID_P,VariableMap.DRIVE_PID_I, VariableMap.DRIVE_PID_D,encDriveRight, driveRightTalon);
		
		//ARM
		armPivot = new Talon(VariableMap.PWM_ARM);
		armObstacleRoller1 = new Talon(VariableMap.PWM_OBSROLLER1);
		armObstacleRoller2 = new Talon(VariableMap.PWM_OBSROLLER2);
		armIntake = new Talon (VariableMap.PWM_INTAKE);
 
		armLimitForward = new DigitalInput(VariableMap.DIO_LIM_ARM_EXTENDED);
		armLimitBackward = new DigitalInput(VariableMap.DIO_LIM_ARM_RETRACTED);
		
		armPotPivot = new AnalogInput(VariableMap.ANA_ARM_POT);
		armPID = new PIDController(VariableMap.ARM_PID_P,VariableMap.ARM_PID_I, VariableMap.ARM_PID_D, armPotPivot, armPivot);
		
		armBallIn = new DigitalInput(VariableMap.DIO_LIM_BALL);
		
		//SYSTEMS
		drive = new Drivetrain(driveLeftTalon, driveRightTalon, encDriveLeft, encDriveRight, driveLeftPID, driveRightPID);
		arm = new Armsystem(armPivot, armObstacleRoller1,armObstacleRoller2, armIntake, armLimitForward, armLimitBackward, armBallIn, armPotPivot, armPID);
		pdp = new PowerDistributionPanel();
		timer = new Timer();		
    }
    
    public void autonomousInit() {

    }

    public void autonomousPeriodic() {
    	/**
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    	*/
    }

    public void teleopInit() {
		timer.stop();
		drive.disableLeftPIDControl();
		drive.disableRightPIDControl();
	}

    public void teleopPeriodic() {
    	doDrive();
	doArm();
	doRollers();
        
    }
    
    public void testPeriodic() {
    
    }
    
    public void doDrive() {
		//power of three to make slow speeds more controllable 
		//(big joystick change for small tweaks in speed)
		drive.setDriveLeft((-1)*Math.pow(driverControl.getLeftY(),3));
		drive.setDriveRight(Math.pow(driverControl.getRightY(),3));
	}
    
	public void doArm() {
		//Run arm pivot - no PID.
		if (driverControl.getLeftButton2()){
			arm.setArmPivotForward(0.5);
		} else if (driverControl.getLeftButton3()){
			arm.setArmPivotBackward(0.5);
		} else {
			arm.stopArmPivot();
		}
	}
	
	public void doRollers() {
		// NO IDEA ABOUT BUTTON MAPPING HERE - USE DRIVERS STATION INTERFACE TO DECIDE WHAT YOU LIKE
		
		//Run obstacle roller - buttons on left joystick
		if (driverControl.getRightButton3()){
			arm.setRollerForward(1);
		} else if (driverControl.getRightButton2()){
			arm.setRollerBackward(1);
		} else {
			arm.stopRoller();
		}
		
		//Run intake - triggers on left and right joystick suggested
		if (driverControl.getLeftButton1()){
			arm.setIntakeIn(1);
		} else if (driverControl.getRightButton1()){
			arm.setIntakeOut(1);
		} else {
			arm.stopIntake();
		}
	}
}
