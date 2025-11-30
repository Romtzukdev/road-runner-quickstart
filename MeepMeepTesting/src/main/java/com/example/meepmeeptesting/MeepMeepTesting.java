package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    static int row = 1;

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-64, -64, Math.toRadians(180)))
                //Welp. Wanted To NOT Make It Spaghetti Code
                //First Row
                .strafeTo(new Vector2d(-12 + (row - 1) * 24, 0))
                        .turn(Math.toRadians(-90))
                .strafeTo(new Vector2d(-12 + (row - 1) * 24, -50))
                .waitSeconds(1)
                .strafeTo(new Vector2d(-12, 0))
                    .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(-34, -24))
                    .waitSeconds(1)
                .turn(Math.toRadians(135))

                //Second Row
                .strafeTo(new Vector2d(-12 + 1 * 24, 0))
                .turn(Math.toRadians(-90))
                .strafeTo(new Vector2d(-12 + 1 * 24, -50))
                .waitSeconds(1)
                .strafeTo(new Vector2d(-12, 0))
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(-34, -24))
                .waitSeconds(1)
                .turn(Math.toRadians(135))

                //Third Row
                .strafeTo(new Vector2d(-12 + 2 * 24, 0))
                .turn(Math.toRadians(-90))
                .strafeTo(new Vector2d(-12 + 2 * 24, -50))
                .waitSeconds(1)
                .strafeTo(new Vector2d(-12, 0))
                .turn(Math.toRadians(-45))
                .strafeTo(new Vector2d(-34, -24))
                .waitSeconds(1)
                .turn(Math.toRadians(135))

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_BLACK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}