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
    private Servo elbow2; //right
    private Servo wrist;
    public Arm(HardwareMap h){
        shoulder = h.get(DcMotor.class,"shoulder");
        collection1 = h.get(CRServo.class, "collection1");
        collection2 = h.get(CRServo.class, "collection2");
        collection2.setDirection(DcMotorSimple.Direction.REVERSE);
        elbow1 = h.get(Servo.class, "elbow1");
        elbow2 = h.get(Servo.class, "elbow2");
        elbow2.setDirection(Servo.Direction.REVERSE); //right; 0 is now the 180
        elbow1.scaleRange(0.0, 0.66); //Set position is still 0-1, but the 1 = 180 not 1 = 270
        elbow2.scaleRange(0.0, 0.66);
        wrist = h.get(Servo.class, "wirst");
        elbow1.setPosition(0.5);
        elbow2.setPosition(0.5);
    }
    public void setShoulder(double targetPos){
        shoulder.setPower(targetPos);
    }
    public void setElbow(double diffPos) { //Precondition: diffPos is a decimal to increment/decrement
        double current = elbow1.getPosition();
        elbow1.setPosition(current + diffPos);
        elbow2.setPosition(current + diffPos);
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
