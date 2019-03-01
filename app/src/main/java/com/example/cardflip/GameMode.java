package com.example.cardflip;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class GameMode extends AppCompatActivity {

    private int gameModeChosen;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemode);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        TextView gamemode1 = findViewById(R.id.gamemode1);
        TextView gamemode2 = findViewById(R.id.gamemode2);

        gamemode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameMode.this,GameActivity.class);
                intent.putExtra("gameMode",1);
                startActivity(intent);

            }
        });

        gamemode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameMode.this,GameActivity.class);
                intent.putExtra("gameMode",2);
                startActivity(intent);

            }
        });
    }

    public int getGameModeChosen() {
        return this.gameModeChosen;
    }
}
