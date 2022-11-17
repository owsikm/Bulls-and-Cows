package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        SecretNumber();
    }

    public static void SecretNumber() {
        System.out.println("Please, enter the secret code's length: ");
        Scanner scan = new Scanner(System.in);
        int numberOfSecretNumberDigits = 0;
        String StrNumberDigits = "";
        String StrNumberSymbols = "";
        try {
            StrNumberDigits = scan.nextLine();
            numberOfSecretNumberDigits = Integer.parseInt(StrNumberDigits);
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"" + StrNumberDigits + "\" isn't a valid number.");
            System.exit(0);
        }

        System.out.println("Input the number of possible symbols in the code: ");
        int numberOfSymbols = 0;
        try {
            StrNumberSymbols = scan.nextLine();
            numberOfSymbols = Integer.parseInt(StrNumberSymbols);
        } catch (NumberFormatException e) {
            System.out.printf("Error: \"" + StrNumberSymbols + "\" isn't a valid number.");
            System.exit(0);
        }

        if (numberOfSymbols < numberOfSecretNumberDigits||numberOfSecretNumberDigits==0) {
            System.out.println("Error: it's not possible to generate a code with a length of " + numberOfSecretNumberDigits + " with " + numberOfSymbols + " unique symbols.");
            System.exit(0);
        }
        if (numberOfSymbols > 36) {
            System.out.print("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }

        String masterString = "0123456789abcdefghijklmnopqrstuvwxyz";

        ArrayList<String> mylist = new ArrayList<>();

        for (int i = 0; i < numberOfSymbols; i++) {
            mylist.add(String.valueOf(masterString.charAt(i)));
        }
        Collections.shuffle(mylist);
        String secretCode = String.join("", mylist);
        String secretNumber = secretCode.substring(0, numberOfSecretNumberDigits);

        String str = "*";
        String repeated = str.repeat(numberOfSecretNumberDigits);
        String details = "";
        if (numberOfSymbols <= 9) {
            details = "0-" + numberOfSymbols;
        } else if (numberOfSymbols == 10) {
            details = "0-9, a";
        } else if (numberOfSymbols > 10) {
            details = "0-9, a-" + masterString.charAt(numberOfSymbols - 1);
        }

        System.out.println("The secret is prepared: " + repeated + " (" + details + ").");
        System.out.print("Okay, let's start a game!");

        Game(secretNumber, numberOfSecretNumberDigits);
    }


    public static void Game(String secretNumber, int numberOfSecretNumberdigits) {
        char[] secret = secretNumber.toCharArray();
        Scanner scan = new Scanner(System.in);

        int turn = 0;
        int bulls;
        int cows;
        do {
            turn++;
            System.out.printf("\nTurn %d:\n", turn);
            String input = scan.nextLine();
            char[] digits = input.toCharArray();
            cows = 0;
            bulls = 0;
            for (int i = 0; i < digits.length; i++) {
                if (digits[i] == secret[i]) {
                    bulls++;
                } else {
                    for (int j = 0; j < digits.length; j++) {
                        if (digits[i] == secret[j] && digits[j] != secret[j]) {
                            cows++;
                        }
                    }
                }
            }
            System.out.print("Grade: ");
            if (bulls > 1 && cows > 1) {
                System.out.printf("%d bull(s) and %d cow(s). ", bulls, cows);
            } else if (bulls > 1 && cows == 0) {
                System.out.printf("%d bulls. ", bulls);
            } else if (bulls == 0 && cows > 1) {
                System.out.printf("%d cows. ", cows);
            } else if (bulls == 1 && cows == 0) {
                System.out.printf("%d bull. ", bulls);
            } else if (bulls == 0 && cows == 1) {
                System.out.printf("%d cow. ", cows);
            } else if (bulls == 1 && cows == 1) {
                System.out.printf("%d bull and %d cow. ", bulls, cows);
            } else if (bulls > 1 && cows == 1) {
                System.out.printf("%d bulls and %d cow. ", bulls, cows);
            } else if (bulls == 1 && cows > 1) {
                System.out.printf("%d bull and %d cows. ", bulls, cows);
            } else if (bulls == 0 && cows == 0) {
                System.out.print("None. ");
            }
            if (bulls == numberOfSecretNumberdigits) {
                System.out.println("\nCongratulations! You guessed the secret code.");
            }
        } while ((bulls != numberOfSecretNumberdigits));

    }
}
