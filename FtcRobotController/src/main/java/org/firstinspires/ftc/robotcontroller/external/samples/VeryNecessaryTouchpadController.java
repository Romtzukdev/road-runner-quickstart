package org.firstinspires.ftc.robotcontroller.external.samples;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Very Necessary Touchpad Controller", group = "Touch Like How I Touch Kids")
public class VeryNecessaryTouchpadController extends LinearOpMode {
    private DcMotor frontLeft, frontRight, backLeft, backRight;

    @Override
    public void runOpMode() throws InterruptedException {
        //Setup (As Usual)
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        while(opModeIsActive())
        {
            //Man I Love Touching Kids
            if(gamepad1.touchpad_finger_1 && gamepad1.touchpad_finger_2)
            {
                double axial = gamepad1.touchpad_finger_1_y;  // Note: pushing stick forward gives negative value
                double lateral = gamepad1.touchpad_finger_1_x;
                double yaw = gamepad1.touchpad_finger_2_x + 0;

                // Combine the joystick requests for each axis-motion to determine each wheel's power.
                // Set up a variable for each drive wheel to save the power level for telemetry.
                double frontLeftPower = axial + lateral + yaw;
                double frontRightPower = axial - lateral - yaw;
                double backLeftPower = axial - lateral + yaw;
                double backRightPower = axial + lateral - yaw;

                frontLeft.setPower(frontLeftPower * 0.5);
                frontRight.setPower(frontRightPower * 0.5);
                backLeft.setPower(backLeftPower * 0.5);
                backRight.setPower(backRightPower * 0.5);

                telemetry.addLine(String.format("Finger 1: x=%5.2f y=%5.2f\n", gamepad1.touchpad_finger_1_x, gamepad1.touchpad_finger_1_y));
            }else //Oops I Forgor
            {
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
            }

            telemetry.update();
        }
    }
}
