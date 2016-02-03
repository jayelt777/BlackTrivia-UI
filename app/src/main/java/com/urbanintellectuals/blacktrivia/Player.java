package com.urbanintellectuals.blacktrivia;

/* Created to house the Player class which holds all the variables that are specific to a player */

public class Player {

    //Instance Variables
    private int score = 0;
    private boolean isTurn = false;
    private boolean isWinner = false;
    private String name;

    //Guest Constructor (10 random digits)
    public Player() {
        score = 0;
        isTurn = false;
        isWinner = false;
        name = "Guest ";
        for (int i = 1; i <= 8; i++) {
            int randNum = (int) (Math.random() * 10);
            name = name + randNum;
        }
    }

    //Player Constructor (Set Name)
    public Player(String n) {
        score = 0;
        isTurn = false;
        isWinner = false;
        name = n;
    }

    //Accessor Methods
    public int getScore() {
        return score;
    }
    public boolean getIsTurn() {
        return isTurn;
    }
    public boolean getIsWinner() {
        return isWinner;
    }
    public String getName() {
        return name;
    }

    //Mutator Methods
    public void addScore(){
        // Used when a player gets a question correct
        this.score++;
    }
    public void changeTurn(){
        // Used after a player answers a question and the post question things happen
        if (isTurn)
            isTurn = false;
        else
            isTurn = true;
    }
    public void setWinner(){
        // Used once a player reaches 21
        if (score >= 10) isWinner = true;
    }

}
