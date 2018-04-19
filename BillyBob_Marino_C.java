package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Daniel on 3/26/2018.
 */

@TeleOp
public class BillyBob_Marino_C extends LinearOpMode {
    private DcMotor arm;
    private DcMotor left_wheel;
    private DcMotor right_wheel;
    private Servo left_claw;
    private Servo right_claw;
    private TouchSensor touch;

    @Override
    public void runOpMode() {
        arm = hardwareMap.get(DcMotor.class, "arm");
        left_wheel = hardwareMap.get(DcMotor.class, "left_wheel");
        right_wheel = hardwareMap.get(DcMotor.class, "right_wheel");
        left_claw = hardwareMap.get(Servo.class, "left_claw");
        right_claw = hardwareMap.get(Servo.class, "right_claw");
        touch = hardwareMap.get(TouchSensor.class, "touch");

        double left_wheel_power = 0;
        double right_wheel_power = 0;
        double arm_position = 0;
        double arm_speed;
        double drive_speed;

        arm.setPower(-0.1);

        while (!touch.isPressed()) {
        }

        arm.setPower(0.1);

        while (touch.isPressed()) {
        }

        arm.setPower(0);

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {

            if (this.gamepad1.right_bumper) {
                drive_speed = 10;
            } else {
                drive_speed = 1;
            }

            left_wheel.setPower(-this.gamepad1.left_stick_y / drive_speed);
            right_wheel.setPower(this.gamepad1.right_stick_y / drive_speed);

            // makes arm motor slower
            if (this.gamepad2.right_bumper) {
                arm_speed = 10;
            } else {
                arm_speed = 5;
            }

            double pos = arm.getCurrentPosition();
            if (pos > 0 && pos < 2000
                    || pos >= 2000 && this.gamepad2.left_stick_y > 0
                    || pos <= 0 && this.gamepad2.left_stick_y < 0) {
                arm.setPower(-this.gamepad2.left_stick_y / arm_speed);
                telemetry.addData("touch", arm.getCurrentPosition());
                telemetry.update();
            } else {
                arm.setPower(0);
            }

            if (this.gamepad2.x) {
                left_claw.setPosition(180);
                right_claw.setPosition(0);
            } else {
                left_claw.setPosition(0);
                right_claw.setPosition(180);
            }


        }
    }
}
