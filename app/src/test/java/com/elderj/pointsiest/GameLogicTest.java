package com.elderj.pointsiest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class GameLogicTest {

    public static ArrayList<Sportsperson> sportspeople;

    @Before
    public void before() {
        Sportsperson moFarah = new Sportsperson(
                "Mo",
                "Farah",
                "Mo Farah",
                99,
                999.999,
                "mo farah profile pic url"
        );

        Sportsperson peytonManning = new Sportsperson(
                "Peyton",
                "Manning",
                "Peyton Manning",
                3,
                311.113,
                "peyton manning profile pic url"
        );

        ArrayList<Sportsperson> sportspeople = new ArrayList<>();
        sportspeople.add(moFarah);
        sportspeople.add(peytonManning);
    }

    @Test
    public void gameCorrectlyJudgesPointsiestSportsperson() {
//        Object

        assertTrue(GameLogic.checkWinner(sportspeople, "99"));


    }


}
