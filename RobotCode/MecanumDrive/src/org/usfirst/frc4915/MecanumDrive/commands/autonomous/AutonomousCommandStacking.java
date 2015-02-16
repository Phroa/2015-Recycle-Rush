package org.usfirst.frc4915.MecanumDrive.commands.autonomous;
import org.usfirst.frc4915.MecanumDrive.commands.drive.MoveStraightPositionModeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.drive.StrafeCommand;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorJumpToPosition;
import org.usfirst.frc4915.MecanumDrive.commands.elevator.ElevatorMoveToHeight;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.CloseGrabber;
import org.usfirst.frc4915.MecanumDrive.commands.grabber.OpenGrabber;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommandStacking extends CommandGroup {
    
    public AutonomousCommandStacking() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.

    	addParallel(new ElevatorMoveToHeight());
    	
    	System.out.println("Moving Elevator (level 1.5)");
    	addSequential(new ElevatorJumpToPosition(1.5));
    	System.out.println("Closing Grabber");
    	addSequential(new CloseGrabber());
    	System.out.println("Moving Elevator (level 2.5)");
    	addSequential(new ElevatorJumpToPosition(2.5));
    	System.out.println("Strafing");
    	//strafe left 1.9ft
    	addSequential(new StrafeCommand(-1.9,.5));
    	addSequential(new OpenGrabber());
    	addSequential(new ElevatorJumpToPosition(0));
    	addSequential(new CloseGrabber());
    	addSequential(new ElevatorJumpToPosition(1));
    	System.out.println("Driving back 12 ft");
    	addSequential(new MoveStraightPositionModeCommand(-12, 0.7));
    }
}