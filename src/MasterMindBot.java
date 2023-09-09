import java.util.Scanner;

/**
 * version 5: Modify version 4 so that the player is a bot, not the user.
 * How intelligent a Mastermind player can you build?
 */
public class MasterMindBot {

    public static final String colors="RGYBOP";

    public static void main(String args[]){

        // getGuess
        // you'll need to add code to validate that user entered four
        // valid colors, but leave that at end of project
        String guess="";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guesses consist of 4 letters from:" + colors);
        System.out.println("Please enter a 4 letter guess:");
        guess = scanner.nextLine();
        System.out.println("guess is:"+guess);  // remove this once working


    }
}
