package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RomiDrivetrain;

public class DriveCommand extends Command {
    
    private final RomiDrivetrain drive;
    private final DoubleSupplier speed;
    private final DoubleSupplier rot;

    public DriveCommand(RomiDrivetrain drive, DoubleSupplier speed, DoubleSupplier rot) {
        this.drive = drive;
        this.speed = speed;
        this.rot = rot;

        addRequirements(drive);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drive.arcadeDrive(speed.getAsDouble(), rot.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {}

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }

}
