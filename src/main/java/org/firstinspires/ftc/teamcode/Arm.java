package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class Arm {
    private DcMotor shoulder = null;
    private CRServo collection1 = null;
    private CRServo collection2 = null;
    private Servo elbow1;
    private Servo elbow2;
    public Arm(HardwareMap h){
        shoulder = h.get(DcMotor.class,"shoulder");
        collection1 = h.get(CRServo.class, "collection1");
        collection2 = h.get(CRServo.class, "collection2");
        collection2.setDirection(DcMotorSimple.Direction.REVERSE);
        elbow1 = h.get(Servo.class, "elbow1");
        elbow2 = h.get(Servo.class, "elbow2");
        elbow2.setDirection(Servo.Direction.REVERSE);
    }
    public void setShoulder(double targetPos){
        shoulder.setPower(targetPos);
    }
    public void setElbow(double targetPos) {
        targetPos += 1;
        targetPos /= 2;
        elbow1.setPosition(targetPos);
        elbow2.setPosition(targetPos);
    }
    public void intake(){
        collection1.setPower(-1);
        collection2.setPower(-1);
    }
    public void outtake(){
        collection1.setPower(1);
        collection2.setPower(1);
    }
    public void stopCollection(){
        collection1.setPower(0);
        collection2.setPower(0);
    }
}
