package org.example;

import java.io.*;
import java.util.Optional;

public class PlayerManager {
    private static final String DATA_FOLDER = "players";
    private Player currentPlayer;

    public PlayerManager() {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public void loadOrCreatePlayer(String playerName) {
        File playerFile = new File(DATA_FOLDER, playerName + ".txt");
        if (playerFile.exists()) {
            currentPlayer = loadPlayer(playerFile);
            System.out.println("Welcome back, " + currentPlayer.getName() + ". Your best score: " + currentPlayer.getBestScore());
        } else {
            currentPlayer = new Player(playerName);
            saveCurrentPlayer();
            System.out.println("New player created: " + playerName);
        }
    }

    public void saveCurrentPlayer() {
        if (currentPlayer == null) return;
        File playerFile = new File(DATA_FOLDER, currentPlayer.getName() + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerFile))) {
            writer.write("BestScore:" + currentPlayer.getBestScore());
        } catch (IOException e) {
            System.out.println("Error saving player data: " + e.getMessage());
        }
    }

    public void resetCurrentPlayerStats() {
        if (currentPlayer != null) {
            currentPlayer.setBestScore(Integer.MAX_VALUE);
            File playerFile = new File(DATA_FOLDER, currentPlayer.getName() + ".txt");
            if (playerFile.exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(playerFile))) {
                    writer.write(""); // Clear the file content
                } catch (IOException e) {
                    System.out.println("Error resetting player data: " + e.getMessage());
                }
            }
            System.out.println("Stats reset for player: " + currentPlayer.getName());
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private Player loadPlayer(File playerFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(playerFile))) {
            String line = reader.readLine();
            int bestScore = Integer.parseInt(line.split(":")[1]);
            return new Player(playerFile.getName().replace(".txt", ""), bestScore);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading player data: " + e.getMessage());
        }
        return new Player(playerFile.getName().replace(".txt", ""));
    }
}