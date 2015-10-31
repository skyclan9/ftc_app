
package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.Range;
//import com.qualcomm.robotcore.hardware.ColorSensor;


public class driveTest extends OpMode{

//<define any variables needed>

//<define motors and servos>
//

    DcMotorController.DeviceMode devMode;
    DcMotorController ctrlTank; //ctrlAux;
    //ServoController ctrlBox;
    DcMotor right, left, combine;
    Servo leftGate, rightGate, boxCombine;

    //ColorSensor colorSensor;

    //public enum ColorSensorDevice {MODERN_ROBOTICS_I2C};

    //public ColorSensorDevice device = ColorSensorDevice.MODERN_ROBOTICS_I2C;

    float lStick = 0, rStick = 0;
    boolean climb = false, revClimb = false;
//DcMotor <name>;
//Test 10101
//Servo <name>;

     public driveTest() {

    }

    @Override
    public void init() {

        //maps the motors, defining what they're named on the robot controller
        right = hardwareMap.dcMotor.get("right_motor");
        left = hardwareMap.dcMotor.get("left_motor");
        //combine = hardwareMap.dcMotor.get("combine");

        //leftGate = hardwareMap.servo.get("leftGate");
        //rightGate = hardwareMap.servo.get("rightGate");
        //boxCombine = hardwareMap.servo.get("boxCombine");


        //maps the color sensor similar to the motors
        //colorSensor = hardwareMap.colorSensor.get("colorSensor");

        //maps the motor controllers
        ctrlTank = hardwareMap.dcMotorController.get("motors");
        //ctrlLeft = hardwareMap.dcMotorController.get("left_controller");
        //ctrlAux = hardwareMap.dcMotorController.get("combine_controller");

        //ctrlBox = hardwareMap.servoController.get("boxStuff");

        right.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        left.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);



        //??????
        devMode = DcMotorController.DeviceMode.WRITE_ONLY;

        //reverses the direction the left motors go by default so they go the same direction as the right
        left.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override

    public void loop() {

        //declares the variables and assigned them the value of the left and right joystick
        lStick = Range.clip(gamepad1.left_stick_y, -1,1);
        rStick = Range.clip(gamepad1.right_stick_y,-1,1);

        //makes the dead zones for the joysticks and allows the robot to run depending on joystick inputs
        if (lStick > .1 || lStick < -.1) {
            left.setPower(lStick);

        }

        //sets power of the motors to 0 if they're not being used
        else {
            left.setPower(0);

        }
        if (rStick > .1 || rStick < -.1) {
            right.setPower(rStick);
        }
        else {
            right.setPower(0);
        }



        telemetry.addData("Text", "***Robot Data***");
        telemetry.addData("Left Motor Power","Left Power:"+left);
        telemetry.addData("Right Motor Power","Right Power:"+right);

    }

    @Override

    public void stop(){

    }

//this is used for making it so when the joysticks are at a low value it scales the

}