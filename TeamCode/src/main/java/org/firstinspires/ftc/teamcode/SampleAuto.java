package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
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

        Pose2d beginPose = new Pose2d(new Vector2d(-30,0),Math.toRadians(180));

        MecanumDrive drive = new MecanumDrive(hardwareMap,beginPose);
        Intake intake = new Intake(hardwareMap);
        waitForStart();

        Action first = drive.actionBuilder(beginPose)
                .strafeTo(new Vector2d(-4, 0))
                .turn(Math.toRadians(-90))
                .strafeTo(new Vector2d(-4, -56))
                .waitSeconds(2)
                .build();
        SequentialAction s = new SequentialAction(
                intake.Take(1,20000)
        );
        Actions.runBlocking(
            new ParallelAction(
                    first,
                    s
            )

        );




    }
}
