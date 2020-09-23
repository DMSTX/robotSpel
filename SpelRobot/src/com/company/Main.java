package com.company;

public class Main {

    public static void main(String[] args) {
        GameEngine ge = new GameEngine(); // skapar ett objekt av GameEngine-typ
        System.out.println(ge.menu()); // skriver ut menyn
        ge.setNoOfZebras(ge.readNoOfZebras()); // Sätter antalet Zebror till antal som användaren skriver in
        //Här behövs ett anrop till GameEngines setter för att ge den antalet zebror

        //System.out.println(ge.getNoOfZebras()); --- testutskrift för att se att antal zebror lästs in

        ge.fillZebraList(ge.getNoOfZebras()); // fyller en lista med det antal zebror användaren angivit

        /*for (int i = 0; i < ge.getNoOfZebras(); i++) { //testutskrift för att skriva ut listan med zebrornas x-position och y-position
            System.out.println(ge.zebraList.get(i).getPositionX());
            System.out.println(ge.zebraList.get(i).getPositionY());
        }*/
    }
}