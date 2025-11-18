package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake {
    private DcMotor intake;
    public Intake(HardwareMap hardwareMap){
        this.intake = hardwareMap.get(DcMotor.class,"intake");
    }

    public Action Take(double power,double time_in_ms){
        return new Action() {
            ElapsedTime timer = new ElapsedTime();
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                while(timer.milliseconds() < time_in_ms){
                    intake.setPower(power);
                    return true;
                }
                return false;
            }
        };
    }
    public Action waitMs(double time_in_ms){
        return new Action() {
            ElapsedTime timer = new ElapsedTime();
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                while(timer.milliseconds() < time_in_ms){
                    return true;
                }
                return false;
            }
        };
    }
}
