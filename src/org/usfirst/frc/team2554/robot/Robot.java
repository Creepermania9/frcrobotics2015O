
package org.usfirst.frc.team2554.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	Joystick joystickright;
	Relay rel, rel2;	
	GyroscopeAccelerometer gyro;
	DriveSystem mainDrive;
	DigitalInput up, down;
	AnalogInput ai;
	public void robotInit() {
    	
    	joystickright = new Joystick(0);
    	gyro = new GyroscopeAccelerometer();
    	mainDrive = new DriveSystem(joystickright, gyro, new int[] { 3, 2, 1, 0});
    	rel = new Relay(0, Direction.kBoth);
    	rel2 = new Relay(1, Direction.kBoth);
    	down = new DigitalInput(0);
    	up = new DigitalInput(1);
    	ai = new AnalogInput(3);
    	gyro.initialize();
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	System.out.println(ai.getValue());
    	mainDrive.DrivePolar();   	
    	if((joystickright.getRawButton(4) || joystickright.getRawButton(3)) && 
    			(joystickright.getRawButton(6) || joystickright.getRawButton(5))
    			&& joystickright.getRawButton(11) && joystickright.getRawButton(12)) {
    		rel.set(Value.kOff); 
    	}
    	else if((joystickright.getRawButton(4) || joystickright.getRawButton(3)) && !down.get()) {//Lifter down
    		rel.set(Value.kReverse);
    	}
    	else if((joystickright.getRawButton(6) || joystickright.getRawButton(5)) && !up.get()) {//Lifter up
    		rel.set(Value.kForward);
    	}
    	else {
    		rel.set(Value.kOff);
    	}
    	
    	if (joystickright.getRawButton(12)){//close arm?
    		rel2.set(Value.kForward);
    	}
    	else if (joystickright.getRawButton(11)){//open arm?
    		rel2.set(Value.kReverse);
    	}
    	else
    	{

    		rel2.set(Value.kOff);
    	}
    		
        		
    }
    
    /**
     * This function is called periodically during test mode
     */
    
    public void testPeriodic() {
    
    }
    
}
































//belal is a phat potato