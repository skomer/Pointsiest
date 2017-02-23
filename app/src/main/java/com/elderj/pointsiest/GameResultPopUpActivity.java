package com.elderj.pointsiest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

//
public class GameResultPopUpActivity extends AppCompatActivity {

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

    public void doThis() {
        LayoutInflater layoutInflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View popUpView = layoutInflater.inflate(R.layout.activity_popup, null);
        final PopupWindow popupWindow = new PopupWindow(popUpView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        Button buttonCancel = (Button)popUpView.findViewById(R.id.cancel);
        buttonCancel.setOnClickListener(new OnClickListener() {

            //@Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }

        });

    }

}
