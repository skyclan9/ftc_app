package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
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


    public chasee1_teleop (){

    }

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
        float right = -gamepad1.right_stick_y;
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
            if (climb_pow == 0) {
                climb_pow = 100;
                climber.setPower(climb_pow);
            }
            else {
                climb_pow = 0;
                climber.setPower(climb_pow);
            }
        }

        telemetry.addData("Text", "***Robot Data***");
        telemetry.addData("Left Motor Power","Left Power:"+left);
        telemetry.addData("Right Motor Power","Right Power:"+right);
        telemetry.addData("Climber", "Climber:"+climb_pow);

    }
    @Override
    public void stop(){

    }
}
