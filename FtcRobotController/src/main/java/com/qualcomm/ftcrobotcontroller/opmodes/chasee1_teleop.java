package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by michaelgoldschlager on 10/13/15.
 */
public class chasee1_teleop extends OpMode{

    DcMotor leftback;
    DcMotor leftfront;
    DcMotor rightfront;
    DcMotor rightback;
    DcMotor climber;
    DcMotorController climber_controller;
    DcMotorController left_controller;
    DcMotorController right_controller;
    DcMotorController.DeviceMode devMode;
    //boolean climb = false;
    int climb_pow = 0;
    
    public chasee1_teleop (){}

    @Override
      public void init(){
        leftback = hardwareMap.dcMotor.get("leftback");
        leftfront = hardwareMap.dcMotor.get("leftfront");
        rightback = hardwareMap.dcMotor.get("rightback");
        rightfront = hardwareMap.dcMotor.get("rightfront");
        climber = hardwareMap.dcMotor.get("climber");
        climber_controller = hardwareMap.dcMotorController.get("climber_controller");
        left_controller = hardwareMap.dcMotorController.get("left_controller");
        right_controller = hardwareMap.dcMotorController.get("right_controller");
        devMode = DcMotorController.DeviceMode.WRITE_ONLY;

    }

    @Override
    public void loop(){
        float left = -gamepad1.left_stick_y;
        float right = gamepad1.right_stick_y;
        right = Range.clip(right,-1,1);
        left = Range.clip(left,-1,1);
        right = (float)scaleInput(right);
        left = (float)scaleInput(left);
        leftback.setPower(left);
        leftfront.setPower(left);
        rightback.setPower(right);
        rightfront.setPower(right);
       /* if (gamepad1.left_bumper == true && climb == false){
            climb = true;
            climber.setPower(100);
        }
        else if (gamepad1.left_bumper == true && climb == true){
            climb = false;
            climber.setPower(100);
        }
        */
        if (gamepad1.left_bumper) {
            climber.setPower(-.5);
        }
        if (gamepad1.right_bumper) {
            climber.setPower(0);
        }

        telemetry.addData("Text", "***Robot Data***");
        telemetry.addData("Left Motor Power","Left Power:"+left);
        telemetry.addData("Right Motor Power","Right Power:"+right);
        telemetry.addData("Climber", "Climber:"+climb_pow);

    }
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }
}
