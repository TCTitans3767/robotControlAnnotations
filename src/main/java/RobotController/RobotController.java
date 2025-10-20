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

    protected static Command defaultMode = Commands.none();
    protected static Command defaultDriveMode = Commands.none();

    protected static Command panicCommand = Commands.none();

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

    /**
     * Sets the current {@link Command} to the given {@link Command}.
     * The new {@link Command} will be scheduled if the current {@link Command} is different from the given {@link Command}
     * The new {@link Command} must directly control only the robot's drivetrain mechanism, not the superstructure.
     * @param newMode
     */
    public static void setDriveModeCommand(Command newMode) {
        if (currentDriveMode != newMode && currentDriveMode != null) {
            currentDriveMode.cancel();
        }
        previousDriveMode = currentDriveMode;
        currentDriveMode = newMode;
        currentDriveMode.schedule();
    }

    /**
     * Gets the current {@link Command} that the robot is running.
     * @return The current {@link Command}.
     */
    public static Command getCurrentMode() {
        return currentMode;
    }

    /**
     * Gets the current {@link Command} that the robot is running for the drivetrain.
     * @return The current drivetrain {@link Command}.
     */
    public static Command getCurrentDriveMode() {
        return currentDriveMode;
    }

    /**
     * Gets the previous {@link Command} that the robot was running.
     * @return The previous {@link Command}.
     */
    public static Command getPreviousMode() {
        return previousMode;
    }

    /**
     * Gets the previous {@link Command} that the robot was running for the drivetrain.
     * @return The previous drivetrain {@link Command}.
     */
    public static Command getPreviousDriveMode() {
        return previousDriveMode;
    }

    /**
     * Sets the default {@link Command} that the robot will return to when {@link RobotController#returnToDefaultMode()} is called.
     * @param defaultMode The default {@link Command}.
     */
    public static void setDefaultMode(Command defaultMode) {
        RobotController.defaultMode = defaultMode;
    }

    /**
     * Sets the default {@link Command} that the robot's drivetrain will return to when {@link RobotController#returnToDefaultMode()} is called.
     * @param defaultDriveMode The default drivetrain {@link Command}.
     */
    public static void setDefaultDriveMode(Command defaultDriveMode) {
        RobotController.defaultDriveMode = defaultDriveMode;
    }

    /**
     * Returns the robot to the default {@link Command} set by {@link RobotController#setDefaultMode(Command)}.
     */
    public static void returnToDefaultMode() {
        setCurrentMode(defaultMode);
    }

    /**
     * Immediately stops the currently running {@link Command}s for both the superstructure and the drivetrain.
     * This function will then schedule the panic {@link Command} if it has been set using {@link RobotController#setPanicCommand(Command)}.
     * This method is intended to be used in emergency situations where the robot needs to be stopped immediately.
     */
    public static void panic() {
        if (currentMode != null) {
            currentMode.cancel();
        }
        if (currentDriveMode != null) {
            currentDriveMode.cancel();
        }
        if (panicCommand != null) {
            panicCommand.schedule();
        }
    }

    /**
     * Sets the panic {@link Command} that will be scheduled when {@link RobotController#panic()} is called.
     * The panicCommand must be a command that allows complete and safe manual control of the robot.
     * This command must be ready to be scheduled at any time without additional setup.
     * @param panicCommand The panic {@link Command}.
     */
    public static void setPanicCommand(Command panicCommand) {
        RobotController.panicCommand = panicCommand;
    }
}
