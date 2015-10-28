package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by michaelgoldschlager on 10/23/15.
 */
public class tread_teleop extends OpMode {
    DcMotor left_motor;
    DcMotor right_motor;
    DcMotorController motors;
    DcMotorController.DeviceMode devMode;

    public tread_teleop() {
    }

    @Override
    public void init() {
        left_motor = hardwareMap.dcMotor.get("left");
        right_motor = hardwareMap.dcMotor.get("right");
        motors = hardwareMap.dcMotorController.get("motors");
        devMode = DcMotorController.DeviceMode.WRITE_ONLY;

    }

    @Override
    public void loop() {
        float left_pow = -gamepad1.left_stick_y;
        float right_pow = gamepad1.right_stick_y;
        right_pow = Range.clip(right_pow, -1, 1);
        left_pow = Range.clip(left_pow, -1, 1);
        right_pow = (float) scaleInput(right_pow);
        left_pow = (float) scaleInput(left_pow);

        right_motor.setPower(right_pow);
        left_motor.setPower(left_pow);


        telemetry.addData("Text", "***Robot Data***");
        telemetry.addData("Left Motor Power", "Left Power:" + left_pow);
        telemetry.addData("Right Motor Power", "Right Power:" + right_pow);

    }

    double scaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};

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
