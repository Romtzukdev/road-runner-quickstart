package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous
public class LaunchTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Launcher launcher = new Launcher(hardwareMap);

        waitForStart();

        Action shoot = launcher.Rot(1);

        Actions.runBlocking(shoot);
    }
}
