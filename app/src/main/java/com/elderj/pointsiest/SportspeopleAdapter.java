package com.elderj.pointsiest;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class SportspeopleAdapter extends ArrayAdapter<Sportsperson> {


    public SportspeopleAdapter(Context context, ArrayList<Sportsperson> sportspeople) {
        super(context, 0, sportspeople);

    }

    


}
