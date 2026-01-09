package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleTest extends OpMode {
    public MecanumDrive drive;
    @Override
    public void init() {
        Pose2d beginPose =  new Pose2d(new Vector2d(24,24),Math.toRadians(45));
        drive = new MecanumDrive(hardwareMap,beginPose);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        telemetry.addData("pose",drive.localizer.getPose().toString());
        telemetry.update();
    }



    }

