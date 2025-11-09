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
        Launcher launcher = new Launcher(hardwareMap);
        Vector2d target = new Vector2d(20,10);

        waitForStart();
        double dx = (target.x - beginPose.position.x);
        double dy = (target.y - beginPose.position.y);

        Action turn = drive.actionBuilder(beginPose)
                .turn(Math.atan(dy/dx))
                .build();
        Action shoot = launcher.Launch(Math.hypot(dx,dy)*0.0254);


        Actions.runBlocking(
                new SequentialAction(
                        turn,
                        shoot
                )
        );



    }
}
