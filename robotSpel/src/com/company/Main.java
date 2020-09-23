package com.company;

public class Main {

    public static void main(String[] args) {
        GameEngine ge = new GameEngine();
        System.out.println(ge.menu());
        ge.setNoOfZebras(ge.readNoOfZebras());

        //System.out.println(ge.getNoOfZebras()); --- testutskrift

        ge.fillZebraList(ge.getNoOfZebras()); // testar att fylla en lista med zebror

        for (int i = 0; i < ge.getNoOfZebras(); i++) { // skriver ut listan med zebror
            System.out.println(ge.zebraList);
        }

    }
}

