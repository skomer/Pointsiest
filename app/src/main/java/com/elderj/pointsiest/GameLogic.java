package com.elderj.pointsiest;


import java.util.ArrayList;


public class GameLogic {




    public static boolean checkPointsiest(ArrayList<Sportsperson> sportspeople, int selectedId) {

        double selectedPoints = 0.0;
        double notSelectedPoints = 0.0;

        for (int i = 0; i < sportspeople.size(); i++) {
            Sportsperson sPerson = sportspeople.get(i);
            int id = sPerson.getId();
            if (id == selectedId) {
                selectedPoints = sPerson.getPoints();
            } else {
                notSelectedPoints = sPerson.getPoints();
            }
        }

        if (selectedPoints > notSelectedPoints) {
            return true;
        }
        return false;

    }

}
