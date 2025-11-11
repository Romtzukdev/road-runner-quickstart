import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class Take extends LinearOpMode {
    private DcMotor Left, Right;
    private double Power;
    @Override
    public void runOpMode() throws InterruptedException {
        Left = hardwareMap.get(DcMotor.class, "leftFront");
        Right = hardwareMap.get(DcMotor.class, "rightFront");

        Left.setDirection(DcMotorSimple.Direction.REVERSE);
        Right.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()){
            Power = -gamepad1.left_trigger + gamepad1.right_trigger/10;
            Left.setPower(Power);
            Right.setPower(Power);
        }
    }


}
