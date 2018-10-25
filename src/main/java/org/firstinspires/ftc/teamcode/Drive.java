package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp(name="Drive", group="Iterative Opmode")
public class Drive extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Mecanum m = null;
    private Hang h = null;
    private Arm a = null;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        //initialize all mechanisms
        m = new Mecanum(hardwareMap);
        h = new Hang(hardwareMap);
        a = new Arm(hardwareMap);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        move();
        hang();
        arm();
    }
    private void arm(){
        if(gamepad2.right_trigger>.2){
            a.intake();
        }
        else if(gamepad2.left_trigger>.2){
            a.outtake();
        }
        else {
            a.stopCollection();
        }
        a.setElbow(gamepad2.right_stick_y);
        a.setShoulder(gamepad2.left_stick_y);
        //ADD WRIST LEVELING
    }
    private void hang(){

        if (gamepad1.left_bumper){
           h.up();
        }
        else if(gamepad1.right_bumper){
            h.down();
        }
        else h.stop();
    }
    private void move(){
        m.move(gamepad1);
    }
    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        m.stop();
        a.stopCollection();
    }

}
