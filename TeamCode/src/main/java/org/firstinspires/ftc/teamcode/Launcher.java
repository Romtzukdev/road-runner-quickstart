package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Launcher {
    private DcMotor Left, Right;
    private double radius = 0.1;
    public Launcher(HardwareMap hardwareMap){
        this.Left = hardwareMap.get(DcMotor.class, "LeftLauncher");
        this.Right = hardwareMap.get(DcMotor.class, "RightLauncher");
    }
    public double getPower(double Distance){
        double g = 9.81;
        double velocity = Math.sqrt(g*Distance);
        double power = (60*velocity)/(2*Math.PI*radius);
        return (power)/(1620);
    }

    public Action Launch(double Distance){
        double power = getPower(Distance);
        return new Action() {

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                Left.setPower(power);
                Right.setPower(power);
                ElapsedTime time = new ElapsedTime();
                while(time.milliseconds()<200){
                    double i;
                }
                return false;
            }
        };
    }



}
