package org.usfirst.frc.team3710.robot;

import edu.wpi.first.wpilibj.*;

public class Armsystem {

	Talon pivot;
	Talon obstacleroller1,obstacleroller2;
	Talon intake;
	DigitalInput forwardlimit;
	DigitalInput backwardlimit;
	DigitalInput ballin;
	AnalogInput armpot;
	PIDController pidPivot;

	//armPivot, armObstacleRoller, armIntake, armLimitForward, armLimitBackward, armBallIn, armPotPivot, armPID);
	public Armsystem(Talon p, Talon roll1, Talon roll2, Talon in, DigitalInput lf, DigitalInput lb,  DigitalInput bi, AnalogInput app, PIDController apid) {
		pivot = p;
		obstacleroller1 = roll1;
		obstacleroller2 = roll2;
		intake = in;

		//NEED TO SET UP LIMIT SWITCHES, ANALOG IN, CONTROLLER - CHECK THIS
		forwardlimit = lf;
		backwardlimit = lb;
		ballin = bi;
		armpot = app;		
		pidPivot = apid;
	}

	public void setIntakeIn(double power) {
		//if (!ballin.get()){
			intake.set(power);
			//swap signs with setIntakeOut if direction is reversed
		//}
	}
	public void setIntakeOut(double power) {
		intake.set(-power);
		//swap signs with setIntakeIn if direction is reversed
	}
	public void stopIntake() {
		intake.set(0);
	}



	public void setRollerForward(double power) {
		obstacleroller1.set(power);
		obstacleroller2.set(-power);
		//swap signs with setRollerBackward if direction is reversed
	}
	public void setRollerBackward(double power) {
		obstacleroller1.set(-power);
		obstacleroller2.set(power);
		//swap signs with setRollerForward if direction is reversed
	}
	public void stopRoller() {
		obstacleroller1.set(0.0);
		obstacleroller2.set(0.0);
	}
	
	//Passes a sensor setpoint to the PID controller so the motor tries to achieve the setpoint
	public void setPIDPivot(int position){
		pidPivot.enable();
		pidPivot.setSetpoint(position);
	}
		
	//Disables the  PID controller
	public void disablePIDPivot(){
		pidPivot.disable();
		pivot.set(0.0);
	}
	
	public void setArmPivotForward(double power) {
		if (!forwardlimit.get()){
			pivot.set(power);
			//swap signs with setArmPivotBackward if direction is reversed
		}
	}
	public void setArmPivotBackward(double power) {
		if (!backwardlimit.get()){
			pivot.set(-power);
			//swap signs with setArmPivotForward if direction is reversed
		}
	}
	public void stopArmPivot() {
		pivot.set(0.0);
	}
	

	
	/*
	public int getEncoder() {
		return encLeft.get();
	}

	public void resetEncoder() {
		encLeft.reset();
	}
	*/
}