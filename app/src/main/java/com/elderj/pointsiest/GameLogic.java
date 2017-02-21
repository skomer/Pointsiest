package com.elderj.pointsiest;

import android.widget.ImageView;


public class GameLogic {

    ImageView playerImage;

    public String checkWinner(Sportsperson sPersonSelected, Sportsperson sPersonNotSelected) {
        double selectedPoints = sPersonSelected.getPoints();
        double notSelectedPoints = sPersonNotSelected.getPoints();
        if (selectedPoints > notSelectedPoints) {
            return "player";
        } else if (notSelectedPoints > selectedPoints) {
            return "game";
        } else {
            return "error";
        }
    }





}
