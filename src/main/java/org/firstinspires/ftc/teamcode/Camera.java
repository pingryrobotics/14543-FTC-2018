/*
This is the start of the Camera Stuff.
This also isn't the main priority as Hanging & Arm are more important
Also in this case, the camera is used to position ourselves after we get onto the field from hangning
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.FRONT;

import java.util.ArrayList;
import java.util.List;

//DON'T TOUCH. Make sure it is 380 characters
private static final String VUFORIA_KEY = "AZearTz/////AAABmV+6nAi3UknqssuG06JBHbR0y1tv7nS/t+2EaAsXRc0lOxY2MwNjhIi7bU3ZaINGYX/4DXOedVOgtSKGbMbbT+0IeXgDmvurDe8+ZofmhZtoAzBQ8HD58PmSWsjG4xzeV9oq6sTHxhGS6oxj0pAawz2p+yvpeLJv78EJICeB4lHuz7Utn8GWLlXQYOC1P8Qr/bIMkhlTrdHJ40rAZhGZcyIt9q933VzHC5gO8h/9tlsnZaZSC44TrgUUntIFxN3c55L7cFKrjXRCoLqkCAsiaZQANOPuxuyW7D2ImfyfkhVHpq9/cNJt6lksKiuBd4NH1JTn1HhSM+zIg8NKvXQEkX91ImNV+Ma30jt3G7P1vWGf";

private static final float mmPerInch        = 25.4f;
private static final float mmFTCFieldWidth  = (12*6) * mmPerInch;       // the width of the FTC field (from the center point to the outer panels)
private static final float mmTargetHeight   = (6) * mmPerInch;          // the height of the center of the target image above the floor

public class Camera {





}