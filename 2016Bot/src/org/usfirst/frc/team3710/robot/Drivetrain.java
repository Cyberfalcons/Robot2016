package org.usfirst.frc.team3710.robot;

import edu.wpi.first.wpilibj.*;

public class Drivetrain {

	Talon leftA;
	Talon rightA;
	Encoder encRight;
	Encoder encLeft;
	PIDController pidLeft;
	PIDController pidRight;

	public Drivetrain(Talon la, Talon ra, Encoder el, Encoder er, PIDController lpid, PIDController rpid) {
		leftA = la;
		rightA = ra;

		encLeft = el;
		encLeft.setMaxPeriod(.1);
		encLeft.setMinRate(10);
		encLeft.setDistancePerPulse(1);
		encLeft.setReverseDirection(false);
		encLeft.setSamplesToAverage(7);

		encRight = er;
		encRight.setMaxPeriod(.1);
		encRight.setMinRate(10);
		encRight.setDistancePerPulse(1);
		encRight.setReverseDirection(false);
		encRight.setSamplesToAverage(7);
		
		pidLeft = lpid;
		pidRight = rpid;
	}

	//Sets the right side drive to a certain speed
	public void setDriveRight(double power) {
		rightA.set((power * VariableMap.SPEED_MODIFIER));
	}

	//Sets the left side drive to a certain speed
	public void setDriveLeft(double power) {
		leftA.set((power * VariableMap.SPEED_MODIFIER));
	}
	
	//Passes a encoder setpoint to the left side PID controller so that the drive train will get the robot there
	public void setPIDDriveLeft(int position){
		pidLeft.enable();
		pidLeft.setSetpoint(position);
	}
	
	//Passes a encoder setpoint to the right side PID controller so that the drive train will get the robot there
	public void setPIDDriveRight(int position){
		pidRight.enable();
		pidRight.setSetpoint(position);
	}
	
	//A method that will tell the drivetrain to drive a certain distance using PID
	//Use with caution
	public void driveRightSetDistance(int feet, int inches){
		int ticks = 0;
		ticks = feet * 65;
		ticks = ticks + (inches * (65/12));
		pidRight.enable();
		pidRight.setSetpoint(ticks);
	}
	
	//A method that will tell the drivetrain to drive a certain distance using PID
    //Use with caution
	public void driveLeftSetDistance(int feet, int inches){
		int ticks = 0;
		ticks = feet * 65;
		ticks = ticks + (inches * (65/12));
		ticks = ticks * -1;
		pidLeft.enable();
		pidLeft.setSetpoint(ticks);
	}
	
	//Disables the left PID controller
	public void disableLeftPIDControl(){
		pidLeft.disable();
		leftA.set(0.0);
	}
	
	//Disables the right PID controller
	public void disableRightPIDControl(){
		pidRight.disable();
		rightA.set(0.0);
	}

	public int getEncoderRight() {
		return encRight.get();
	}

	public int getEncoderLeft() {
		return encLeft.get();
	}

	public void resetLeftEncoder() {
		encLeft.reset();
	}

	public void resetRightEncoder() {
		encRight.reset();
	}
}