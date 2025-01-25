package org.example;

import java.util.Random;
import java.util.Scanner;

public class GameLogic {
    private final Scanner scanner;

    public GameLogic() {
        this.scanner = new Scanner(System.in);
    }

    public void play(Player player) {
        Random random = new Random();
        int targetNumber = random.nextInt(101);
        int attempts = 0;

        System.out.println("Guess the number (0-100):");

        while (true) {
            System.out.print("Enter your guess: ");
            int guess = Integer.parseInt(scanner.nextLine());
            attempts++;

            if (guess == targetNumber) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                if (attempts < player.getBestScore()) {
                    player.setBestScore(attempts);
                    System.out.println("New best score: " + attempts);
                }
                break;
            } else if (guess < targetNumber) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }
        }
    }
}
