package com.company;

public class GameBoard {

        int [][] gameboard = {{'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.'}};



        public void printBoard (){
            for (int [] row : gameboard) {
                for (int  c : row) {
                    System.out.print((char) c + " ");
                }
                System.out.println();
            }
        }
    }

