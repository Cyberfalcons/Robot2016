package org.usfirst.frc.team3710.robot;

//Used to layout all ports and interfaces that the robot uses so that you do not put two systems on one PWM or something!
public class VariableMap {

	/*
	 * PWM Ports (motor outputs)
	 * 
	 * Port 0 - Drive Left
	 * Port 1 - Drive Right
	 * Port 2 - Arm Pivot
	 * Port 3 - Arm Obstacle Roller
	 * Port 4 - Arm Intake Roller 
	 * Port 5 - 
	 * Port 6 - 
	 * Port 7 - 
	 * Port 8 - 
	 * Port 9 - 
	 */
	public static final int PWM_DRIVE_LEFT = 0;
	public static final int PWM_DRIVE_RIGHT = 1;
	public static final int PWM_ARM = 2;
	public static final int PWM_OBSROLLER = 3;
	public static final int PWM_INTAKE = 4;
	
	/*
	 * PCM Modules
	 * Port 0 - SAMPLE Solenoid 1 A
	 * Port 1 - SAMPLE Solenoid 1 B
	 * Port 2 - 
	 * Port 3 - 
	 * Port 4 -
	 * Port 5 -
	 * Port 6 -
	 * Port 7 -
	 */
	public static final int PCM_SAMPLE_SOLE_1_A = 0;
	public static final int PCM_SAMPLE_SOLE_1_B = 1;	

	/*
	 * Digital IO Pins
	 * 
	 * Port 0 - Drive Encoder Left A 
	 * Port 1 - Drive Encoder Left B 
	 * Port 2 - Drive Encoder Right A 
	 * Port 3 - Drive Encoder Right B 
	 * Port 4 - Arm Limit (Extend)
	 * Port 5 - Arm Limit (Retract)
	 * Port 6 - Ball Limit (fully seated)
	 * Port 7 - 
	 * Port 8 - 
	 * Port 9 - 
	 */
	
	public static final int DIO_DRIVE_ENC_LEFT_A = 0;
	public static final int DIO_DRIVE_ENC_LEFT_B = 1;
	public static final int DIO_DRIVE_ENC_RIGHT_A = 2;
	public static final int DIO_DRIVE_ENC_RIGHT_B = 3;
	public static final int DIO_LIM_ARM_EXTENDED = 4;
	public static final int DIO_LIM_ARM_RETRACTED = 5;
	public static final int DIO_LIM_BALL = 6;

	
	
	/*
	 * Analog Pins
	 * 
	 * Port 0 - Arm potentiometer
	 * Port 1 - 
	 * Port 2 - 
	 * Port 3 - 
	 */
	public static final int ANA_ARM_POT = 0;	

	/* Misc useful variables */
	public static final double DRIVE_PID_P = 0.006;
	public static final double DRIVE_PID_I = 0.000;
	public static final double DRIVE_PID_D = 0.002;
	public static final double ARM_PID_P = 0.006;
	public static final double ARM_PID_I = 0.000;
	public static final double ARM_PID_D = 0.002;
	public static boolean VERBOSE_CONSOLE = false;
	
	/* Power Channels
	 * 
	 * Port 0 - 
	 * Port 1 - 
	 * Port 2 - 
	 * Port 3 - 
	 * Port 4 -
	 * Port 5 - 
	 * Port 6 -
	 * Port 7 -
	 * Port 8 -
	 * Port 9 - 
	 * Port 10 - 
	 * Port 11 -
	 * Port 12 -
	 * Port 13 -
	 * Port 14 -
	 * Port 15 - 
	 */

}