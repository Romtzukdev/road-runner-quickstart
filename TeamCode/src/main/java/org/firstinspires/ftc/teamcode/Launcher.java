package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Launcher {
    private DcMotor RLauncher, LLauncher;
    private Servo PushServo;
    private ElapsedTime Timer;
    private int n = 0;

    public enum LaunchState{
        IDLE,
        LAUNCH,
        PUSH
    }
    public LaunchState launchState;

    public void init(HardwareMap hardwareMap){
        RLauncher = hardwareMap.get(DcMotor.class,"RLauncher");
        LLauncher = hardwareMap.get(DcMotor.class,"LLauncher");
        PushServo = hardwareMap.get(Servo.class,"PushServo");

        RLauncher.setDirection(DcMotorSimple.Direction.REVERSE);

        Timer = new ElapsedTime();

        PushServo.setPosition(1);

    }

    private void setState(LaunchState state){
        launchState = state;
        Timer.reset();
        Timer.startTime();
    }



    public Action LAUNCH(){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if ( n == 0){
                    setState(LaunchState.LAUNCH);
                }
                switch (launchState){
                    case IDLE:
                        RLauncher.setPower(0);
                        LLauncher.setPower(0);
                        PushServo.setPosition(1);
                        n = 0;
                        return false;
                    case LAUNCH:
                        n += 1;
                        RLauncher.setPower(1);
                        LLauncher.setPower(1);
                        if (Timer.seconds() > 2){
                            setState(LaunchState.PUSH);
                        }
                        return true;
                    case PUSH:
                        PushServo.setPosition(0.2);
                        if (Timer.seconds() > 4){
                            setState(LaunchState.IDLE);
                        }
                        return true;
                    default:
                        setState(LaunchState.IDLE);
                        return true;
                }
            }
        };
    }
}
