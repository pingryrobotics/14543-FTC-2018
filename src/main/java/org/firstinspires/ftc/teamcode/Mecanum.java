/**
 * Class to drive with 4 mecanum wheels
 */
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
public class Mecanum {
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    public Mecanum(HardwareMap h){
        backRight = h.get(DcMotor.class, "backRight");
        frontLeft = h.get(DcMotor.class, "frontLeft");
        frontRight = h.get(DcMotor.class, "frontRight");
        backLeft = h.get(DcMotor.class, "backLeft");
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
    }
    public void move(Gamepad gamepad){
        double theta = Math.atan2(gamepad.left_stick_x, gamepad.left_stick_y)+(Math.PI/4);
        double r = Range.clip(Math.hypot(gamepad.left_stick_x,gamepad.left_stick_y),-1,1);
        double turn = -gamepad.right_stick_x;
        double fr = Math.cos(theta) * r + turn;
        double fl = Math.sin(theta) * r - turn;
        double br = Math.sin(theta) * r + turn;
        double bl = Math.cos(theta) * r - turn;
        backLeft.setPower(bl);
        backRight.setPower(br);
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
    }
    public void stop(){
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
    }
}
