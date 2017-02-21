package com.elderj.pointsiest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements {

    TextView tvPlayerScore, tvGameScore;
    String json;
    JSONObject jsonObj;
    JSONArray jsonArray;
    InputStream inputStream;
    ArrayList<JSONObject> sPeople;
    Button resetScores;
    ImageView person1;
    ImageView person2;

//    public static final String PointsiestPREFERENCES = "PointsiestPrefs";
    public static final String pScore = "playerScoreKey";
    public static final String gScore = "gameScoreKey";
    SharedPreferences sharedPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPlayerScore = (TextView)findViewById(R.id.player_score);
        tvGameScore = (TextView)findViewById(R.id.game_score);
        resetScores = (Button)findViewById(R.id.reset_scores);

        sharedPrefs = getPreferences(Context.MODE_PRIVATE);

        readAndRenderScores();

        json = loadJsonFromFile();
        sPeople = makeArrayListJsonObjects();

        int noOfsPeople = sPeople.size();
        int rand1 = getRandomNumber(noOfsPeople);
        int rand2 = rand1;
        while (rand2 == rand1) {
            rand2 = getRandomNumber(noOfsPeople);
        }
        Sportsperson sportsperson1 = createSportspersonObject(rand1);
        Sportsperson sportsperson2 = createSportspersonObject(rand2);

        // set up sportspeople areas
        TextView person1Name = (TextView) findViewById(R.id.person_1_name);
        person1Name.setText(sportsperson1.getFullName());
        String pic1Url = sportsperson1.getProfilePicUrl();
        ImageView person1Image = (ImageView) findViewById(R.id.person_1_image);

        Ion.with(person1Image)
//                .placeholder(R.drawable.placeholder_image)
//                .error(R.drawable.error_image)
//                .animateLoad(spinAnimation)
//                .animateIn(fadeInAnimation)
                .load(pic1Url);

        TextView person2name = (TextView) findViewById(R.id.person_2_name);
        person2name.setText(sportsperson2.getFullName());
        String pic2Url = sportsperson2.getProfilePicUrl();
        ImageView person2Image = (ImageView) findViewById(R.id.person_2_image);

        Ion.with(person2Image)
//                .placeholder(R.drawable.placeholder_image)
//                .error(R.drawable.error_image)
//                .animateLoad(spinAnimation)
//                .animateIn(fadeInAnimation)
                .load(pic2Url);

        resetScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveScores(0, 0);
                readAndRenderScores();
            }
        });

    }


    public void readAndRenderScores() {
        int playerScore = sharedPrefs.getInt(pScore, 0);
        TextView playerScoreView = tvPlayerScore;
        playerScoreView.setText(Integer.toString(playerScore));

        int gameScore = sharedPrefs.getInt(gScore, 0);
        TextView gameScoreView = tvGameScore;
        gameScoreView.setText(Integer.toString(gameScore));
    }

    public void saveScores(int playerScore, int gameScore) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(pScore, playerScore);
        editor.putInt(gScore, gameScore);
        editor.apply();
        // editor.commit();
    }

    public String loadJsonFromFile() {
        try {
            inputStream = getAssets().open("sportspeople.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public ArrayList<JSONObject> makeArrayListJsonObjects() {
        try {
            jsonObj = new JSONObject(json);
            jsonArray = jsonObj.getJSONArray("sportspeople");
            ArrayList<JSONObject> peopleObjects = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject sPerson = jsonArray.getJSONObject(i);
                peopleObjects.add(sPerson);
            }
            return peopleObjects;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Sportsperson createSportspersonObject(int index) {
        JSONObject sPerson = sPeople.get(index);
        try {
            String firstName = sPerson.getString("first_name");
            String lastName = sPerson.getString("last_name");
            String fullName = sPerson.getString("full_name");
            int id = sPerson.getInt("id");
            double points = sPerson.getDouble("points");
            String profilePicUrl = sPerson.getString("profile_picture_url");

            return new Sportsperson(firstName, lastName, fullName, id, points, profilePicUrl);

        } catch (JSONException ignored) {
            ignored.printStackTrace();
            return null;
        }
    }

    public int getRandomNumber(int limit) {
        Random random = new Random();
        int rand = random.nextInt(limit);
        return rand;
    }

    public void onClick(View view) {
        Log.d("view", view.toString());
    }


}
