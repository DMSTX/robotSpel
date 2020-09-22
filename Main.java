import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GameEngine ge = new GameEngine();
        System.out.println(ge.menu());
        ge.setNoOfZebras(ge.readNoOfZebras());

        System.out.println(ge.getNoOfZebras());

    }
}
