// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.liftSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class liftCommand extends Command {

  private liftSubsystem LIFT_SUBSYSTEM; 
  private Joystick joystick;

  double down;

  /** Creates a new liftCommand. */
  public liftCommand(liftSubsystem lift, Joystick joy) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.LIFT_SUBSYSTEM = lift;
    this.joystick = joy;

    addRequirements(LIFT_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    down = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    down = joystick.getRawAxis(1);

    LIFT_SUBSYSTEM.set(down);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    LIFT_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
