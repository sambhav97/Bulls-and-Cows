package bullscows;
import java.util.*;

public class Main {

    public static int graderBulls(String num, String secretCode) {
        int bullsCount = 0;
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == secretCode.charAt(i)) {
                bullsCount++;
            }
        }
        return bullsCount;
    }

    public static int graderCows(String num, String secretCode) {
        int cowsCount = 0;
        for (int i = 0; i < num.length(); i++) {
            for (int j = 0; j < secretCode.length(); j++) {
                if (i == j) {
                    continue;
                } else if (num.charAt(i) == secretCode.charAt(j)) {
                    cowsCount++;
                }
            }
        }
        return cowsCount;
    }

    public static String randomGenerator(int length, int symbols) {
        String str = "0123456789abcdefghijklmnopqrstuvwxyz";
        String newStr = str.substring(0, symbols - 1);
        char[] chars = newStr.toCharArray();
        List<Character> randomList = new ArrayList<Character>();
        for (char ch: chars) {
            randomList.add(ch);
        }
        Collections.shuffle(randomList);
        StringBuilder result = new StringBuilder();
        for (var ch : randomList.subList(0, length)) {
            result.append(ch);
        }
        return result.toString();
    }

    public static void printMessageSecretCode(int length, int symbols) {
        String str = "0123456789abcdefghijklmnopqrstuvwxyz";
        char a1 = str.charAt(0);
        char a2;
        if (symbols > 10) {
            a2 = str.charAt(9);
        } else {
            a2 = str.charAt(symbols - 1);
        }
        char a3 = str.charAt(10);
        char a4 = str.charAt(symbols - 1);

        char[] starArray = new char[length];
        for (int i = 0; i < starArray.length; i++) {
            starArray[i] = '*';
        }
        String star = new String(starArray);

        if (symbols < 11) {
            System.out.printf("The secret is prepared: %s (%c-%c).", star, a1, a2);
            System.out.println("\nOkay, let's start a game!");
        } else {
            System.out.printf("The secret is prepared: %s (%c-%c, %c-%c).", star, a1, a2, a3, a4);
            System.out.println("\nOkay, let's start a game!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        String length1 = scanner.nextLine();
        int length = 0;
        int symbols = 0;
        boolean run = true;
        try {
            length = Integer.parseInt(length1);
        } catch (NumberFormatException e) {
            System.out.println("Error: " + length1 + " isn't a valid number.");
            run = false;
        }
        if (length == 0) {
            System.out.println("Error: length can't be equal to 0");
            run = false;
        } else {
            System.out.println("Input the number of possible symbols in the code:");
            symbols = scanner.nextInt();

            if (symbols < length) {
                System.out.println("Error: it's not possible to generate a code with a length of 6 with 5 unique symbols.");
                run = false;
            } else if (symbols > 36) {
                System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
                run = false;
            }
        }
        if (run) {
            printMessageSecretCode(length, symbols);
            String secretCode = randomGenerator(length, symbols);
            int bulls = 0;
            int cows = 0;
            int turnNumber = 1;
            while (bulls != length) {
                System.out.printf("Turn %d:\n", turnNumber);
                String num = scanner.nextLine();
                bulls = graderBulls(num, secretCode);
                cows = graderCows(num, secretCode);
                if (bulls != length) {
                    System.out.printf("Grade: %d bull and %d cow\n", bulls, cows);
                } else if (bulls == length) {
                    System.out.println("Grade: " + length + " bulls");
                    System.out.println("Congratulations! You guessed the secret code.");
                }
                turnNumber++;
            }
        }
    }
}