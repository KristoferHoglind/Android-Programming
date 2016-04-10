package com.example.stoffesbok.lab3;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class NameComponent extends View {

    private Context context;
    private String name;
    private Paint text_paint;
    private Paint background_paint;
    private int index;

    // Constructor
    public NameComponent(Context _context, String _name, int _index)
    {
        super(_context);
        context = _context;
        name = _name;
        index = _index;
        text_paint = new Paint();
        background_paint = new Paint();
    }

    // Get the name of this component
    public String getNameOfComponent()
    {
        return name;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        background_paint.setColor(Color.TRANSPARENT);
        canvas.drawPaint(background_paint);

        text_paint.setColor(Color.BLACK);

        // TODO: Dynamisk font-size?
        text_paint.setTextSize(40);
        text_paint.setAntiAlias(true);
        text_paint.setTextAlign(Paint.Align.LEFT);

        // TODO: Dynamisk position i listan?
        canvas.drawText(name, 35, 60, text_paint);
    }

    @Override
    protected void onMeasure(int width, int height)
    {
        super.onMeasure(width, height);

        // TODO: Dynamisk Width & Height
        setMeasuredDimension(500, 100);
    }
}
