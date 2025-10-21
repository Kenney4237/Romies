// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import static frc.robot.Constants.DrivetrainConstants.*;
import static edu.wpi.first.units.Units.Centimeters;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.wpilibj2.command.Commands.*;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
  

  // The robot's subsystems and commands are defined here...
  


  
  private final RomiDrivetrain drive = new RomiDrivetrain();
  private Distance dist = Centimeters.of(10);
  private Angle angle = Degrees.of(90);

  private final ExampleCommand m_autoCommand = new ExampleCommand(drive);


  private final DriveCommand driveCommand;
  private final Joystick stick = new Joystick(0);

  private double translateDir = Math.signum(dist.baseUnitMagnitude());
  private Command translateCommand;

  private double turnDir = Math.signum(angle.baseUnitMagnitude());
  private Command turnCommand;
  
  private final SendableChooser<Command> chooser = new SendableChooser<>();

  //private final Command translateCommand;
    
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
      driveCommand = new DriveCommand(drive, () -> stick.getY(), () -> stick.getX());

      translateCommand = runEnd(() -> drive.arcadeDrive(translateDir * kDefaultDriveSpeed, 0), () -> drive.arcadeDrive(0, 0), drive)
        .until(() -> drive.getAverageDistance().times(translateDir).gte(dist.times(translateDir)))
          .beforeStarting(runOnce(drive::resetEncoders));
          double turnDir = Math.signum(dist.baseUnitMagnitude());

      turnCommand = runEnd(() -> drive.arcadeDrive(0, turnDir * kDefaultRotSpeed), () -> drive.arcadeDrive(0, 0), drive)
        .until(() -> drive.getAngle().times(turnDir).gte(angle.times(turnDir)))
          .beforeStarting(runOnce(drive::resetGyro));

      
      drive.setDefaultCommand(driveCommand);  
      
      // Configure the button bindings
      configureButtonBindings();

      /*-----------------------------------*/

      chooser.addOption("drive 6 inches", translateCommand);
      chooser.addOption("turn 180", turnCommand);
      SmartDashboard.putData(chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return chooser.getSelected();
    // An ExampleCommand will run in autonomous
  }
}
