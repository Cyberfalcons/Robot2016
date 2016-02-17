
package org.usfirst.frc.team3710.robot;

import edu.wpi.first.wpilibj.*;
/**
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
*/


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;

    
    
	//Initialize system classes as instances of the classes
	Drivetrain drive;
	Armsystem arm;

	
	// Controller and Sensor
	Talon driveRightTalon, driveLeftTalon, armPivot, armObstacleRoller, armIntake;
	Joystick jsLeft, jsRight;
	Encoder encDriveLeft, encDriveRight;
	PIDController driveLeftPID, driveRightPID, armPID;
	DigitalInput armLimitForward, armLimitBackward, armBallIn;
	AnalogInput armPotPivot;
	PowerDistributionPanel pdp;
	Timer timer;
	
	
    public void robotInit() {
    	//INPUT DEVICES
    	jsLeft = new Joystick(1);
    	jsRight = new Joystick(2);
    	
    	//DRIVETRAIN
        driveLeftTalon=new Talon(VariableMap.PWM_DRIVE_LEFT);
        driveRightTalon=new Talon(VariableMap.PWM_DRIVE_RIGHT);     
		
		encDriveLeft = new Encoder(VariableMap.DIO_DRIVE_ENC_LEFT_A,VariableMap. 	 DIO_DRIVE_ENC_LEFT_B, false,Encoder.EncodingType.k4X);
		encDriveRight = new Encoder(VariableMap.DIO_DRIVE_ENC_RIGHT_A,VariableMap. DIO_DRIVE_ENC_RIGHT_B, false,Encoder.EncodingType.k4X);
		
		driveLeftPID = new PIDController(VariableMap.DRIVE_PID_P,VariableMap.DRIVE_PID_I, VariableMap.DRIVE_PID_D, encDriveLeft,driveLeftTalon);
		driveRightPID = new PIDController(VariableMap.DRIVE_PID_P,VariableMap.DRIVE_PID_I, VariableMap.DRIVE_PID_D,encDriveRight, driveRightTalon);
		
		//ARM
		armPivot = new Talon(VariableMap.PWM_ARM);
		armObstacleRoller = new Talon(VariableMap.PWM_OBSROLLER);
		armIntake = new Talon (VariableMap.PWM_INTAKE);
 
		armLimitForward = new DigitalInput(VariableMap.DIO_LIM_ARM_EXTENDED);
		armLimitBackward = new DigitalInput(VariableMap.DIO_LIM_ARM_RETRACTED);
		
		armPotPivot = new AnalogInput(VariableMap.ANA_ARM_POT);
		armPID = new PIDController(VariableMap.ARM_PID_P,VariableMap.ARM_PID_I, VariableMap.ARM_PID_D, armPotPivot, armPivot);
		
		armBallIn = new DigitalInput(VariableMap.DIO_LIM_BALL);
		
		
		//SYSTEMS
		drive = new Drivetrain(driveLeftTalon, driveRightTalon, encDriveLeft, encDriveRight, driveLeftPID, driveRightPID);
		arm = new Armsystem(armPivot, armObstacleRoller, armIntake, armLimitForward, armLimitBackward, armBallIn, armPotPivot, armPID);
		pdp = new PowerDistributionPanel();
		timer = new Timer();		
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {

    }

    /**
     * This function is called periodically during autonomous
     */
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

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	doDrive();
		doArm();
		doRollers();
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    public void doDrive() {
		//power of three to make slow speeds more controllable 
		//(big joystick change for small tweaks in speed)
		drive.setDriveLeft((-1)*Math.pow(jsLeft.getY(),3));
		drive.setDriveRight(Math.pow(jsRight.getY(),3));
	}
	public void doArm() {
		//Run arm pivot - no PID.
		if (jsRight.getRawButton(3)){
			arm.setArmPivotForward(0.5);
		} else if (jsRight.getRawButton(4)){
			arm.setArmPivotBackward(0.5);
		} else {
			arm.stopArmPivot();
		}
		
		
	}
	public void doRollers() {
		// NO IDEA ABOUT BUTTON MAPPING HERE - USE DRIVERS STATION INTERFACE TO DECIDE WHAT YOU LIKE
		
		//Run obstacle roller - buttons on left joystick
		if (jsLeft.getRawButton(3)){
			arm.setRollerForward(1);
		} else if (jsLeft.getRawButton(4)){
			arm.setRollerBackward(1);
		} else {
			arm.stopRoller();
		}
		
		//Run intake - triggers on left and right joystick suggested
		if (jsLeft.getRawButton(9)){
			arm.setIntakeIn(1);
		} else if (jsRight.getRawButton(9)){
			arm.setIntakeOut(1);
		} else {
			arm.stopIntake();
		}

	}
}
