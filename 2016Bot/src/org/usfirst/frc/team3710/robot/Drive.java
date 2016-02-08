package org.usfirst.frc.team3710.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class Drive {

    private Talon talLeft, talRight;

    private Encoder encLeft, encRight;
    
    private byte flip = 1;
	
    public Drive (Talon tL, Talon tR, Encoder eL, Encoder eR ){
    	
    	talLeft = tL;
        talRight = tR;

        
        encLeft = eL;
        encRight = eR;      
        
    }
    
    /*
     * Set speed of left side motors
     */
    public void setDriveLeft (double speed) {
    	talLeft.set(-speed*flip);
    }
    
    /*
     * Set speed of right side motors
     */
    public void setDriveRight (double speed) {
    	talRight.set(speed*flip);
    }
    
    public void controlFlip() {
    	flip *= -1;
    }
}
