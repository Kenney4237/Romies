package frc.robot.commands;

import static edu.wpi.first.wpilibj2.command.Commands.runEnd;
import static edu.wpi.first.wpilibj2.command.Commands.runOnce;
import static frc.robot.Constants.DrivetrainConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.units.measure.Distance;

public class TranslateCommand extends Command {
    
    private RomiDrivetrain drive;
    private Distance dist;
    private double dir;
    private double translateDir = Math.signum(dist.baseUnitMagnitude());
    private Command translateCommand;

    public TranslateCommand(RomiDrivetrain drive, Distance dist) {
        this.drive = drive;
        this.dist = dist;
        this.dir = Math.signum(this.dist.baseUnitMagnitude());
        
        addRequirements(drive);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drive.resetEncoders();
    }

    @Override
    public void execute() {
        drive.arcadeDrive(kDefaultDriveSpeed * dir, 0);
    }

    @Override
    public boolean isFinished() {
        return drive.getAverageDistance().times(dir).gte(dist.times(dir));
    }

    @Override
    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
}

}
