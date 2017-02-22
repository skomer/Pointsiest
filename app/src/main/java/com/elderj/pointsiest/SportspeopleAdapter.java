package com.elderj.pointsiest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;



public class SportspeopleAdapter extends ArrayAdapter<Sportsperson> {

    public SportspeopleAdapter(Context context, ArrayList<Sportsperson> sportspeople) {
        super(context, 0, sportspeople);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Sportsperson sportsperson = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_single_sportsperson, parent, false);
        }
        ImageView personImage = (ImageView) convertView.findViewById(R.id.personImage);
        String picUrl = sportsperson.getProfilePicUrl();
        Ion.with(personImage)
//                .placeholder(R.drawable.placeholder_image)
//                .error(R.drawable.error_image)
//                .animateLoad(spinAnimation)
//                .animateIn(fadeInAnimation)
                .load(picUrl);

        TextView personName = (TextView) convertView.findViewById(R.id.personName);
        personName.setText(sportsperson.getFullName());

        return convertView;
    }


}
