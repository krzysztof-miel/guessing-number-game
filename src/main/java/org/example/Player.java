package org.example;

public class Player {
    private final String name;
    private int bestScore;

    public Player(String name) {
        this.name = name;
        this.bestScore = Integer.MAX_VALUE; // Default to a very high number
    }

    public Player(String name, int bestScore) {
        this.name = name;
        this.bestScore = bestScore;
    }

    public String getName() {
        return name;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}
