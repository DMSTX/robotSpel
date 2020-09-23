package com.company;

public class Main {

    public static void main(String[] args) {
        GameEngine ge = new GameEngine(); // skapar ett objekt av GameEngine-typ
        System.out.println(ge.menu()); // skriver ut menyn

        ge.setNoOfZebras(ge.readNoOfZebras()); // Sätter antalet Zebror till antal som användaren skriver in
        ge.setNoOfCheetahs(ge.getNoOfZebras()); // Sätter antalet Cheetahs utifrån det antal zebror som skrivs in

        System.out.println(ge.getNoOfZebras()); // testutskrift för att se att antal zebror lästs in
        System.out.println(ge.getNoOfCheetahs()); // testutskrift för att se att antal cheetahs har satts

        ge.fillZebraList(ge.getNoOfZebras()); // fyller en lista med det antal zeebra-objekt användaren angivit
        ge.fillCheetahList(ge.getNoOfCheetahs()); // fyller en lista med det randomiserade antalet cheetah-objekt

        for (int i = 0; i < ge.getNoOfZebras(); i++) { //testutskrift för att skriva ut listan med zebrornas x-position och y-position
            System.out.print("Zebra Plats: ");
            System.out.print(ge.zebraList.get(i).getPositionX()+":");
            System.out.print(ge.zebraList.get(i).getPositionY());
            System.out.println();
        }

        for (int i = 0; i < ge.getNoOfCheetahs(); i++) { //testutskrift för att skriva ut listan med gepardernas x-position och y-position
            System.out.print("Gepard Plats: ");
            System.out.print(ge.cheetahList.get(i).getPositionX()+":");
            System.out.print(ge.cheetahList.get(i).getPositionY());
            System.out.println();
        }


    }
}