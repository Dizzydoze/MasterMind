import java.util.*;

/**
 * version 2: The program has only constant data members and the following **static** methods:
 * - generateSecret-- returns a string
 * - getGuess -- returns a string
 * - computeExacts -- parameters secret and guess, returns the number of exact matches in the guess.
 * - computePartials-- parameters secret and guess, returns the number of partial matches.
 * Use these exact method names and define the parameters and return values as specified.
 */
public class MastermindStatic {
    public static final String colors="RGYBOP";

    public static void main(String args[]){

        int chances = 10;
        // generate secret string
        String secret = generateSecret();
        StringBuilder resultRecord = new StringBuilder();
        while (true){
            if (chances == 0){
                System.out.println("[GAME OVER] YOU LOSE! [SECRET] " + secret);
                return;
            }
            // get guess string
            String guess = getGuess();
            if (guess.isEmpty()){
                continue;
            }

            // main process logic
            chances -= 1;
            ArrayList<Object> exacts = computeExacts(guess, secret);
            int exact = (int) exacts.get(0);
            String tmpGuess = exacts.get(1).toString();
            String tmpSecret = exacts.get(2).toString();
            int partial = computePartials(tmpGuess, tmpSecret);

            if (exact == secret.length()){
                System.out.println("[SUCCESS] YOU WIN! [SECRET] " + secret);
                return;
            }
            String roundResult = "[GUESS]" + guess + "[PARTIAL]" + partial
                    + "[EXACT]" + exact + "[CHANCES REMAIN]" + chances + "\n";
            resultRecord.append(roundResult);
            System.out.println(resultRecord);
        }

    }

    public static String generateSecret() {
        // generate secret color combination
        Random random = new Random();
        String secret = "";
        for (int i = 0; i < 4; i++) {
            int ranInt = random.nextInt(6); // allow duplicates
            secret += colors.charAt(ranInt);
        }
        System.out.println("[Secret] " + secret);
        return secret;
    }

    public static String getGuess() {
        // get guess string
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guesses consist of 4 letters from:" + colors);
        System.out.println("Please enter a 4 letter guess:");
        String guess = scanner.nextLine();

        // check length validation
        if (guess.length() != 4) {
            System.out.println("[REENTER]Exact 4 Letters Each Guess!");
            return "";
        }

        // check color validation
        for (int i = 0; i < guess.length(); i++) {
            if (colors.indexOf(guess.charAt(i)) == -1){
                System.out.println("[REENTER]Only Colors within " + colors + " Allowed!");
                return "";
            }
        }

        // check letter validation
        for (int i = 0; i < guess.length(); i++) {
            if (!Character.isLetter(guess.charAt(i))) {
                System.out.println("[REENTER]Only English Letters Allowed!");
                return "";
            }
        }
        // return valid guess string
        return guess;
    }

    public static ArrayList<Object> computeExacts(String guess, String secret) {
        int exact = 0;
        ArrayList<Object> exacts = new ArrayList<>();
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)){
                exact += 1;
                // mark exact match as * in both secret and guess to skip
                secret = secret.substring(0, i) + '*' + secret.substring(i+1);
                guess = guess.substring(0, i) + '*' + guess.substring(i+1);
            }
        }
        exacts.add(exact);
        exacts.add(guess);
        exacts.add(secret);
        return exacts;
    }

    public static int computePartials(String guess, String secret) {
        int partial = 0;
        for (int i = 0; i < guess.length(); i++) {
            if(guess.charAt(i)!= '*' && secret.indexOf(guess.charAt(i))!=-1){
                // mark partial match as * to skip
                int partialSecretIndex = secret.indexOf(guess.charAt(i));
                secret = secret.substring(0, partialSecretIndex) + '*' + secret.substring(partialSecretIndex+1);
                guess = guess.substring(0, i) + '*' + guess.substring(i+1);
                partial += 1;
            }
        }
        return partial;
    }
}
