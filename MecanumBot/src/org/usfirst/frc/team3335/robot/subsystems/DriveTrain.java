package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import org.usfirst.frc.team3335.robot.Robot;
import org.usfirst.frc.team3335.robot.RobotMap;
import org.usfirst.frc.team3335.robot.commands.MecanumDriveTrain;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrain extends Subsystem {
	
	//creates motors
	private final WPI_TalonSRX /*motorLeft1,*/ motorLeft1, motorLeft2;
	private final WPI_TalonSRX /*motorRight1,*/ motorRight1, motorRight2;
	public final MecanumDrive drive;
	private int direction = RobotMap.DRIVE_TRAIN_FORWARD_DIRECTION;
	//initilizes the motors
	public DriveTrain() {
		super();
		motorLeft1 = new WPI_TalonSRX(RobotMap.MOTOR_DRIVE_LEFT1);
		motorLeft2 = new WPI_TalonSRX(RobotMap.MOTOR_DRIVE_LEFT2);
		motorRight1 = new WPI_TalonSRX(RobotMap.MOTOR_DRIVE_RIGHT1);
		motorRight2 = new WPI_TalonSRX(RobotMap.MOTOR_DRIVE_RIGHT2);
		SpeedControllerGroup left1 = new SpeedControllerGroup(motorLeft1);//left front
		SpeedControllerGroup left2 = new SpeedControllerGroup(motorLeft2);//left back
		SpeedControllerGroup right1 = new SpeedControllerGroup(motorRight1);//right front
		SpeedControllerGroup right2 = new SpeedControllerGroup(motorRight2);//right back
		
		//mecanum speed command math
		double rf,rb,lf,lb;
		double forward = -Robot.oi.getJoystick().getY();
		double right = Robot.oi.getJoystick().getX();
		double clockwise = Robot.oi.getJoystick().getZ();
		double K = .01;//the value that determines sensitivity of turning tweek to edit
		clockwise = K*clockwise;
		//inverse kinimatics
		rf = forward + clockwise + right;
		lf = forward - clockwise - right;
		lb = forward + clockwise - right;
		rb = forward - clockwise + right;
		left1.set(lf);
		left2.set(lb);
		right1.set(rf);
		right2.set(rb);
		
		drive = new MecanumDrive(left1,left2,right1,right2);

	}
	
		
	
	//sets the break mode for each motor
	public void setBrake(boolean brake) {
		// Formerly: frontLeft.enableBrakeMode(brake);
		// See https://github.com/CrossTheRoadElec/Phoenix-Documentation#installing-phoenix-framework-onto-your-frc-robot
		NeutralMode mode = brake ? NeutralMode.Brake : NeutralMode.Coast;

		//motorLeft1.setNeutralMode(mode);
		motorLeft1.setNeutralMode(mode);
		motorLeft2.setNeutralMode(mode);
		//motorRight1.setNeutralMode(mode);
		motorRight1.setNeutralMode(mode);
		motorRight2.setNeutralMode(mode);
	}
	public void setDirection(int direction) {
		this.direction = direction * RobotMap.DRIVE_TRAIN_FORWARD_DIRECTION;
	}
	
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MecanumDriveTrain());
		// TODO Auto-generated method stub

	}
	//what is being seen by the mecanumDriveTrain class 
	public void drive(Joystick joystick) {
		//driveOrig(joystick);
		driveMecanum(joystick);
		
	}
	//what is driving the robot
	public void driveMecanum(Joystick joystick) {
		
		drive.driveCartesian(Robot.oi.stick.getX(), Robot.oi.stick.getY(), Robot.oi.stick.getZ(), 0);
		
		Timer.delay(0.01);
		
		
	}
	

}
