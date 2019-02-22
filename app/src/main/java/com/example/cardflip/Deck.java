package com.example.cardflip;

import android.graphics.Color;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;

class Deck {

    Deck(){
    }

    ArrayList<ColorData> makeDeck(){
        ArrayList<ColorData> deck = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            deck.add(new ColorData(R.drawable.orangec, Color.parseColor("#EF7C2E")));
            deck.add(new ColorData(R.drawable.bluec,Color.parseColor("#62A8DD")));
            deck.add(new ColorData(R.drawable.purplec,Color.parseColor("#A0599F")));
            deck.add(new ColorData(R.drawable.yellowc,Color.parseColor("#EEE75C")));
            deck.add(new ColorData(R.drawable.greenc,Color.parseColor("#71B62E")));
        }

        Collections.shuffle(deck);

        return deck;
    }


}
