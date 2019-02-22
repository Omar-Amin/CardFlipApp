package com.example.cardflip;

import java.util.ArrayList;
import java.util.Collections;

class Deck {


    Deck(){
    }

    ArrayList<Integer> makeDeck(){
        ArrayList<Integer> deck = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            deck.add(R.drawable.orangec);
            deck.add(R.drawable.bluec);
            deck.add(R.drawable.purplec);
            deck.add(R.drawable.yellowc);
            deck.add(R.drawable.greenc);
        }

        Collections.shuffle(deck);

        return deck;
    }

}
