package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class intake {
    private DcMotor Intake;
    private ElapsedTime Timer;
    private int n = 0;
    public void init(HardwareMap hardwareMap){
        Intake = hardwareMap.get(DcMotor.class,"intake");
        Timer = new ElapsedTime();


    }
    public Action Take(double Sec){

        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (n == 0){
                    Timer.reset();
                    Timer.startTime();
                }
                Intake.setPower(1);
                if (Timer.seconds() > Sec){
                    Intake.setPower(0);
                    n = 0;
                    return false;
                }
                n += 1;
                return true;
            }
        };
    }

    public Action Start(){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                Intake.setPower(1);
                return true;
            }
        };
    }
    public Action Stop(){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                Intake.setPower(0);
                return true;
            }
        };
    }


}
