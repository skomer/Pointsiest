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

    public SportspeopleAdapter(Context context, ArrayList<Sportsperson> sportspeople) {
        super(context, 0, sportspeople);
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
        double points = sportsperson.getPoints();
        personImage.setTag(points);


        TextView personName = (TextView) convertView.findViewById(R.id.person_name);
        personName.setText(sportsperson.getFullName());

        personImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("sportspeople adapter", v.getTag().toString());
            }
        });



        return convertView;
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_sportsperson, container, false);
//        ImageView imageView = (ImageView) view.findViewById(R.id.person_image);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("sportspeople adapter", v.getTag().toString());
//            }
//        });
//        return view;


}
