package com.example.minesweeper;

import java.util.Random;

public class GameBoard {

//    private static GameBoard instance = null;
    Random rand = new Random();

    public GameBoard() {
        board = new CellState[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j]= new CellState();
            }
        }
    }
//    public static GameBoard getInstance() {
//        if (instance == null) {
//            instance = new GameBoard();
//        }
//        return instance;
//    }

    private CellState[][] board;

    private CellState.State actionType = CellState.State.REVEAL;

    public void cleanBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j].clean();
            }
        }
        actionType = CellState.State.REVEAL;
    }
    public CellState.State getCoverContent(int x, int y) {
        return board[x][y].getState();
    }

    public CellState getCellState(int x, int y) { return board[x][y]; }

    public CellState.State getActionType() { return actionType; }

    public void actionFlag() {
        actionType = CellState.State.FLAG;
    }

    public void actionReveal() {
        actionType = CellState.State.REVEAL;
    }

    public void setMines() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(rand.nextInt(3) == 1) {
                    board[i][j].setValue(CellState.Value.MINE);
                }
            }
        }
    }

    // Counts the number of mines around
    public void setMineCount() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(board[i][j].getValue() != CellState.Value.MINE) {
                    int mineCounter = 0;
                    if (i > 0 && j > 0 && board[i-1][j-1].getValue() == CellState.Value.MINE)
                        mineCounter++;
                    if (i > 0 && board[i-1][j].getValue() == CellState.Value.MINE)
                        mineCounter++;
                    if (i > 0 && j < 4 && board[i-1][j+1].getValue() == CellState.Value.MINE)
                        mineCounter++;
                    if (j > 0 && board[i][j-1].getValue() == CellState.Value.MINE)
                        mineCounter++;
                    if (board[i][j].getValue() == CellState.Value.MINE)
                        mineCounter++;
                    if (j < 4 && board[i][j+1].getValue() == CellState.Value.MINE)
                        mineCounter++;
                    if (i < 4 && j > 0 && board[i+1][j-1].getValue() == CellState.Value.MINE)
                        mineCounter++;
                    if (i < 4 && board[i+1][j].getValue() == CellState.Value.MINE)
                        mineCounter++;
                    if (i < 4 &&  j < 4 && board[i+1][j+1].getValue() == CellState.Value.MINE)
                        mineCounter++;

                    CellState.Value mineCounterShort = CellState.Value.getEnum(mineCounter);

                    board[i][j].setValue( mineCounterShort);
                }
            }
        }
    }

    private int countMines() {
        int mineCounter = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].getValue() == CellState.Value.MINE)
                    mineCounter++;
            }
        }
        return mineCounter;
    }

    private boolean mineRevealed() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].getValue() == CellState.Value.MINE && board[i][j].getState() == CellState.State.TOUCHED)
                    return true;
            }
        }
        return false;
    }

    private boolean nonmineFlagged() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].getValue() != CellState.Value.MINE && board[i][j].getState() == CellState.State.FLAG)
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
                if (board[i][j].getValue() == CellState.Value.MINE && board[i][j].getState() == CellState.State.FLAG)
                    minesFlagged++;
                if (board[i][j].getValue() != CellState.Value.MINE && board[i][j].getState() == CellState.State.TOUCHED)
                    nonMinesOpened++;
            }
        }
        return minesFlagged == countMines() && nonMinesOpened == (25 - countMines());
    }
}

