package com.elderj.pointsiest;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;



public class SportspeopleAdapter extends ArrayAdapter<Sportsperson> {

    ArrayList<Sportsperson> sportspeople;


    public SportspeopleAdapter(Context context, ArrayList<Sportsperson> sportspeople) {
        super(context, 0, sportspeople);
        this.sportspeople = sportspeople;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Sportsperson sportsperson = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_single_sportsperson, parent, false);
        }
        ImageView personImage = (ImageView) convertView.findViewById(R.id.person_image);
        String picUrl = sportsperson.getProfilePicUrl();
        Ion.with(personImage)
//                .placeholder(R.drawable.placeholder_image)
//                .error(R.drawable.error_image)
//                .animateLoad(spinAnimation)
//                .animateIn(fadeInAnimation)
                .load(picUrl);
        int id = sportsperson.getId();
        personImage.setTag(id);


        TextView personName = (TextView) convertView.findViewById(R.id.person_name);
        personName.setText(sportsperson.getFullName());

//        personImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Object idObject = v.getTag();
//                int selectedId = (Integer) idObject;
//                GameLogic.checkPointsiest(sportspeople, selectedId);
//            }
//        });





        return convertView;
    }

}
