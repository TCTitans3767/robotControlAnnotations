package RobotController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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

    public static void setCurrentMode(Command newMode) {
        if (currentMode != newMode && currentMode != null) {
            currentMode.cancel();
        }
        previousMode = currentMode;
        currentMode = newMode;
        currentMode.schedule();
    }

    public static void setCurrentDriveMode(Command newMode) {
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
