package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp(name="TeleOp", group="TeleOp")
public class tele extends LinearOpMode {
    public Limelight3A limelight;
    public DcMotor LeftFront, RightFront, LeftBack, RightBack;

    @Override
    public void runOpMode() throws InterruptedException {
        LeftFront = hardwareMap.get(DcMotor.class, "LeftFront");
        RightFront = hardwareMap.get(DcMotor.class, "RightFront");
        LeftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        RightBack = hardwareMap.get(DcMotor.class, "RightBack");

        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);
        limelight.start();
        limelight.pipelineSwitch(6);


        waitForStart();

        while (opModeIsActive()){
            LLResult result = limelight.getLatestResult();
            Pose3D botpose = result.getBotpose();
            telemetry.addData("X",botpose.getPosition().x);
            telemetry.addData("Y",botpose.getPosition().y);
            telemetry.addData("Z",botpose.getPosition().z);
            telemetry.update();


        }
    }

    }

