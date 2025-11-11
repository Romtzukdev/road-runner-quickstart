package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Launcher {
    private DcMotor Left, Right;
    private double radius = 0.05;
    public Launcher(HardwareMap hardwareMap){
        this.Left = hardwareMap.get(DcMotor.class, "leftFront");
        this.Right = hardwareMap.get(DcMotor.class, "rightFront");

        this.Left.setDirection(DcMotorSimple.Direction.REVERSE);
        this.Right.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public double getHorizontalPower(double distance,double height){
        distance = distance * 2.5;
        double g = 9.81;
        double velocity = (distance * Math.sqrt(g))/(Math.sqrt(2 * height));
        double omega = velocity / radius;
        double RPM = (omega * 60)/(2*Math.PI);
        return RPM / 1620;
    }

    public double getProjectilePowerLand(double distance,double angle){
        distance = distance * 2.5;
        double g = 9.81;
        double velocity = Math.sqrt((distance * g)/Math.sin(Math.toRadians(2*angle)));
        double omega =  velocity / radius;
        double RPM = (omega * 60)/(2*Math.PI);
        return RPM / 1620;
    }

    public Action Rot(double power){
        return new Action() {
            private final ElapsedTime timer = new ElapsedTime();

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (timer.seconds() < 40.0) {
                    Left.setPower(power);
                    Right.setPower(power);
                    return true;
                } else {
                    Left.setPower(0);
                    Right.setPower(0);
                    return false;
                }
            }
        };
    }
    public Action ProjectileLandLaunch(double distance, double angle){
        double power = getProjectilePowerLand(distance,angle);
        return new Action() {
            private final ElapsedTime timer = new ElapsedTime();

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (timer.seconds() < 2.0) {
                    Left.setPower(power);
                    Right.setPower(power);
                    return true;
                } else {
                    Left.setPower(0);
                    Right.setPower(0);
                    return false;
                }
            }
        };
    }
    public Action HorizontalLaunch(double distance, double height){
        double power = getHorizontalPower(distance,height);
        return new Action() {
            private final ElapsedTime timer = new ElapsedTime();

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (timer.seconds() < 2.0) {
                    Left.setPower(power);
                    Right.setPower(power);
                    return true;
                } else {
                    Left.setPower(0);
                    Right.setPower(0);
                    return false;
                }
            }
        };
    }






}
