package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Autonomous
public class AutoTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose =  new Pose2d(new Vector2d(-24,-24),Math.toRadians(90));
        MecanumDrive drive = new MecanumDrive(hardwareMap,beginPose);


        Action Drive = drive.actionBuilder(beginPose)
                .strafeToSplineHeading(new Vector2d(24,24),Math.toRadians(90))
                .build();
        waitForStart();

        Actions.runBlocking(Drive);



    }
}
