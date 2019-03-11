package com.example.tic_tac_toe;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<String> moves;
    private String symbol;

    public Player(String symbol) {
        this.moves = new ArrayList<String>(5);
        this.symbol = symbol;
    }

    public void makeMove(String move) {
        moves.add(move);
    }

    public String getSymbol() {
        return this.symbol;
    }

    public List<String> getMoves() {
        return moves;
    }
}
