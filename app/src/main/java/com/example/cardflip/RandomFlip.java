package com.example.cardflip;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class RandomFlip {

    private int numCards;

    RandomFlip(int numCards){
        this.numCards = numCards;
    }

    ArrayList<String> randomize(){
        ArrayList<String> listOfCards = new ArrayList<>();
        Random randomBit = new Random();

        for (int i = 0; i < numCards; i++) {
            listOfCards.add(randomBit.nextInt(2)+"");
        }

        int count = 0;
        for (String elm: listOfCards) {
            if (elm.equals("1")){
                count++;
            }
        }

        if(count%2 == 0){   //number of 1's can't be equal
            if (count < numCards/2) {
                for (int i = 0; i < numCards; i++) {
                    if (listOfCards.get(i).equals("1")) {
                        listOfCards.set(i, "0");
                        break;
                    }
                }
                Collections.shuffle(listOfCards);
            }else {
                for (int i = 0; i < numCards; i++) {
                    if (listOfCards.get(i).equals("0")) {
                        listOfCards.set(i, "1");
                        break;
                    }
                }
                Collections.shuffle(listOfCards);
            }
        }

        listOfCards.add(0,"");
        listOfCards.add("");

        return listOfCards;
    }

}
