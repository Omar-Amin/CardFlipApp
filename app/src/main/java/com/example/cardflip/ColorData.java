package com.example.cardflip;

class ColorData{
    private Integer deckVal;
    private Integer colors;

    ColorData(Integer deckVal, Integer colors){
        this.deckVal = deckVal;
        this.colors = colors;
    }

    Integer getDeckVal(){
        return this.deckVal;
    }

    Integer getColors(){
        return this.colors;
    }

}