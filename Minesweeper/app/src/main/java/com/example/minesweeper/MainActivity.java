package com.example.minesweeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private int group1Id = 1;

    int restartId = Menu.FIRST;
    int profileId = Menu.FIRST +1;
    int searchId = Menu.FIRST +2;

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(group1Id, restartId, restartId, "").setIcon(R.drawable.bomb);
        return super.onCreateOptionsMenu(menu);
    }

     */

    private LinearLayout linearLayout;
    private GameBoard gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.activity_main);

        gameBoard = ((MinesweeperView) findViewById(R.id.gameView)).gameBoard;

        Button btnRestart = (Button) findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gameBoard.cleanBoard();
                gameBoard.setMines();
                gameBoard.setMineCount();
                ((MinesweeperView) findViewById(R.id.gameView)).invalidate();

                Snackbar restartSnackbar = Snackbar.make(linearLayout, "Game restarted", Snackbar.LENGTH_LONG);
                restartSnackbar.show();
            }
        });

        Button btnFlag = (Button) findViewById(R.id.btnFlag);
        btnFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Function that sets touches to place flags

                gameBoard.actionFlag();

                Snackbar flagSnackbar = Snackbar.make(linearLayout, "Flagging on", Snackbar.LENGTH_LONG);
                flagSnackbar.show();
            }
        });

        Button btnReveal = (Button) findViewById(R.id.btnReveal);
        btnReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Function that sets touches to open tiles
                gameBoard.actionReveal();

                Snackbar revealSnackbar = Snackbar.make(linearLayout, "Flagging off", Snackbar.LENGTH_LONG);
                revealSnackbar.show();
            }
        });
    }

    public void showSnackBarWithDelete(String msg) {
        Snackbar.make(linearLayout, msg,
                Snackbar.LENGTH_LONG).setAction(
                "Restart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Restart the game
                        gameBoard.cleanBoard();
                        gameBoard.setMines();
                        gameBoard.setMineCount();
                    }
                }
        ).show();
    }
}