package com.example.tic_tac_toe;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private List<Player> players;
    private List<List> winningCombinations;
    private Player currentPlayer;
    private int count;

    public Game() {
        this.players = new ArrayList<>(2);
        winningCombinations = new ArrayList<>(8);
        winningCombinations.add(Arrays.asList("1", "2", "3"));
        winningCombinations.add(Arrays.asList("4", "5", "6"));
        winningCombinations.add(Arrays.asList("7", "8", "9"));
        winningCombinations.add(Arrays.asList("1", "4", "7"));
        winningCombinations.add(Arrays.asList("2", "5", "8"));
        winningCombinations.add(Arrays.asList("3", "6", "9"));
        winningCombinations.add(Arrays.asList("1", "5", "9"));
        winningCombinations.add(Arrays.asList("3", "5", "7"));
        this.count = 0;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean hasWon(Player player) {
        return winningCombinations.stream().anyMatch(player::hasMoveSet);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateCurrentPlayer() {
        this.currentPlayer = this.players.stream().filter(y -> y != this.currentPlayer).collect(Collectors.toList()).get(0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getGameState(String cellNo) {
        count++;
        currentPlayer.makeMove(cellNo);
        if (this.hasWon(currentPlayer)) {
            String symbol = currentPlayer.getSymbol();
            return symbol + " has won";
        }
        if (count == 9) {
            return "Game Draw";
        }
        this.updateCurrentPlayer();
        return "";
    }
}
