package com.example.cardflip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private ImageView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11;
    private ImageView[] visualCards = {card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11};
    private ArrayList<ColorData> cards;
    private ArrayList<String> rFlip;
    private TextView scoreBoard;
    private TextView gameStatusTV;
    private TextView bonusScore;
    private TextView bonusTxt;
    private TextView highscore;
    private TextView highscoreTxt;

    private Button tryAgain;
    private boolean resetBonus = true;
    private SharedPreferences prefs;
    private TimeCounter timeCounter;

    private LinkedList<Integer> bonusStatus = new LinkedList<>();

    private Integer bonus = 1;
    private int scoreAdd = 0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //setting up the highscore and deck
        Deck deck = new Deck();
        cards = deck.makeDeck();
        highscore = findViewById(R.id.highscoreVal);
        prefs = this.getSharedPreferences("highscore",Context.MODE_PRIVATE);
        int score = prefs.getInt("highscoreValue",0);
        highscore.setText(score + "");

        gameStatusTV = findViewById(R.id.gameStatus);
        tryAgain = findViewById(R.id.finishButton);
        scoreBoard = findViewById(R.id.score);
        bonusScore = findViewById(R.id.bonusTxt);
        bonusTxt = findViewById(R.id.score2);
        highscoreTxt = findViewById(R.id.highscoreTxt);

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);
        card7 = findViewById(R.id.card7);
        card8 = findViewById(R.id.card8);
        card9 = findViewById(R.id.card9);
        card10 = findViewById(R.id.card10);
        card11 = findViewById(R.id.card11);

        visualCards[0] = card1;
        visualCards[1] = card2;
        visualCards[2] = card3;
        visualCards[3] = card4;
        visualCards[4] = card5;
        visualCards[5] = card6;
        visualCards[6] = card7;
        visualCards[7] = card8;
        visualCards[8] = card9;
        visualCards[9] = card10;
        visualCards[10] = card11;

        tryAgain.setText("Start");

        tryAgain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                timeCounter =  new TimeCounter(30*1000,1000);
                timeCounter.start();
                makeGame();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void makeGame(){
        Collections.shuffle(cards);
        tryAgain.setText("Stop");
        tryAgain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                for (ImageView visualCard : visualCards) {
                    visualCard.setImageResource(R.drawable.greyc2);
                    visualCard.setOnClickListener(null);
                }
                resetCards();
                timeCounter.cancel();
            }
        });

        RandomFlip randomFlip = new RandomFlip(11);

        rFlip = randomFlip.randomize();

        //set cards as flipped/not flipped
        for (int i = 0; i < visualCards.length; i++) {
            if (rFlip.get(i+1).equals("1")) {
                visualCards[i].setImageResource(cards.get(i).getDeckVal());

                visualCards[i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        changeCard(view);
                    }
                });
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void changeCard(View view){
        ImageView imageView = (ImageView) view;
        for (int i = 0; i < visualCards.length; i++) {
            //searching for the item
            if(visualCards[i].getId() == view.getId()){
                int cardColor = cards.get(i).getColors();
                bonusStatus.add(cardColor);
                checkBonus();
                //setting the scoreboard
                scoreAdd += 2*bonus;
                scoreBoard.setTextColor(cardColor);
                scoreBoard.setText(scoreAdd+"");
                //change color of highscore
                highscore.setTextColor(cardColor);
                highscoreTxt.setTextColor(cardColor);
                //change color of "try again" "you won" etc.
                gameStatusTV.setTextColor(cardColor);
                tryAgain.setTextColor(cardColor);

                rFlip.set(i+1,"R"); //update it as removed
                //add/remove eventlistener and flip the left card, update rFlip
                if(rFlip.get(i).equals("0") && i != 0){
                    visualCards[i-1].setImageResource(cards.get(i-1).getDeckVal());
                    visualCards[i-1].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            changeCard(view);
                        }
                    });
                    rFlip.set(i,"1");
                }else if(rFlip.get(i).equals("1") && i != 0){ //does the same but checks it neighbours
                    visualCards[i-1].setImageResource(R.drawable.greyc2);
                    visualCards[i-1].setOnClickListener(null);
                    rFlip.set(i,"0");
                }

                //add/remove eventlistener and flip the right card, update rFlip
                if (rFlip.get(i+2).equals("0") && i != visualCards.length-1){
                    visualCards[i+1].setImageResource(cards.get(i+1).getDeckVal());
                    visualCards[i+1].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            changeCard(view);
                        }
                    });
                    rFlip.set(i+2,"1");
                } else if (rFlip.get(i+2).equals("1") && i != visualCards.length-1){
                    visualCards[i+1].setImageResource(R.drawable.greyc2);
                    visualCards[i+1].setOnClickListener(null);
                    rFlip.set(i+2,"0");
                }
            }
        }
        imageView.setVisibility(View.INVISIBLE);
        imageView.setOnClickListener(null);
        checkGame();
    }

    @SuppressLint("SetTextI18n")
    private void checkBonus(){
        if(bonusStatus.size() > 1){
            Integer firstColor = bonusStatus.pollFirst();
            Integer secondColor = bonusStatus.getFirst();
            //doubles the bonus each time
            if(firstColor.equals(secondColor)){
                if(bonus < 9000000){
                    bonus *= 5;
                }
                bonusScore.setTextColor(firstColor); //changes colors
                bonusScore.setText(bonus + "x"); //to indicate which color to press on to get bonus
                bonusTxt.setTextColor(firstColor);
                resetBonus = true; //resetBonus to give the player a chance to keep his bonus
            }else if (bonus > 1 && resetBonus){
                bonusScore.setTextColor(secondColor);
                bonusTxt.setTextColor(secondColor);
                resetBonus = false; //setting to false, so next time the player chooses a color that is not similar
            }                       //the bonus score reset
            else{
                bonusScore.setTextColor(secondColor);
                bonusTxt.setTextColor(secondColor);
                bonusScore.setText("1x");
                bonus = 1;
                resetBonus = true;
            }
        }else{
            bonusScore.setTextColor(bonusStatus.getFirst());
            bonusTxt.setTextColor(bonusStatus.getFirst());
        }
    }

    @SuppressLint("SetTextI18n")
    void checkGame(){
        for (int i = 1; i < rFlip.size()-1; i++) {
            if (rFlip.get(i).equals("1")){
                return;
            }
        }

        for (int i = 1; i < rFlip.size()-1; i++) {
            if (rFlip.get(i).equals("0")){
                timeCounter.cancel();
                resetCards();
                gameStatusTV.setText("You lost...");
                return;
            }
        }
        //Adding more time when clearing the game
        String tmp = gameStatusTV.getText().toString();
        timeCounter.cancel();
        timeCounter = new TimeCounter((5+Integer.parseInt(tmp))*1000 ,1000);
        timeCounter.start();

        resetGame();
    }

    @SuppressLint("SetTextI18n")
    private void setHighscore(){
        prefs = this.getSharedPreferences("highscore",Context.MODE_PRIVATE);
        int score = prefs.getInt("highscoreValue",0);

        System.out.println(score);
        if(score < scoreAdd ){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highscoreValue",scoreAdd);
            editor.apply();
            highscore.setText(scoreAdd + "");
        }
    }

    @SuppressLint("SetTextI18n")
    private void resetGame(){
        for (ImageView visualCard : visualCards) {
            visualCard.setVisibility(View.VISIBLE);
            visualCard.setImageResource(R.drawable.greyc2);
        }

        makeGame();
    }

    @SuppressLint("SetTextI18n")
    private void resetCards(){
        setHighscore();
        bonus = 1;
        bonusScore.setText("1x");
        bonusStatus.clear();
        resetBonus = true;
        gameStatusTV.setText("30");
        scoreAdd = 0;

        int color = Color.parseColor("#5F5F5D");
        bonusTxt.setTextColor(color);
        scoreBoard.setTextColor(color);
        bonusScore.setTextColor(color);
        highscoreTxt.setTextColor(color);
        highscore.setTextColor(color);
        tryAgain.setTextColor(color);
        gameStatusTV.setTextColor(color);

        tryAgain.setText("Start");
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreBoard.setText("0");
                timeCounter =  new TimeCounter(30*1000,1000);
                resetGame();
                timeCounter.start();
            }
        });
    }

    private class TimeCounter extends CountDownTimer { //class to set timer

        private TimeCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onTick(long l) {
            gameStatusTV.setText(l/1000 + "");
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onFinish() {
            //when the game is finished, and you haven't lost, set remaining cards to null and reset
            gameStatusTV.setText("Times over!");
            for (ImageView visualCard : visualCards) {
                visualCard.setOnClickListener(null);
            }

            resetCards();
        }
    }

}
