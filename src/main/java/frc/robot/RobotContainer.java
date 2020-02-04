/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.RobotMap;
import frc.robot.commands.AutoAlign;
import frc.robot.commands.Rumble;
import frc.robot.commands.RunIntake;
import frc.robot.commands.SetActuatorSpeed;
import frc.robot.commands.SetLeftWinchSpeed;
import frc.robot.commands.SetRightWinchSpeed;
import frc.robot.commands.Shoot;
import frc.robot.commands.TurnRotator;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final DifferentialDrive diffDrive = new DifferentialDrive(RobotMap.leftMaster, RobotMap.rightMaster);
    private final Drive drive = new Drive(RobotMap.leftMaster, RobotMap.rightMaster);
    private final Intake intake = new Intake(RobotMap.intake);
    private final Shooter shooter = new Shooter(RobotMap.topShooter, RobotMap.bottomShooter);
    private final Actuator actuator = new Actuator(RobotMap.actuator);
<<<<<<< HEAD
    private final Winch winch = new Winch(RobotMap.leftWinch, RobotMap.rightWinch);
=======
    private final Rotator rotator = new Rotator(RobotMap.rotator);
>>>>>>> f0973b4c82133300a7d77784a9525249f02a4d23

    private final XboxController controller = new XboxController(0);
    private final ControllerAxis 
        leftX = new ControllerAxis(controller, 0), 
        leftY = new ControllerAxis(controller, 1);
        //leftTrigger = new ControllerAxis(controller, 2),
        //rightTrigger = new ControllerAxis(controller, 3),
        //rightX = new ControllerAxis(controller, 4), 
        //rightY = new ControllerAxis(controller, 5);

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        SmartDashboard.putNumber("Top Flywheel", 0.0);
        SmartDashboard.putNumber("Bottom Flywheel", 0.0);
        configureButtonBindings();

        // The drive bindings need to be put in this format:
        // drive.setDefaultCommand(new RunCommand(() -> drive.controlScheme(...), drive))
        // The second "drive" is there because the RunCommand function must require drive to run it. 
        drive.setDefaultCommand(new RunCommand(() -> diffDrive.arcadeDrive(leftY.getAsDouble(), -leftX.getAsDouble(), controller.getStickButtonPressed(Hand.kRight)), drive));
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        JoystickButton aButton = new JoystickButton(controller, 1);
        aButton.whileHeld(new RunIntake(intake, 0.75));
        JoystickButton bButton = new JoystickButton(controller, 2);
        bButton.whileHeld(new Shoot(shooter));
        JoystickButton xButton = new JoystickButton(controller, 3);
        xButton.whileHeld(new AutoAlign(drive));
        JoystickButton yButton = new JoystickButton(controller, 4);
        yButton.whileHeld(new RunIntake(intake, -0.4));
        JoystickButton leftBumper = new JoystickButton(controller, 5);
<<<<<<< HEAD
        //leftBumper.whileHeld(new SetActuatorSpeed(actuator, -.1));
        leftBumper.whileHeld(new SetLeftWinchSpeed(winch, -1.0));
        JoystickButton rightBumper = new JoystickButton(controller, 6);
        //rightBumper.whileHeld(new SetActuatorSpeed(actuator, .1));
        rightBumper.whileHeld(new SetRightWinchSpeed(winch, -1.0));
        JoystickButton leftStick = new JoystickButton(controller, 9);
        leftStick.whileHeld(new SetLeftWinchSpeed(winch, 1.0));
        JoystickButton rightStick = new JoystickButton(controller, 10);
        rightStick.whileHeld(new SetRightWinchSpeed(winch, 1.0));
=======
        leftBumper.whileHeld(new SetActuatorSpeed(actuator, -.25));
        JoystickButton rightBumper = new JoystickButton(controller, 6);
        rightBumper.whileHeld(new SetActuatorSpeed(actuator, .25));
        JoystickButton menuButton = new JoystickButton(controller, 8);
        menuButton.whileHeld(new TurnRotator(rotator, 0.5));
>>>>>>> f0973b4c82133300a7d77784a9525249f02a4d23
    }

}
