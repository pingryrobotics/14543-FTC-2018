/*
This is the start of the Camera Stuff.
This also isn't the main priority as Hanging & Arm are more important
Also in this case, the camera is used to position ourselves after we get onto the field from hangning
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.ArrayList;
import java.util.List;

/*private static final float mmPerInch        = 25.4f;
private static final float mmFTCFieldWidth  = (12*6) * mmPerInch;       // the width of the FTC field (from the center point to the outer panels)
private static final float mmTargetHeight   = (6) * mmPerInch;          // the height of the center of the target image above the floor
*/
//DON'T TOUCH. Make sure it is 380 characters
@TeleOp()
public class Camera extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final String VUFORIA_KEY = "AZearTz/////AAABmV+6nAi3UknqssuG06JBHbR0y1tv7nS/t+2EaAsXRc0lOxY2MwNjhIi7bU3ZaINGYX/4DXOedVOgtSKGbMbbT+0IeXgDmvurDe8+ZofmhZtoAzBQ8HD58PmSWsjG4xzeV9oq6sTHxhGS6oxj0pAawz2p+yvpeLJv78EJICeB4lHuz7Utn8GWLlXQYOC1P8Qr/bIMkhlTrdHJ40rAZhGZcyIt9q933VzHC5gO8h/9tlsnZaZSC44TrgUUntIFxN3c55L7cFKrjXRCoLqkCAsiaZQANOPuxuyW7D2ImfyfkhVHpq9/cNJt6lksKiuBd4NH1JTn1HhSM+zIg8NKvXQEkX91ImNV+Ma30jt3G7P1vWGf";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
            telemetry.addData("Status", "Success!");
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
            System.exit(255);
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }

            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        if (updatedRecognitions.size() == 3) {
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            int silverMineral2X = -1;
                            for (Recognition recognition : updatedRecognitions) {
                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();
                                } else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();
                                } else {
                                    silverMineral2X = (int) recognition.getLeft();
                                }
                            }
                            if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                                if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Left");
                                    telemetry.addData("GoldMin:", goldMineralX);
                                    telemetry.addData("silverMineral1:", silverMineral1X);
                                    telemetry.addData("silverMineral2:", silverMineral2X);
                                } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Right");
                                    telemetry.addData("GoldMin:", goldMineralX);
                                    telemetry.addData("silverMineral1:", silverMineral1X);
                                    telemetry.addData("silverMineral2:", silverMineral2X);
                                } else {
                                    telemetry.addData("Gold Mineral Position", "Center");
                                    telemetry.addData("GoldMin:", goldMineralX);
                                    telemetry.addData("silverMineral1:", silverMineral1X);
                                    telemetry.addData("silverMineral2:", silverMineral2X);
                                }
                            }
                        }
                        telemetry.update();
                    }
                }
            }
        }

        if (tfod != null) {
            tfod.shutdown();
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}