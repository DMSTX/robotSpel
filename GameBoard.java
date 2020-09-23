package com.company;

public class GameBoard {

    //Klassmedlemmar

    private char cheetah = 'c';
    private char[][] gameboard;
    private  int width, heigth;

    //noOfZebras?

    //Default konstruktor
    public GameBoard() {
        startGameboard();
    }
    public GameBoard(int width, int height){
        this.width = width;
        this.heigth = height;
        this.gameboard = new char[this.heigth][this.width];
    }

    //Metod som fyller spelplan med punkter
    public void startGameboard() {
        for (int i = 0; i < this.heigth; i++) {
            for (int j = 0; j < this.width; j++) {
                this.gameboard[i][j] = '.';
            }
        }
    }

    //Metod som skriver ut spelplan
    public void printBoard() {

        //startGameboard();
        for (int i = 0; i < this.heigth; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.gameboard[i][j]);
            }
            System.out.println("");
        }
    }



    //Metod för att placera Zebror
    public void placeGamePieceZ(int x, int y) { //Placera gamepieces Z
        //association till gamepiece för att hämta positioner x och y
        this.gameboard[x][y] = 'z';

        //System.out.println(gameboard[x][y]); //testa utskrift för position x och y
    }

}