package com.example.minesweeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CellView {
    private Cell cell;
    private Context ctx;

    public CellView(Context ctx, Cell tile)
    {
        this.ctx = ctx;
        this.cell = tile;
    }

    /*
    public Bitmap getImage()
    {
        return ImageFactory.getInstance(ctx).getImage(tile.getValue().toString());
    }
     */

    public void drawImage(Canvas canvas, float centerX, float centerY, float width, float height) {
        Bitmap bmp = FlyweightFactory.getInstance(ctx).getImage(cell.getValue().toString());
        if(bmp != null)
            canvas.drawBitmap(bmp, centerX, centerY, null);
        if (cell.IsUnTouched())
            canvas.drawRect(centerX, centerY, centerX + (width / 5), centerY + (height / 5), FlyweightFactory.getInstance(ctx).getPaint(Cell.State.UNTOUCHED.toString()));
        else if (cell.IsTouched())
            canvas.drawRect(centerX, centerY, centerX + width / 5, centerY + height / 5, FlyweightFactory.getInstance(ctx).getPaint(Cell.State.TOUCHED.toString()));
        else if (cell.IsFlag()) {
            canvas.drawRect(centerX, centerY, centerX + width / 5, centerY + height / 5, FlyweightFactory.getInstance(ctx).getPaint(Cell.State.UNTOUCHED.toString()));
            canvas.drawBitmap(FlyweightFactory.getInstance(ctx).getImage(Cell.State.FLAG.toString()), centerX, centerY, null);
        }
    }
}
