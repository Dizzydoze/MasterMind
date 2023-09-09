import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * version 4: provide the same methods as in version 3,
 * but define the secret code (named "secret") as a data member.
 * You should not send "secret" as a parameter since it is a data member.
 */
public class MastermindSecret {

    public String guess;
    public String secret = "";
    public StringBuilder secretSB;
    public StringBuilder guessSB;
    public static final String colors="RGYBOP";

    public static void main(String args[]){

        MastermindSecret masterMind = new MastermindSecret();

        int chances = 10;

        // generate secret string
        masterMind.generateSecret();
        StringBuilder resultRecord = new StringBuilder();
        while (true){
            if (chances == 0){
                System.out.println("[GAME OVER] YOU LOSE! [SECRET] " + masterMind.secret);
                return;
            }
            // get guess string
            masterMind.getGuess();
            if (masterMind.guess.isEmpty()){
                continue;
            }
            // main process logic
            chances -= 1;
            int exact = masterMind.computeExacts();
            int partial = masterMind.computePartials();

            if (exact == masterMind.secret.length()){
                System.out.println("[SUCCESS] YOU WIN! [SECRET] " + masterMind.secret);
                return;
            }
            String roundResult = "[GUESS]" + masterMind.guess + "[PARTIAL]" + partial
                    + "[EXACT]" + exact + "[CHANCES REMAIN]" + chances + "\n";
            resultRecord.append(roundResult);
            System.out.println(resultRecord);
        }
    }

    public void generateSecret() {
        // generate secret color combination
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int ranInt = random.nextInt(6); // allow duplicates
            this.secret += colors.charAt(ranInt);
        }
        // secretSB is the same as secret at the beginning
        this.secretSB = new StringBuilder(this.secret);
        System.out.println("[Secret] " + this.secret);
    }

    public void getGuess() {
        // get guess string
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guesses consist of 4 letters from:" + colors);
        System.out.println("Please enter a 4 letter guess:");
        String guess = scanner.nextLine();

        // check length validation
        if (guess.length() != 4) {
            System.out.println("[REENTER]Exact 4 Letters Each Guess!");
            this.guess = "";
        }
        // check color validation
        for (int i = 0; i < guess.length(); i++) {
            if (colors.indexOf(guess.charAt(i)) == -1){
                System.out.println("[REENTER]Only Colors within " + colors + " Allowed!");
                this.guess = "";
            }
        }
        // check letter validation
        for (int i = 0; i < guess.length(); i++) {
            if (!Character.isLetter(guess.charAt(i))) {
                System.out.println("[REENTER]Only English Letters Allowed!");
                this.guess = "";
            }
        }
        // assign valid guess string
        this.guess = guess;
        // guessSB is the same as guess at the beginning
        this.guessSB = new StringBuilder(this.guess);
    }

    public int computeExacts() {
        // reset secretSB each round
        this.secretSB = new StringBuilder(this.secret);
        int exact = 0;
        for (int i = 0; i < this.guess.length(); i++) {
            if (this.guess.charAt(i) == this.secret.charAt(i)){
                exact += 1;
                // mark exact match as * in both secretSB and guessSB to skip, always use SB, as we keep changing the same SB
                this.secretSB = new StringBuilder(this.secretSB.substring(0, i) + '*' + this.secretSB.substring(i + 1));
                this.guessSB = new StringBuilder(this.guessSB.substring(0, i) + '*' + this.guessSB.substring(i+1));
            }
        }
        return exact;
    }

    public int computePartials() {

        int partial = 0;
        for (int i = 0; i < this.guess.length(); i++) {
            // always use guessSB, secretSB for checking, as we keep changing both SB to *
            if(this.guessSB.charAt(i)!= '*' && this.secretSB.indexOf(String.valueOf(this.guessSB.charAt(i)))!=-1){
                // mark partial match in both stringBuilder as * to skip duplication
                int partialSecretIndex = this.secretSB.indexOf(String.valueOf(this.guessSB.charAt(i)));
                this.secretSB = new StringBuilder(this.secretSB.substring(0, partialSecretIndex) + '*'
                        + this.secretSB.substring(partialSecretIndex+1));
                this.guessSB = new StringBuilder(this.guessSB.substring(0, i) + '*' + this.guessSB.substring(i+1));
                partial += 1;
            }
        }
        return partial;
    }
}
