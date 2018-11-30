package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import java.util.List;

public class CameraObj {

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private static final String VUFORIA_KEY = "AZearTz/////AAABmV+6nAi3UknqssuG06JBHbR0y1tv7nS/t+2EaAsXRc0lOxY2MwNjhIi7bU3ZaINGYX/4DXOedVOgtSKGbMbbT+0IeXgDmvurDe8+ZofmhZtoAzBQ8HD58PmSWsjG4xzeV9oq6sTHxhGS6oxj0pAawz2p+yvpeLJv78EJICeB4lHuz7Utn8GWLlXQYOC1P8Qr/bIMkhlTrdHJ40rAZhGZcyIt9q933VzHC5gO8h/9tlsnZaZSC44TrgUUntIFxN3c55L7cFKrjXRCoLqkCAsiaZQANOPuxuyW7D2ImfyfkhVHpq9/cNJt6lksKiuBd4NH1JTn1HhSM+zIg8NKvXQEkX91ImNV+Ma30jt3G7P1vWGf";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    public CameraObj(){
        initVuforia();
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
            telemetry.addData("Status", "Success! for Camera");
        }
        else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
            System.exit(255);
        }
        telemetry.update();
        //Initiate tfod if it isn't activated
        if (tfod != null) {
            tfod.activate();
        }
    }

    //-1 means left, 0 means center, 1 means right, and INT_Min means sees nothing
    public int getPos()
    {
        // getUpdatedRecognitions() will return null if no new information is available since
        // the last time that call was made.
        List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
        telemetry.addData("# Object Detected", updatedRecognitions.size());
        if (updatedRecognitions.size() == 3) {
            int goldMineralX = -1;
            int silverMineral1X = -1;
            int silverMineral2X = -1;
            for (Recognition recognition : updatedRecognitions) {
                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                    goldMineralX = (int) recognition.getTop();
                }
                else if (silverMineral1X == -1) {
                    silverMineral1X = (int) recognition.getTop();
                }
                else {
                    silverMineral2X = (int) recognition.getTop();
                }
            }
            if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                    telemetry.addData("Gold Mineral Position", "Left");
                    telemetry.addData("GoldMin:", goldMineralX);
                    telemetry.addData("silverMineral1:", silverMineral1X);
                    telemetry.addData("silverMineral2:", silverMineral2X);
                    return -1; //-1 is left
                }
                else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                    telemetry.addData("Gold Mineral Position", "Right");
                    telemetry.addData("GoldMin:", goldMineralX);
                    telemetry.addData("silverMineral1:", silverMineral1X);
                    telemetry.addData("silverMineral2:", silverMineral2X);
                    return 1;
                }
                else {
                    telemetry.addData("Gold Mineral Position", "Center");
                    telemetry.addData("GoldMin:", goldMineralX);
                    telemetry.addData("silverMineral1:", silverMineral1X);
                    telemetry.addData("silverMineral2:", silverMineral2X);
                    return 0;
                }
            }
        }
        return Integer.MIN_VALUE; //doesn't see anything
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
