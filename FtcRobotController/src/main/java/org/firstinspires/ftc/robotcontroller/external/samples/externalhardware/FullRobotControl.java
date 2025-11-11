package org.firstinspires.ftc.robotcontroller.external.samples.externalhardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

//FullRobotControl? Sounds Familiar
@TeleOp(name = "Full Robot Control", group = "11AA So It Will Be The First")
public class FullRobotControl extends LinearOpMode
{
    //Motor Vars
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;

    private boolean slowMode = false;

    //Wheel Vars
    private double speed = 0;
    private double lerpSpeed = 0.1;
    private double intakeSpeed = 0;

    private DcMotor leftWheel, rightWheel;
    private DcMotor intake;

    @Override
    public void runOpMode() throws InterruptedException
    {
        //Motor Setup
        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeft");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRight");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRight");

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        //Wheel Setup
        leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");
        intake = hardwareMap.get(DcMotor.class, "intake");

        leftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        rightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            if(gamepad1.leftStickButtonWasPressed())
                slowMode = !slowMode;

            double max;

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral = gamepad1.left_stick_x;
            double yaw = gamepad1.right_stick_x;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double frontLeftPower = axial + lateral + yaw;
            double frontRightPower = axial - lateral - yaw;
            double backLeftPower = axial - lateral + yaw;
            double backRightPower = axial + lateral - yaw;

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(backRightPower));

            if (max > 1.0) {
                frontLeftPower /= max;
                frontRightPower /= max;
                backLeftPower /= max;
                backRightPower /= max;
            }

            // Send calculated power to wheels
            frontLeftDrive.setPower(frontLeftPower * (slowMode ? 0.5 : 1));
            frontRightDrive.setPower(frontRightPower * (slowMode ? 0.5 : 1));
            backLeftDrive.setPower(backLeftPower * (slowMode ? 0.5 : 1));
            backRightDrive.setPower(backRightPower * (slowMode ? 0.5 : 1));

            //Controls
            if(gamepad2.aWasPressed())
            {
                speed = 0.8 - speed;
                intakeSpeed = 0.5 - intakeSpeed;
            }

            //I AM SPEED! (ok it's not THAT fast)
            leftWheel.setPower(lerp(leftWheel.getPower(), speed, lerpSpeed));
            rightWheel.setPower(lerp(rightWheel.getPower(), speed, lerpSpeed));
            intake.setPower(intakeSpeed);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", frontLeftPower, frontRightPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", backLeftPower, backRightPower);
            telemetry.addData("Left Wheel Power", leftWheel.getPower());
            telemetry.addData("Right Wheel Power", rightWheel.getPower());
            telemetry.addData("Intake Power", intakeSpeed);
            telemetry.update();
        }
    }

    double lerp(double a, double b, double f)
    {
        return (a * (1.0 - f)) + (b * f);
    }
}
