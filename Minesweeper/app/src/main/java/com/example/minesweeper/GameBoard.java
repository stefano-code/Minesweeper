package com.example.minesweeper;

import android.util.Log;

import java.util.Random;

public class GameBoard {

//    private static GameBoard instance = null;
    Random rand = new Random();

    public GameBoard() {
        board = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j]= new Cell();
            }
        }
    }
//    public static GameBoard getInstance() {
//        if (instance == null) {
//            instance = new GameBoard();
//        }
//        return instance;
//    }

    private Cell[][] board;

    private Cell.State actionType = Cell.State.REVEAL;

    public void cleanBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j].clean();
            }
        }
        actionType = Cell.State.REVEAL;
    }
    public Cell.State getCoverContent(int x, int y) {
        return board[x][y].getState();
    }

    public Cell getTile(int x, int y) { return board[x][y]; }

    public Cell.State getActionType() { return actionType; }

    public void actionFlag() {
        actionType = Cell.State.FLAG;
        Log.d("MODEL_TAG", "actionType = FLAG!");
    }

    public void actionReveal() {
        actionType = Cell.State.REVEAL;
        Log.d("MODEL_TAG", "actionType = REVEAL!");
    }

    public void setMines() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(rand.nextInt(3) == 1) {
                    board[i][j].setValue(Cell.Value.MINE);
                    Log.i("MODEL_TAG", "Model " + "[" + i + "]" + "[" + j + "]" + " has a mine!");
                }
            }
        }
    }

    // Counts the number of mines around
    public void setMineCount() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(board[i][j].getValue() != Cell.Value.MINE) {
                    int mineCounter = 0;
                    if (i > 0 && j > 0 && board[i-1][j-1].getValue() == Cell.Value.MINE)
                        mineCounter++;
                    if (i > 0 && board[i-1][j].getValue() == Cell.Value.MINE)
                        mineCounter++;
                    if (i > 0 && j < 4 && board[i-1][j+1].getValue() == Cell.Value.MINE)
                        mineCounter++;
                    if (j > 0 && board[i][j-1].getValue() == Cell.Value.MINE)
                        mineCounter++;
                    if (board[i][j].getValue() == Cell.Value.MINE)
                        mineCounter++;
                    if (j < 4 && board[i][j+1].getValue() == Cell.Value.MINE)
                        mineCounter++;
                    if (i < 4 && j > 0 && board[i+1][j-1].getValue() == Cell.Value.MINE)
                        mineCounter++;
                    if (i < 4 && board[i+1][j].getValue() == Cell.Value.MINE)
                        mineCounter++;
                    if (i < 4 &&  j < 4 && board[i+1][j+1].getValue() == Cell.Value.MINE)
                        mineCounter++;

                    Cell.Value mineCounterShort = Cell.Value.getEnum(mineCounter);

                    board[i][j].setValue( mineCounterShort);
                    Log.i("MODEL_TAG", "Model " + "[" + i + "]" + "[" + j + "]" + " has " + mineCounter + " around it!");
                }
            }
        }
    }

    private int countMines() {
        int mineCounter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].getValue() == Cell.Value.MINE)
                    mineCounter++;
            }
        }
        return mineCounter;
    }

    private boolean mineRevealed() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].getValue() == Cell.Value.MINE && board[i][j].getState() == Cell.State.TOUCHED)
                    return true;
            }
        }
        return false;
    }

    private boolean nonmineFlagged() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].getValue() != Cell.Value.MINE && board[i][j].getState() == Cell.State.FLAG)
                    return true;
            }
        }
        return false;
    }

    public boolean gameLost() {
        return mineRevealed() || nonmineFlagged();
    }

    public boolean checkAllTiles() {
        int minesFlagged = 0;
        int nonMinesOpened = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].getValue() == Cell.Value.MINE && board[i][j].getState() == Cell.State.FLAG)
                    minesFlagged++;
                if (board[i][j].getValue() != Cell.Value.MINE && board[i][j].getState() == Cell.State.TOUCHED)
                    nonMinesOpened++;
            }
        }
        return minesFlagged == countMines() && nonMinesOpened == (25 - countMines());
    }
}
