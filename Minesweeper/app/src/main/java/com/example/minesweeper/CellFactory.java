package com.example.minesweeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.HashMap;

    public class CellFactory {
        private HashMap<String, Bitmap> images = new HashMap<String, Bitmap>();

        static CellFactory getInstance(Context ctx) {
            if(instance == null)
                instance = new CellFactory(ctx);
            return instance;
        }
        static CellFactory instance;

        private CellFactory(Context ctx) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = true;
            options.inSampleSize = 2;
            images.put(CellState.Value.MINE.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.bomb, options));
            images.put(CellState.Value.ONE.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.one, options));
            images.put(CellState.Value.TWO.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.two, options));
            images.put(CellState.Value.THREE.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.three, options));
            images.put(CellState.Value.FOUR.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.four, options));
            images.put(CellState.Value.FIVE.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.five, options));
            images.put(CellState.Value.SIX.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.six, options));
            images.put(CellState.Value.SEVEN.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.seven, options));
            images.put(CellState.Value.EIGHT.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.eight, options));
        }

        CellAppearance create(CellState cellState) {
            return new CellAppearance(cellState, images.get(cellState.getValue().toString()));
        }
    }

