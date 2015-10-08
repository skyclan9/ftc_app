package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by michaelgoldschlager on 10/8/15.
 */
public class Joystick_output_opmode extends OpMode {

    public Joystick_output_opmode (){

    }

    @Override
    public void init(){

    }

    @Override
    public void loop(){
        String direction = "";
        if (-gamepad1.left_stick_y>=0){
            direction="Up";
        }
        else if (-gamepad1.left_stick_y<=0){
            direction="Down";
        }
        else if (gamepad1.left_stick_x>=0){
            direction="Right";
        }
        else if (gamepad1.left_stick_x<=0){
            direction="Left";
        }

        telemetry.addData("Text","***Robot Data***");
        telemetry.addData("Direction","Direction: " + direction);

    }
    @Override
    public void stop(){

    }
}
