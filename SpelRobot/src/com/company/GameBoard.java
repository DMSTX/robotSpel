package com.company;

public class GameBoard {

    //Klassmedlemmar
    public char zebra = 'z';
    public char cheetah = 'c';
    public char[][] gameboard = new char[10][10];

    //noOfZebras?

    //Default konstruktor
    public GameBoard() {
        startGameboard();
    }

    //Metod som fyller spelplan med punkter
    public void startGameboard() {
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard.length; j++) {
                gameboard[j][j] = '.';
            }
            gameboard[i][i] = '.';
        }
    }

    //Metod som skriver ut spelplan
    public void printBoard(char[][] gameboard) {
        startGameboard();
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard.length; j++) {
                System.out.print(gameboard[j][j]);
            }
            System.out.println(gameboard[i][i]);
        }
    }

    public void printNewBoard(char[][] gameboard) {
        this.gameboard = gameboard;
        for (int i = 0; i < this.gameboard.length; i++) {
            for (int j = 0; j < this.gameboard.length; j++) {
                System.out.print(this.gameboard[j][j]);
            }
            System.out.println(this.gameboard[i][i]);
        }
    }

    //Metod för att placera Zebror
    /*public char placeGamePieceZ(int x, int y) { //Placera gamepieces Z
        //clearPosition(x,y);
        GamePiece test = new GamePiece(); //association till gamepiece för att hämta positioner x och y
        setGameboard()
                [test.getPositionX()][test.getPositionY()] = zebra;

        return this.gameboard [x][y];
        //System.out.println(gameboard[x][y]); //testa utskrift för position x och y
        //printNewBoard(gameboard);
    }*/

    public void setGameboard (char [][] gameboard){
        Zebra z = new Zebra();
        System.out.println(z.getPositionX());
        //gameboard [x][y] = zebra;
        //this.gameboard = gameboard;
    }

    //public void clearPosition (int x, int y){
        //this.gameboard [x] [y] = ' ';
    }






