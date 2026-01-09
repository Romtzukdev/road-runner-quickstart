package org.firstinspires.ftc.robotcontroller.external.samples;

//Import Shit. Not Important (ok it's important but ignore that)
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Wheel Speed Test", group = "Test Stuff")
public class WheelSpeedTest extends LinearOpMode {
    private double speed = 0;

    private DcMotor leftWheel, rightWheel;

    @Override
    public void runOpMode() throws InterruptedException
    {
        //Start-Up
        leftWheel = hardwareMap.get(DcMotor.class, "wheel1");
        rightWheel = hardwareMap.get(DcMotor.class, "wheel2");

        leftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        rightWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive())
        {
            //Controls
            if(gamepad1.aWasPressed())
                speed = 0.2;
            else if(gamepad1.bWasPressed())
                speed = 0.4;
            else if(gamepad1.xWasPressed())
                speed = 0.6;
            else if(gamepad1.yWasPressed())
                speed = 0.8;
            else if(gamepad1.startWasPressed())
                speed = 1;
            else if(gamepad1.backWasPressed())
                speed = 0;

            //I AM SPEED! (ok it's not THAT fast)
            leftWheel.setPower(speed);
            rightWheel.setPower(speed);

            telemetry.addData("Speed", speed);
            telemetry.update();
        }
    }
}
