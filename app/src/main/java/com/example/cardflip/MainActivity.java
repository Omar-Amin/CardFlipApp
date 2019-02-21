package com.example.cardflip;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11;
    ImageView[] visualCards = {card1, card2, card3, card4, card5, card6, card7, card8, card9, card10, card11};
    ArrayList<Integer> cards;
    ArrayList<String> rFlip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Deck deck = new Deck();
        RandomFlip randomFlip = new RandomFlip(11);

        rFlip = randomFlip.randomize();

        cards = deck.makeDeck();

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

        for (int i = 0; i < visualCards.length; i++) {

            if (rFlip.get(i+1).equals("1")) {
                visualCards[i].setImageResource(cards.get(i));

                visualCards[i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        changeCard(view);
                    }
                });
            }
        }

        //TODO: Implement the game. Make sure it is only clickable if it is flipped

    }

    private void changeCard(View view){
        ImageView imageView = (ImageView) view;


        for (int i = 0; i < visualCards.length; i++) {
            if(visualCards[i].getId() == view.getId()){
                rFlip.set(i+1,"R"); //update it as removed
                if(rFlip.get(i).equals("0") && i != 0){ //add eventlistener and flip the card, update rFlip
                    visualCards[i-1].setImageResource(cards.get(i-1));
                    visualCards[i-1].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            changeCard(view);
                        }
                    });
                    rFlip.set(i,"1");
                }else if(rFlip.get(i).equals("1") && i != 0){ //does the same but checks it neighbours
                    visualCards[i-1].setImageResource(R.drawable.yellow_back);
                    visualCards[i-1].setOnClickListener(null);
                    rFlip.set(i,"0");
                }
                if (rFlip.get(i+2).equals("0") && i != visualCards.length-1){
                    visualCards[i+1].setImageResource(cards.get(i+1));
                    visualCards[i+1].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            changeCard(view);
                        }
                    });
                    rFlip.set(i+2,"1");
                } else if (rFlip.get(i+2).equals("1") && i != visualCards.length-1){
                    visualCards[i+1].setImageResource(R.drawable.yellow_back);
                    visualCards[i+1].setOnClickListener(null);
                    rFlip.set(i+2,"0");
                }
            }
        }

        imageView.setImageBitmap(null);
        imageView.setOnClickListener(null);
    }
}
