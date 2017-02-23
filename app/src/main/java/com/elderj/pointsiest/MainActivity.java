package com.elderj.pointsiest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tvPlayerScore, tvGameScore;
    String json;
    JSONObject jsonObj;
    JSONArray jsonArray;
    InputStream inputStream;
    ArrayList<JSONObject> sPeople;
    Button resetScores;
    ListView listView;

//    public static final String PointsiestPREFERENCES = "PointsiestPrefs";
    public int pScore;
    public int gScore;
    ArrayList<Sportsperson> sportspeople;
    public static final String pScoreKey = "playerScoreKey";
    public static final String gScoreKey = "gameScoreKey";
    SharedPreferences sharedPrefs;
    String strGameResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPlayerScore = (TextView)findViewById(R.id.player_score);
        tvGameScore = (TextView)findViewById(R.id.game_score);
        resetScores = (Button)findViewById(R.id.reset_scores);

        sharedPrefs = getPreferences(Context.MODE_PRIVATE);

        readScores();
        renderScores();

        json = loadJsonFromFile();
        sPeople = makeArrayListJsonObjects();

        listView = (ListView)findViewById(R.id.sperson_listview);


        int noOfsPeople = sPeople.size();
        int rand1 = getRandomNumber(noOfsPeople);
        int rand2 = rand1;
        while (rand2 == rand1) {
            rand2 = getRandomNumber(noOfsPeople);
        }
        Sportsperson sperson1 = createsPersonObject(rand1);
        Sportsperson sperson2 = createsPersonObject(rand2);

        sportspeople = new ArrayList<>();
        sportspeople.add(sperson1);
        sportspeople.add(sperson2);

        SportspeopleAdapter adapter = new SportspeopleAdapter(this, sportspeople);
        listView.setAdapter(adapter);


        OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                View dialogView = getLayoutInflater().inflate( R.layout.activity_dialog, null );

                final AlertDialog gameResultDialog = new AlertDialog.Builder(MainActivity.this).create();
                gameResultDialog.setView(dialogView);

                TextView tvGameResult = (TextView) dialogView.findViewById(R.id.game_result);

                Object idObject = view.getTag();
                int selectedId = (Integer) idObject;

                boolean result = GameLogic.checkPointsiest(sportspeople, selectedId);

                if (result == true) {
                    strGameResult = "Well done! You guessed the pointsiest sportsperson. You get a point.";
                } else {
                    strGameResult = "No, you guessed wrong. The game gets a point.";
                }

                tvGameResult.setText(strGameResult);
                gameResultDialog.show();

                Button buttonCancel = (Button) dialogView.findViewById(R.id.cancel);
                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gameResultDialog.cancel();
                    }

                });

                incrementScores(result);

            }

        };

        listView.setOnItemClickListener(onListClick);


        resetScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pScore = 0;
                gScore = 0;
                saveScores();
                readScores();
                renderScores();
            }
        });

    }

    public void readScores() {
        pScore = sharedPrefs.getInt(pScoreKey, 0);
        gScore = sharedPrefs.getInt(gScoreKey, 0);
    }

    public void renderScores() {
        TextView playerScoreView = tvPlayerScore;
        playerScoreView.setText(Integer.toString(pScore));

        TextView gameScoreView = tvGameScore;
        gameScoreView.setText(Integer.toString(gScore));
    }

    public void incrementScores(boolean playerWin) {
        readScores();
        if (playerWin == true) {
            pScore += 1;
        } else {
            gScore += 1;
        }
        saveScores();
        readScores();
        renderScores();
    }

    public void saveScores() {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(pScoreKey, pScore);
        editor.putInt(gScoreKey, gScore);
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

    public Sportsperson createsPersonObject(int index) {
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



}
