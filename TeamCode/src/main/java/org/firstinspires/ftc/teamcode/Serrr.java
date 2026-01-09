package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class Serrr extends LinearOpMode {
    private Servo push;
    private Rev2mDistanceSensor dis;

    @Override
    public void runOpMode() throws InterruptedException {
        push = hardwareMap.get(Servo.class,"PushDaddy");
        dis = hardwareMap.get(Rev2mDistanceSensor.class,"dis");
        double pose = 0;

        waitForStart();

        while (opModeIsActive()){
            pose = gamepad1.right_stick_x;
            push.setPosition(pose);
            telemetry.addData("POSE",push.getPosition());
            telemetry.addData("Distance",dis.getDistance(DistanceUnit.INCH));

            telemetry.update();
        }
    }

}
