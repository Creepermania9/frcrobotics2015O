package org.usfirst.frc.team2554.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class DriveSystem {
	Joystick joystick;
	GyroscopeAccelerometer gyro;
	RobotDrive drive;
	
	public DriveSystem(Joystick main, GyroscopeAccelerometer mg, int[] ports) {
		joystick = main;
		gyro = mg;
		drive = new RobotDrive(ports[0], ports[1], ports[2], ports[3]);
		
	}
	public void DrivePolar() {
		double multiple = (-joystick.getThrottle() + 1.0) / 2.0;
    	double magnitude = joystick.getMagnitude() * multiple;
    	double direction = joystick.getDirectionDegrees();
    	double sign = (-joystick.getZ()) >= 0 ? 1.0 : -1.0;
    	double rotation = limit(0.25, Math.abs(joystick.getZ())) * multiple * sign;
    	if(rotation >= 0.25){
    		rotation += -0.25;
    	}
    	else if(rotation <= -0.25) {
    		rotation+= 0.25;
    	}
    	
    	if(joystick.getTrigger()) {
    		rotation = 0.0;
    	}
    	
    	if(joystick.getRawButton(2)) {
    		magnitude = 0;
    	}
    	   	
    	drive.mecanumDrive_Polar(magnitude, direction, rotation);
	}
	
	public void DriveAuto() {
		drive.mecanumDrive_Polar(0.2, 90, 0);
	}
	
	public void DriveCartesian() {
		
	}
	
	 double limit(double a, double b)
	 {
	    	if(b < a)
	    		return 0;
	    	else 
	    		return b;
	    }
	    double sign(double n) {
	    	if(n >= 0.0)
	    		return 1.0;
	    	return -1.0;
	    	
	    }

}
