package com.example.tic_tac_toe;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private List<Player> players;
    public static List<List> WINNING_COMBINATIONS;
    private Player currentPlayer;
    private int count;

    //Static block to initialize all winning combinations
    static {
        WINNING_COMBINATIONS = new ArrayList<>(8);
        WINNING_COMBINATIONS.add(Arrays.asList("1", "2", "3"));
        WINNING_COMBINATIONS.add(Arrays.asList("4", "5", "6"));
        WINNING_COMBINATIONS.add(Arrays.asList("7", "8", "9"));
        WINNING_COMBINATIONS.add(Arrays.asList("1", "4", "7"));
        WINNING_COMBINATIONS.add(Arrays.asList("2", "5", "8"));
        WINNING_COMBINATIONS.add(Arrays.asList("3", "6", "9"));
        WINNING_COMBINATIONS.add(Arrays.asList("1", "5", "9"));
        WINNING_COMBINATIONS.add(Arrays.asList("3", "5", "7"));
    }

    public Game(Player firstPlayer, Player secondPlayer) {
        this.players = new ArrayList<>(2);
        this.addPlayer(firstPlayer);
        this.addPlayer(secondPlayer);
        this.setCurrentPlayer(firstPlayer);
        this.count = 0;
    }

    private void addPlayer(Player player) {
        this.players.add(player);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean hasWon(Player player) {
        return Game.WINNING_COMBINATIONS.stream().anyMatch(player::hasMoveSet);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void setCurrentPlayer(Player currentPlayer) {
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
