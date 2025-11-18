package org.firstinspires.ftc.robotcontroller.external.samples.externalhardware;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.List;

@TeleOp(name = "Limelight Movement Bullshit", group = "Chudy Sucks")
public class LimelightMovement extends LinearOpMode {

    private Limelight3A limelight;

    private DcMotor frontLeft, frontRight, backLeft, backRight;

    @Override
    public void runOpMode() throws InterruptedException
    {
        limelight = hardwareMap.get(Limelight3A.class, "Ethernet Device");

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        telemetry.setMsTransmissionInterval(11);

        limelight.pipelineSwitch(0);

        /*
         * Starts polling for data.
         */
        limelight.start();

        waitForStart();

        while (opModeIsActive()) {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);

            LLResult result = limelight.getLatestResult();
            if (result != null) {
                if (result.isValid()) {

                    List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
                    for (LLResultTypes.FiducialResult fr : fiducialResults) {
                        switch (fr.getFiducialId()) //Fuck That I Don't Care Spagetti Code HERE WE GO!!!!!
                        {
                            case 21:
                                frontLeft.setPower(-1);
                                frontRight.setPower(-1);
                                backLeft.setPower(1);
                                backRight.setPower(1);
                            case 22:
                                frontLeft.setPower(1);
                                frontRight.setPower(1);
                                backLeft.setPower(1);
                                backRight.setPower(1);
                            case 23:
                                frontLeft.setPower(1);
                                frontRight.setPower(1);
                                backLeft.setPower(-1);
                                backRight.setPower(-1);
                        }
                    }

                }
            }

            //telemetry.addData("LIMELIGJTHGGKSGRWG", limelight);
            telemetry.update();
        }
    }
}
