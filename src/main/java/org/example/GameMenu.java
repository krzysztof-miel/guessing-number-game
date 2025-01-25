package org.example;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class GameMenu {
    private final PlayerManager playerManager;
    private final GameLogic gameLogic;
    private final Scanner scanner;

    public GameMenu() {
        this.playerManager = new PlayerManager();
        this.gameLogic = new GameLogic();
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Guessing Game Menu ===");
            System.out.println("1. Select/Create Player");
            System.out.println("2. Play Game");
            System.out.println("3. Reset Player Stats");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> selectOrCreatePlayer();
                case 2 -> playGame();
                case 3 -> resetPlayerStats();
                case 4 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void selectOrCreatePlayer() {
        System.out.print("Enter player name: ");
        String playerName = scanner.nextLine();
        playerManager.loadOrCreatePlayer(playerName);
    }

    private void playGame() {
        if (playerManager.getCurrentPlayer() == null) {
            System.out.println("No player selected. Please select or create a player first.");
            return;
        }
        gameLogic.play(playerManager.getCurrentPlayer());
        playerManager.saveCurrentPlayer();
    }

    private void resetPlayerStats() {
        if (playerManager.getCurrentPlayer() == null) {
            System.out.println("No player selected. Please select or create a player first.");
            return;
        }
        playerManager.resetCurrentPlayerStats();
    }
}