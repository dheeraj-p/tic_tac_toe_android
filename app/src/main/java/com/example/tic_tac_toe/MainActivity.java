package com.example.tic_tac_toe;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9;
    Game game;
    Button playAgain;
    RelativeLayout resultDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playAgain = findViewById(R.id.playAgain);

        resultDialog = findViewById(R.id.resultDialog);

        cell1 = findViewById(R.id.cell1);

        cell2 = findViewById(R.id.cell2);

        cell3 = findViewById(R.id.cell3);

        cell4 = findViewById(R.id.cell4);

        cell5 = findViewById(R.id.cell5);

        cell6 = findViewById(R.id.cell6);

        cell7 = findViewById(R.id.cell7);

        cell8 = findViewById(R.id.cell8);

        cell9 = findViewById(R.id.cell9);


        startNewGame();
        playAgain.setOnClickListener(v -> startNewGame());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        String symbol = this.getPlayerSymbol();
        String cellNo = v.getTag().toString();
        TextView cell = (TextView) v;
        cell.setText(symbol);
        cell.setOnClickListener(null);

        String message = game.getGameState(cellNo);
        if(message != "") {
            endGame(message);
        }

    }

    private void endGame(String message) {
        this.resultDialog = findViewById(R.id.resultDialog);
        this.resultDialog.setVisibility(View.VISIBLE);
        TextView result = findViewById(R.id.result);
        result.setText(message);
        freezeScreen();
    }

    private void freezeScreen() {
        cell1.setOnClickListener(null);
        cell2.setOnClickListener(null);
        cell3.setOnClickListener(null);
        cell4.setOnClickListener(null);
        cell5.setOnClickListener(null);
        cell6.setOnClickListener(null);
        cell7.setOnClickListener(null);
        cell8.setOnClickListener(null);
        cell9.setOnClickListener(null);
    }

    private void startNewGame() {
        this.resultDialog.setVisibility(View.GONE);

        cell1.setText("");
        cell2.setText("");
        cell3.setText("");
        cell4.setText("");
        cell5.setText("");
        cell6.setText("");
        cell7.setText("");
        cell8.setText("");
        cell9.setText("");

        cell1.setOnClickListener(this);
        cell2.setOnClickListener(this);
        cell3.setOnClickListener(this);
        cell4.setOnClickListener(this);
        cell5.setOnClickListener(this);
        cell6.setOnClickListener(this);
        cell7.setOnClickListener(this);
        cell8.setOnClickListener(this);
        cell9.setOnClickListener(this);

        initializeGame();
    }

    private String getPlayerSymbol() {
        Player currentPlayer = this.game.getCurrentPlayer();
        return currentPlayer.getSymbol();
    }

    public void initializeGame() {
        Player player1 = new Player("Player1", "X");
        Player player2 = new Player("Player2", "O");
        this.game = new Game(player1, player2);
    }
}
