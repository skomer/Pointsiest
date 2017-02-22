package com.elderj.pointsiest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class GameLogicTest {

    Sportsperson moFarah = new Sportsperson();
    Sportsperson peytonManning = new Sportsperson();
    ArrayList<Sportsperson> sportspeople = new ArrayList<>();

    @Before
    public void before() {
        moFarah = new Sportsperson(
                "Mo",
                "Farah",
                "Mo Farah",
                99,
                999.999,
                "mo farah profile pic url"
        );

        peytonManning = new Sportsperson(
                "Peyton",
                "Manning",
                "Peyton Manning",
                3,
                311.113,
                "peyton manning profile pic url"
        );

        sportspeople.add(moFarah);
        sportspeople.add(peytonManning);
    }

    @Test
    public void gameCorrectlyJudgesPointsiest() {
        int selectedId = moFarah.getId();
        assertTrue(GameLogic.checkPointsiest(sportspeople, selectedId));
    }

    @Test
    public void gameCorrectlyJudgesUnpointsiest() {
        int selectedId = peytonManning.getId();
        assertFalse(GameLogic.checkPointsiest(sportspeople, selectedId));
    }


}
