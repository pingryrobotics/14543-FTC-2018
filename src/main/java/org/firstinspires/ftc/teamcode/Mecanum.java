/**
 * Class to drive with 4 mecanum wheels
 */
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotor;
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
    }
    public void move(Gamepad gamepad){
        double theta = Math.atan2(gamepad.left_stick_x, gamepad.left_stick_y)+(Math.PI/4);
        double r = Math.hypot(gamepad.left_stick_x,gamepad.left_stick_y);
        double turn = Math.hypot(gamepad.right_stick_x,gamepad.right_stick_y);
        final double fr = Range.clip(((Math.cos(theta) * r ) + turn),-1,1);
        final double fl = Range.clip(((Math.cos(theta) * -r ) + turn),-1,1);
        final double br = Range.clip(((Math.sin(theta) * -r ) + turn),-1,1);
        final double bl = Range.clip(((Math.sin(theta) * r ) + turn),-1,1);
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
