package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Hang {
    private DcMotor winch;
    public Hang(HardwareMap h){
        winch = h.get(DcMotor.class,"winch");
    }
    public void up(){
        winch.setPower(1);
    }
    public void down(){
        winch.setPower(-1);
    }
    public void stop(){
        winch.setPower(0);
    }
}
