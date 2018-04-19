package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController;


@TeleOp
public class HorizontalTestDrive extends LinearOpMode {
    private DcMotor Left_motor;
    private DcMotor Right_motor;
    private DcMotor Arm_motor;
    private Servo Left_servo;
    private Servo Right_servo;
    private TouchSensor Touch_Sensor;
    
    @Override
    public void runOpMode() {
        Left_motor = hardwareMap.get(DcMotor.class, "Left_motor");
        Right_motor = hardwareMap.get(DcMotor.class, "Right_motor");
        Arm_motor = hardwareMap.get(DcMotor.class, "Arm_motor");
        Left_servo = hardwareMap.get(Servo.class, "Left_servo");
        Right_servo = hardwareMap.get(Servo.class, "Right_servo");
        Touch_Sensor = hardwareMap.get(TouchSensor.class, "Touch_Sensor");

        double left_wheel_power = 0;
        double right_wheel_power = 0;
        double arm_position = 0;
        double arm_speed;
        double drive_speed;
        
        Left_servo.setPosition(180);
        Right_servo.setPosition(0);
        
        // Reset the arm position on init
        Arm_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        while (!Touch_Sensor.isPressed()) {
            Arm_motor.setPower(-0.1);
        }

        while (Touch_Sensor.isPressed()) {
            Arm_motor.setPower(0.1);
        }

        Arm_motor.setPower(0);

        Arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Arm_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {

            // Set speed for driving the robot
            if (this.gamepad1.right_bumper) {
                drive_speed = 10;
            } else {
                drive_speed = 1;
            }

            Left_motor.setPower(-this.gamepad1.left_stick_y / drive_speed);
            Right_motor.setPower(this.gamepad1.right_stick_y / drive_speed);

            
            if (this.gamepad2.right_bumper) {
                arm_speed = 10;
            } else {
                arm_speed = 4;
            }
            
            Arm_motor.setPower(-this.gamepad2.left_stick_y / arm_speed);
            
            if (this.gamepad2.x) {
                Left_servo.setPosition(0);
                Right_servo.setPosition(180);
            } else {
                Left_servo.setPosition(180);
                Right_servo.setPosition(0);
            }
        }
    }
}
