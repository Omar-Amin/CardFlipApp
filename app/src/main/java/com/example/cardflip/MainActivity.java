package com.example.cardflip;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11;
    ImageView[] visualCards = {card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11};
    ArrayList<ColorData> cards;
    ArrayList<String> rFlip;
    TextView scoreBoard;
    TextView gameStatusTV;
    String gameStatusStr;
    Button tryAgain;

    int scoreAdd = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        gameStatusTV = (TextView) findViewById(R.id.gameStatus);
        tryAgain = (Button) findViewById(R.id.finishButton);
        scoreBoard = (TextView) findViewById(R.id.score);

        card1 = (ImageView) findViewById(R.id.card1);
        card2 = (ImageView) findViewById(R.id.card2);
        card3 = (ImageView) findViewById(R.id.card3);
        card4 = (ImageView) findViewById(R.id.card4);
        card5 = (ImageView) findViewById(R.id.card5);
        card6 = (ImageView) findViewById(R.id.card6);
        card7 = (ImageView) findViewById(R.id.card7);
        card8 = (ImageView) findViewById(R.id.card8);
        card9 = (ImageView) findViewById(R.id.card9);
        card10 = (ImageView) findViewById(R.id.card10);
        card11 = (ImageView) findViewById(R.id.card11);

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

        makeGame();

    }


    private void makeGame(){
        tryAgain.setOnClickListener(null);
        tryAgain.setVisibility(View.INVISIBLE);
        gameStatusTV.setVisibility(View.INVISIBLE);

        Deck deck = new Deck();
        RandomFlip randomFlip = new RandomFlip(11);

        rFlip = randomFlip.randomize();

        cards = deck.makeDeck();


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

    private void changeCard(View view){
        ImageView imageView = (ImageView) view;
        scoreAdd += 5;

        for (int i = 0; i < visualCards.length; i++) {
            //searching for the item
            if(visualCards[i].getId() == view.getId()){
                scoreBoard.setTextColor(cards.get(i).getColors());
                scoreBoard.setText(scoreAdd+"");
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
        gameStatusStr = checkGame();

        imageView.setVisibility(View.INVISIBLE);
        imageView.setOnClickListener(null);
    }

    String checkGame(){
        for (int i = 1; i < rFlip.size()-1; i++) {
            if (rFlip.get(i).equals("1")){
                return "still";
            }
        }

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
                makeGame();
            }
        });

        for (int i = 1; i < rFlip.size()-1; i++) {
            if (rFlip.get(i).equals("0")){
                tryAgain.setTextColor(Color.RED);
                gameStatusTV.setText("You lost...");
                gameStatusTV.setTextColor(Color.RED);
                gameStatusTV.setVisibility(View.VISIBLE);
                tryAgain.setVisibility(View.VISIBLE);
                return "lost";
            }
        }
        tryAgain.setTextColor(Color.parseColor("#2d9604"));
        gameStatusTV.setText("You won!!!");
        gameStatusTV.setTextColor(Color.parseColor("#2d9604"));
        gameStatusTV.setVisibility(View.VISIBLE);
        tryAgain.setVisibility(View.VISIBLE);
        return "won";

    }

    private void resetGame(){

        for (ImageView visualCard : visualCards) {
            visualCard.setVisibility(View.VISIBLE);
            visualCard.setImageResource(R.drawable.greyc2);
        }
        scoreBoard.setText("0");
        scoreAdd = 0;

    }

}
