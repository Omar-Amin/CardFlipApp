package com.example.cardflip;

import java.util.ArrayList;
import java.util.Collections;

class Deck {


    Deck(){
    }

    ArrayList<Integer> makeDeck(){
        ArrayList<Integer> deck = new ArrayList<>();

        deck.add(R.drawable.ac);
        deck.add(R.drawable.ad);
        deck.add(R.drawable.ah);
        deck.add(R.drawable.as);
        deck.add(R.drawable.eightc);
        deck.add(R.drawable.eightd);
        deck.add(R.drawable.eighth);
        deck.add(R.drawable.eights);
        deck.add(R.drawable.fivec);
        deck.add(R.drawable.fived);
        deck.add(R.drawable.fiveh);
        deck.add(R.drawable.fives);
        deck.add(R.drawable.fourc);
        deck.add(R.drawable.fourd);
        deck.add(R.drawable.fourh);
        deck.add(R.drawable.fours);
        deck.add(R.drawable.jc);
        deck.add(R.drawable.jd);
        deck.add(R.drawable.jh);
        deck.add(R.drawable.js);
        deck.add(R.drawable.kc);
        deck.add(R.drawable.kd);
        deck.add(R.drawable.kh);
        deck.add(R.drawable.ks);
        deck.add(R.drawable.ninec);
        deck.add(R.drawable.nined);
        deck.add(R.drawable.nineh);
        deck.add(R.drawable.nines);
        deck.add(R.drawable.qc);
        deck.add(R.drawable.qd);
        deck.add(R.drawable.qh);
        deck.add(R.drawable.qs);
        deck.add(R.drawable.sevend);
        deck.add(R.drawable.sevenh);
        deck.add(R.drawable.sevens);
        deck.add(R.drawable.sevenc);
        deck.add(R.drawable.sixc);
        deck.add(R.drawable.sixd);
        deck.add(R.drawable.sixh);
        deck.add(R.drawable.sixs);
        deck.add(R.drawable.tenc);
        deck.add(R.drawable.tend);
        deck.add(R.drawable.tenh);
        deck.add(R.drawable.tens);
        deck.add(R.drawable.threec);
        deck.add(R.drawable.threed);
        deck.add(R.drawable.threeh);
        deck.add(R.drawable.threes);
        deck.add(R.drawable.twoc);
        deck.add(R.drawable.twod);
        deck.add(R.drawable.twoh);
        deck.add(R.drawable.twos);


        Collections.shuffle(deck);

        return deck;
    }

}
