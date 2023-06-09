package com.example.minesweeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MinesweeperView extends View {

    private Paint paintBg;
    private Paint paintLine;
    private Paint paintOpen;
    private Bitmap imageFlag;

    private boolean gameEnd = false;

    private Context ctx;

    public GameBoard gameBoard;

    public MinesweeperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;

        paintBg = new Paint();
        paintBg.setColor(Color.GRAY);
        paintBg.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLine.setColor(Color.BLACK);

        paintOpen = new Paint();
        paintOpen.setARGB(0,0,0,0);

//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inScaled = true;
//        options.inSampleSize = 2;
        imageFlag = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.flag, null);

        gameBoard = new GameBoard();

        gameBoard.setMines();
        gameBoard.setMineCount();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draws tiles
        drawGameArea(canvas);
        drawTiles(canvas);
        drawGrid(canvas);
    }

    private void drawTiles(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                float centerX = (i) * getWidth() / 5;
                float centerY = (j) * getHeight() / 5;

                CellAppearance tw = CellFactory.getInstance(getContext()).create(gameBoard.getCellState(i, j));    //SS new CellAppearance(getContext(), gameBoard.getCellState(i, j));
                //SS tw.drawImage(canvas, centerX, centerY, getWidth(), getHeight());
                drawImage(canvas, tw, centerX, centerY, getWidth(), getHeight());
            }
        }
        Log.i("MODEL_TAG", "Bombs and Numbers displayed");
    }

    public void drawImage(Canvas canvas, CellAppearance cellAppearance, float centerX, float centerY, float width, float height) {
        if(cellAppearance.getImage() != null)
            canvas.drawBitmap(cellAppearance.getImage(), centerX, centerY, null);
        if (cellAppearance.getState() == Cell.State.UNTOUCHED)
            canvas.drawRect(centerX, centerY, centerX + (width / 5), centerY + (height / 5), paintBg);
        else if (cellAppearance.getState() == Cell.State.TOUCHED)
            canvas.drawRect(centerX, centerY, centerX + width / 5, centerY + height / 5, paintOpen);
        else if (cellAppearance.getState() == Cell.State.FLAG) {
            canvas.drawRect(centerX, centerY, centerX + width / 5, centerY + height / 5, paintBg);
            //SS canvas.drawBitmap(CellFactory.getInstance(ctx).getImage(CellState.State.FLAG.toString()), centerX, centerY, null);

            canvas.drawBitmap(imageFlag, centerX, centerY, null);
        }
    }


    private void drawGrid(Canvas canvas) {

        Log.i("MODEL_TAG", "Cover displayed");

        // four horizontal lines
        canvas.drawLine(0, getHeight() / 5, getWidth(), getHeight() / 5, paintLine);
        canvas.drawLine(0, 2 * getHeight() / 5, getWidth(), 2 * getHeight() / 5, paintLine);
        canvas.drawLine(0, 3 * getHeight() / 5, getWidth(), 3 * getHeight() / 5, paintLine);
        canvas.drawLine(0, 4 * getHeight() / 5, getWidth(), 4 * getHeight() / 5, paintLine);

        // four vertical lines
        canvas.drawLine(getWidth() / 5, 0, getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(2 * getWidth() / 5, 0, 2 * getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(3 * getWidth() / 5, 0, 3 * getWidth() / 5, getHeight(), paintLine);
        canvas.drawLine(4 * getWidth() / 5, 0, 4 * getWidth() / 5, getHeight(), paintLine);
        Log.i("MODEL_TAG", "Lines drawn");
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (gameEnd) {
                gameBoard.cleanBoard();
                gameBoard.setMines();
                gameBoard.setMineCount();
                invalidate();
                gameEnd = false;
            }
            else {
                int tX = ((int) event.getX()) / (getWidth() / 5);
                int tY = ((int) event.getY()) / (getHeight() / 5);

                handleCoverTouch(tX, tY);

                winningChecker();
            }

            invalidate();
        }
        return super.onTouchEvent(event);
    }

    private void handleCoverTouch(int tX, int tY) {
        if (tX < 5 && tY < 5 &&
            gameBoard.getCellState(tX, tY).getState() == Cell.State.UNTOUCHED &&
            gameBoard.getActionType() == CellState.State.REVEAL) {
            gameBoard.getCellState( tX, tY).setState(CellState.State.TOUCHED );
        }
        else if (tX < 5 && tY < 5 &&
            gameBoard.getCoverContent(tX, tY) == CellState.State.UNTOUCHED &&
            gameBoard.getActionType() == CellState.State.FLAG) {
            gameBoard.getCellState( tX, tY).setState(CellState.State.FLAG );
        }
    }

    private void drawGameArea(Canvas canvas) {
        // border
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBg);
    }

    private void winningChecker() {
        if (gameBoard.checkAllTiles() && !(gameBoard.gameLost()) ) {
            //game won
            ((MainActivity) getContext()).showSnackBarWithDelete(
                    "Congratulations you win!");
            gameEnd = true;
        }
        else if (gameBoard.gameLost()) {
            ((MainActivity) getContext()).showSnackBarWithDelete(
                    "Oh no! You lost!");
            gameEnd = true;
        }
        else if (!(gameBoard.checkAllTiles()) && !(gameBoard.gameLost())) {

        }
    }
}
