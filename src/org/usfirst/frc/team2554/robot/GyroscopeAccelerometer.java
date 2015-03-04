package org.usfirst.frc.team2554.robot;

import edu.wpi.first.wpilibj.I2C;

public class GyroscopeAccelerometer {
	I2C gyro;
	public GyroscopeAccelerometer()
	{
		gyro = new I2C(I2C.Port.kOnboard, 0x68);
	}
	
	public void initialize() {
		gyro.write(0x6B, 0x03); // Power
		gyro.write(0x1A, 0x18); // Basic Config
		gyro.write(0x1B, 0x00);
	}
	
	static double degrees = 0;
	public double getDegrees(double deltaTime) {
		byte[] angle = new byte[1];
		gyro.read(0x47, 1, angle);
		System.out.println(angle[0]);
		double rotation = (angle[0] * deltaTime) * 2;
		
		degrees += rotation;
		
		return -degrees;
	}
	

}
