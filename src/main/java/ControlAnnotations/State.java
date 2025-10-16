package ControlAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class to be automatically generated into the RobotStates class.
 * Every class marked with this annotation must extend {@link edu.wpi.first.wpilibj2.command.Command}
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR, ElementType.TYPE})
public @interface State {
}
