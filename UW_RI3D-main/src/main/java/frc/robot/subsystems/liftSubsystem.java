// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

// import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
// import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class liftSubsystem extends SubsystemBase {

  private SparkMax leftLiftMotor = new SparkMax(1, MotorType.kBrushed);
  private SparkMax rightLiftMotor = new SparkMax(0, MotorType.kBrushed);
  private SparkMaxConfig motorConfig = new SparkMaxConfig();

  private RelativeEncoder leftLiftMotorEncoder = leftLiftMotor.getEncoder();
  private RelativeEncoder rightLiftMotorEncoder = rightLiftMotor.getEncoder();

  // looks like it's getting deprecated
  // private MotorControllerGroup liftMotors = new MotorControllerGroup(leftLiftMotor, rightLiftMotor);

  /** Creates a new liftSubsystem. */
  public liftSubsystem() {
    configure();
    leftLiftMotorEncoder.setPosition(0); 
    rightLiftMotorEncoder.setPosition(0);
  }

  private void configure() {
    motorConfig.inverted(false);
    motorConfig.idleMode(IdleMode.kBrake);
    motorConfig.encoder.positionConversionFactor(1000);
    motorConfig.encoder.velocityConversionFactor(1000);
    motorConfig.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder);

    leftLiftMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
    rightLiftMotor.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);
  }

  public double leftLiftEncoderPosition() {
    return leftLiftMotorEncoder.getPosition();
  }

  public double rightLiftEncoderPosition() {
    return rightLiftMotorEncoder.getPosition();
  }

  public void set(double down){
    leftLiftMotor.set(1); // turn motor on, forward
    rightLiftMotor.set(1);
  }

  public void stop(){
    leftLiftMotor.set(0); // turn motor off
    rightLiftMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("left lift encoder",  leftLiftEncoderPosition()); 
    SmartDashboard.putNumber("right lift encoder", rightLiftEncoderPosition());
  }
}
