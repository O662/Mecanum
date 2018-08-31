package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MecanumDriveTrain extends Command {
	
	public MecanumDriveTrain() {
		requires(Robot.driveTrain);
	}
	
	// Called just before this Command runs the first time
		@Override
		protected void initialize() {
			Robot.driveTrain.setBrake(false);
			//Robot.driveTrain.setDefaltRampRate();
		}

		// Called repeatedly when this Command is scheduled to run
		@Override
		protected void execute() {
			Robot.driveTrain.drive(Robot.oi.getJoystick());
		}

		
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
