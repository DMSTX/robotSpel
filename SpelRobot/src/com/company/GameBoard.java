package com.company;

public class GameBoard {

    //Klassmedlemmar
    private char[][] gameboard;
    private int width, heigth;

    //Defaultkonstruktor
    public GameBoard() {
        startGameboard();
    }

    //Konstruktor med parametrar
    public GameBoard(int width, int height) {
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
        for (int i = 0; i < this.heigth; i++) {
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.gameboard[i][j]);
            }
            System.out.println("");
        }
    }

    //metod för att placera en gamepiece på brädet, för antingen cheetah eller zebra
    public void placeGamePiece(GamePiece g, int x, int y) {
        if (g instanceof Zebra){
            this.gameboard[x][y] = 'z';
        }
        else if (g instanceof Cheetah){
            if (((Cheetah) g).isHungry() == false){
                gameboard[x][y] = 'C';
            }
            else {
                gameboard[x][y] = 'c';
            }
        }
    }

    public void clearBoardPosition(int x, int y){
        this.gameboard[x][y] = '.';
    }
}