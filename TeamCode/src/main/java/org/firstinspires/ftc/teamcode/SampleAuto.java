package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class SampleAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d beginPose = new Pose2d(new Vector2d(-30,0),0);

        MecanumDrive drive = new MecanumDrive(hardwareMap,beginPose);
        waitForStart();

        Action path = drive.actionBuilder(beginPose)
                .lineToX(30)
                .build();

        Actions.runBlocking(new SequentialAction(path));
    }
}
