import java.util.Scanner;

public class GameEngine {

    // Klassmedlemmar
    private int noOfZebras;
    private int noOfCheetahs;

    public GameEngine(){ //defaultkonstruktor
        this.noOfZebras = 10;
        this.noOfCheetahs = 3;
    }

    public GameEngine(int noOfZebras) { // konstruktor med variabler
        this.noOfZebras = noOfZebras;
        this.noOfCheetahs = noOfZebras - 1; // här vill vi randomisera antalet geparder
    }

    // Medlemsmetoder

    public String menu(){ // Skriver ut menyn i början av spelet
        String s = "Välkommen till spelet!\n" +
                "Skriv in hur många zebror du vill ha.\n" +
                "För att avsluta tryck 0.\n";
        return s;
    }

    public int readNoOfZebras(){
        boolean x = false;
        int n = 0;
        do {
            try {
                Scanner scan = new Scanner(System.in);
                n = scan.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Du måste skriva in ett heltal");
                x = true;
            } catch (Exception e) {
                System.out.println("Oväntat fel");
                x = true;
            }
        } while (x); // det finns ett fel här nånstans

        return n;
    }

    // Getter och setter-metoder
    public int getNoOfZebras() {
        return noOfZebras;
    }

    public int getNoOfCheetahs() {
        return noOfCheetahs;
    }

    public void setNoOfCheetahs(int cheetahs) {
        this.noOfCheetahs = cheetahs;
    }

    public void setNoOfZebras(int zebras) {
        this.noOfZebras = zebras;
    }



}
