package com.example.minesweeper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.HashMap;

public class FlyweightFactory {
    private HashMap<String, Bitmap> images = new HashMap<String, Bitmap>();
    private HashMap<String, Paint> paints = new HashMap<String, Paint>();

    private Paint paintBg;
    private Paint paintOpen;

    static FlyweightFactory getInstance(Context ctx)
    {
        if(instance == null)
            instance = new FlyweightFactory(ctx);
        return instance;
    }
    static FlyweightFactory instance;

    private FlyweightFactory(Context ctx)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        options.inSampleSize = 2;
        images.put(Cell.State.FLAG.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.flag, options));
        images.put(Cell.Value.MINE.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.bomb, options));
        images.put(Cell.Value.ONE.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.one, options));
        images.put(Cell.Value.TWO.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.two, options));
        images.put(Cell.Value.THREE.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.three, options));
        images.put(Cell.Value.FOUR.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.four, options));
        images.put(Cell.Value.FIVE.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.five, options));
        images.put(Cell.Value.SIX.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.six, options));
        images.put(Cell.Value.SEVEN.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.seven, options));
        images.put(Cell.Value.EIGHT.toString(), BitmapFactory.decodeResource(ctx.getResources(), R.drawable.eight, options));

        paintBg = new Paint();
        paintBg.setColor(Color.GRAY);
        paintBg.setStyle(Paint.Style.FILL);

        paintOpen = new Paint();
        paintOpen.setARGB(0,0,0,0);

        paints.put(Cell.State.TOUCHED.toString(), paintOpen);
        paints.put(Cell.State.UNTOUCHED.toString(), paintBg);
    }

    public Bitmap getImage(String key)
    {
        return images.get(key);
    }

    public Paint getPaint(String key) {
        return paints.get(key);
    }
}
