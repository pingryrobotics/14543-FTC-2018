package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
public class ServoPosition extends OpMode {
    private double pos;
    private Servo servo;
    private boolean buttonPressed;
    private boolean bp;
    public void init() {
        pos = .5;
        buttonPressed = false;
        servo = hardwareMap.get(Servo.class, "servo");
    }
    public void loop(){
        servo.setPosition(pos);
        if(!buttonPressed){
            if (gamepad1.a){
                incrementPos(.1);
                buttonPressed = true;
            }
            if (gamepad1.x) {
                incrementPos(.01);
                buttonPressed = true;
            }
            if(gamepad1.y){
                incrementPos(-.01);
                buttonPressed = true;
            }
            if(gamepad1.b){
                incrementPos(-.1);
                buttonPressed = true;
            }
        }
        else if(!(gamepad1.b||gamepad1.a||gamepad1.x||gamepad1.y)){
            buttonPressed = false;
        }
    }
    private void incrementPos(double inc){
        pos+=inc;
    }
}
