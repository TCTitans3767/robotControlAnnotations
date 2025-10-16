package RobotController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The base robot state controller that will be the centerpoint of all robot functions.
 * The {@link RobotController} statically contains the current {@link Command} that the robot is running.
 * This {@link Command} can be changed by calling {@link RobotController#setCurrentMode(Command)}.
 */
public class RobotController extends SubsystemBase {

    /**
     * The current {@Command} that the robot is running.
     */
    protected static Command currentMode = Commands.none();
    protected static Command currentDriveMode = Commands.none();
    protected static Command previousMode = Commands.none();
    protected static Command previousDriveMode = Commands.none();

    public RobotController() {

    }

    @Override
    public void periodic() {

    }

    /**
     * Sets the current {@link Command} to the given {@link Command}.
     * The new {@link Command} will be scheduled if the current {@link Command} is different from the given {@link Command}
     * The new {@link Command} must directly control only the robot's superstructure mechanism, not the drivetrain.
     * @param newMode
     */
    public static void setCurrentMode(Command newMode) {
        if (currentMode != newMode && currentMode != null) {
            currentMode.cancel();
        }
        previousMode = currentMode;
        currentMode = newMode;
        currentMode.schedule();
    }

    public static void setDriveModeCommand(Command newMode) {
        if (currentDriveMode != newMode && currentDriveMode != null) {
            currentDriveMode.cancel();
        }
        previousDriveMode = currentDriveMode;
        currentDriveMode = newMode;
        currentDriveMode.schedule();
    }

    public static Command getCurrentMode() {
        return currentMode;
    }

    public static Command getCurrentDriveMode() {
        return currentDriveMode;
    }

    public static Command getPreviousMode() {
        return previousMode;
    }

    public static Command getPreviousDriveMode() {
        return previousDriveMode;
    }
}
