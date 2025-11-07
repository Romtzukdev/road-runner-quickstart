package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous
public class SampleAuto extends LinearOpMode {
    private Rev2mDistanceSensor dis;
    @Override
    public void runOpMode() throws InterruptedException {
        dis = hardwareMap.get(Rev2mDistanceSensor.class,"dis");

        Pose2d beginPose = new Pose2d(new Vector2d(0,0),0);

        MecanumDrive drive = new MecanumDrive(hardwareMap,beginPose);
        waitForStart();
        dis.initialize();
        Action move = drive.actionBuilder(beginPose)
                .strafeTo(new Vector2d(0,-6))
                .build();
        while (opModeIsActive()){
            if (dis.getDistance(DistanceUnit.CM) < 50){
                Actions.runBlocking(move);
            }
        }
    }
}
