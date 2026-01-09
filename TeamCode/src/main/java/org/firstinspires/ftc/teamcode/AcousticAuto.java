package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.ftc.PinpointIMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous
public class AcousticAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d beginPose =  new Pose2d(new Vector2d(24,24),Math.toRadians(45));
        MecanumDrive drive = new MecanumDrive(hardwareMap,beginPose);


        intake intake = new intake();
        intake.init(hardwareMap);
        Launcher launcher = new Launcher();
        launcher.init(hardwareMap);

        waitForStart();

        Action FirstTake = intake.Take(30);

        Action FirstLaunch = launcher.LAUNCH();

        Action FirstDrive1 = drive.actionBuilder(beginPose)
                .strafeToSplineHeading(new Vector2d(12,0),Math.toRadians(45))
                .build();

        Action FirstTurn = drive.actionBuilder(new Pose2d(new Vector2d(12,0),Math.toRadians(45)))
                .turnTo(Math.toRadians(-90))
                .build();

        Action FirstDrive2 = drive.actionBuilder(new Pose2d(new Vector2d(12,0),Math.toRadians(270)))
                .lineToY(58.5)
                .build();

        Action FirstDrive3 = drive.actionBuilder(new Pose2d(new Vector2d(12,58.5),Math.toRadians(270)))
                .strafeToSplineHeading(new Vector2d(24,24), Math.toRadians(45))
                .build();

        Action SecondDrive1 = drive.actionBuilder(new Pose2d(new Vector2d(24,24), Math.toRadians(45)))
                .strafeToSplineHeading(new Vector2d(-18,0),Math.toRadians(270))
                .build();


        Action SecondDrive2 = drive.actionBuilder(new Pose2d(new Vector2d(-20,0),Math.toRadians(45)))
                .lineToY(65)
                .build();


        Action SecondDrive3 = drive.actionBuilder(new Pose2d(new Vector2d(-20,65),Math.toRadians(270)))
                .strafeToSplineHeading(new Vector2d(24,24), Math.toRadians(45))
                .build();

        Action SecondLaunch = launcher.LAUNCH();

        Actions.runBlocking(new ParallelAction(FirstTake, new SequentialAction(
                FirstLaunch,
                FirstDrive1,
                FirstDrive2,
                FirstDrive3
        )));






        //Actions.runBlocking(SecondDrive1);


       // Actions.runBlocking(new ParallelAction(
        //        SecondTake,
        //       SecondDrive2
       // ));


      //  Actions.runBlocking(SecondDrive3);

        Actions.runBlocking(SecondLaunch);




    }
}
