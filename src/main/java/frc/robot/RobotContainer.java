// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import static frc.robot.Constants.*;
import static edu.wpi.first.wpilibj2.command.Commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
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
  private final RomiDrivetrain m_romiDrivetrain = new RomiDrivetrain();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_romiDrivetrain);

  private final DriveCommand driveCommand;
  private final Joystick stick = new Joystick(0);
  //private final Command translateCommand;
    
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
      driveCommand = new DriveCommand(m_romiDrivetrain, () -> stick.getY(), () -> -stick.getX());

      //translateCommand = runEnd(() -> romiDrivetrain.arcadeDrive(translateDir * DrivetrainConstants.kDefaultDriveSpeed, 0), () -> romiDrivetrain.arcadeDrive(0, 0), romiDrivetrain).until(() -> romiDrivetrain.getAverageDistance().times(translateDir).gte(distToDrive.times(translateDir))).beforeStarting(runOnce(romiDrivetrain::resetEncoders));
      
      m_romiDrivetrain.setDefaultCommand(driveCommand);  
      
      // Configure the button bindings
      configureButtonBindings();
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
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
