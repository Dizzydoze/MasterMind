import java.util.*;

/**
 * Mastermind game with code all in main and no non-constant data members defined
 */
public class MastermindMain {
    public static final String colors="RGYBOP";

    public static void main(String args[]){

        // guess history
        StringBuilder guessHistory = new StringBuilder();

        // initial chances
        int chances = 5;

        // generateSecret
        Random random = new Random();
        String secret = "";
        for (int i = 0; i < 4; i++) {
            int ranInt = random.nextInt(6); // allow duplicates
            secret += colors.charAt(ranInt);
        }
        System.out.println("[Secret] " + secret);

        while (true){
            // check remaining chances
            if (chances == 0) {
                System.out.println("[SECRET]" + secret + "[GAME OVER] YOU LOSE!");
                return;
            }

            // reset the count each round
            int exact = 0;
            int partial = 0;

            // getGuess
            Scanner scanner = new Scanner(System.in);
            System.out.println("Guesses consist of 4 letters from:" + colors);
            System.out.println("Please enter a 4 letter guess:");
            String guess = scanner.nextLine();

            // check length validation
            if (guess.length() != 4) {
                System.out.println("[REENTER]Exact 4 Letters Each Guess!");
                continue;
            }

            // check color validation
            boolean colorCheck = true;
            for (int i = 0; i < guess.length(); i++) {
                if (colors.indexOf(guess.charAt(i)) == -1){
                    colorCheck = false;
                    System.out.println("[REENTER]Only Colors within " + colors + " Allowed!");
                    break;
                    }
                }
            if (!colorCheck){continue;}

            // check letter validation
            boolean letterCheck = true;
            for (int i = 0; i < guess.length(); i++) {
                if (!Character.isLetter(guess.charAt(i))) {
                    letterCheck = false;
                    System.out.println("[REENTER]Only English Letters Allowed!");
                }
            }
            if(!letterCheck){continue;}

            // It is a legal guess, chance - 1
            chances -= 1;

            // check exact
            String tmpSecret = secret;
            String tmpGuess = guess;
            for (int i = 0; i < tmpGuess.length(); i++) {
                if (tmpGuess.charAt(i) == tmpSecret.charAt(i)){
                    exact += 1;
                    // mark exact match as * in both secret and guess to skip
                    tmpSecret = tmpSecret.substring(0, i) + '*' + tmpSecret.substring(i+1);
                    tmpGuess = tmpGuess.substring(0, i) + '*' + tmpGuess.substring(i+1);
                }
            }

            // check partial
            for (int i = 0; i < tmpGuess.length(); i++) {
                if(tmpGuess.charAt(i)!= '*' && tmpSecret.indexOf(tmpGuess.charAt(i))!=-1){
                    // mark partial match as * to skip
                    int partialSecretIndex = tmpSecret.indexOf(tmpGuess.charAt(i));
                    tmpSecret = tmpSecret.substring(0, partialSecretIndex) + '*' + tmpSecret.substring(partialSecretIndex+1);
                    tmpGuess = tmpGuess.substring(0, i) + '*' + tmpGuess.substring(i+1);
                    partial += 1;
                }
            }

            // check game result
            if (exact == 4){
                System.out.println("[SUCCESS] YOU WIN!");
                return;
            }
            String currentResult = "[GUESS]" + guess + "[EXACT]" + exact + "[PARTIAL]"
                    + partial + "[CHANCE REMAIN]" + chances + "\n";
            guessHistory.append(currentResult);
            System.out.println(guessHistory);
        }
    }
}
