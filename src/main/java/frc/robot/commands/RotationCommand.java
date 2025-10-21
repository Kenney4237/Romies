package frc.robot.commands;

import static edu.wpi.first.wpilibj2.command.Commands.runEnd;
import static edu.wpi.first.wpilibj2.command.Commands.runOnce;
import static frc.robot.Constants.DrivetrainConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.units.measure.*;

public class RotationCommand extends Command {
    
    private final RomiDrivetrain drive;
    private Angle angle;
    private double dir;
    private double turnDir = Math.signum(angle.baseUnitMagnitude());
    private Command turnCommand;


  public RotationCommand(RomiDrivetrain drive, Angle angle) {
    this.drive = drive;
    this.angle = angle;
    this.dir = Math.signum(this.angle.baseUnitMagnitude());

    addRequirements(drive);
  }

  @Override
  public void initialize() {
      drive.resetGyro();
  }

  @Override
  public void execute() {
      drive.arcadeDrive(0, kDefaultRotSpeed * dir);
  }

  @Override
  public boolean isFinished() {
      return drive.getAngle().times(dir).gte(angle.times(dir));
  }

  @Override
  public void end(boolean interrupted) {
      drive.arcadeDrive(0, 0);
  }

}
