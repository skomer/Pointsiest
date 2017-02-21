package com.elderj.pointsiest;

import org.junit.Test;
import static org.junit.Assert.*;

public class SportspersonTest {

    Sportsperson moFarah = new Sportsperson(
        "Mo",
        "Farah",
        "Mo Farah",
        99,
        999.999,
        "mo farah profile pic url"
    );

    @Test
    public void hasFullName() {
        assertEquals("Mo Farah", moFarah.getFullName());
    }

    @Test
    public void hasId() {
        assertEquals(99, moFarah.getId());
    }

    @Test
    public void hasPoints() {
        assertEquals(999.999, moFarah.getPoints(), 0.0001);
    }


}





