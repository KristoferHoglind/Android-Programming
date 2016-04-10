package com.example.stoffesbok.projekt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Carousel class, a custom view component class containing an image.
 * If custom items wants to be created; only onDraw and onMeasure needs to be overriden. As this class only uses those two functions from View.
 */
public class CarouselItem extends View
{
    // Private variables
    private Context context;

    /**
     * An image that is added to the component.
     */
    private Bitmap image;

    /**
     * A unique id for each item.
     */
    private int id;

    /**
     * Width parameter of the view.
     */
    private int width;

    /**
     * Height parameter of the view.
     */
    private int height;

    /**
     * Creates a custom component with an item and holds an image.
     * @param _image The image that will be shown.
     */
    public CarouselItem(Context _context, Bitmap _image)
    {
        super(_context);
        context = _context;
        image = _image;
    }

    /** On draw, draw the item on the view.
     * @param canvas The canvas that will be drawn.
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Paint canvas_paint = new Paint();
        canvas_paint.setAntiAlias(false);
        canvas_paint.setFilterBitmap(false);

        // Scale images for item size
        float width_after_scale;
        float height_after_scale;
        if(image.getHeight() < image.getWidth())
        {
            width_after_scale = (float)width;
            height_after_scale = (float)image.getHeight() * (width_after_scale/(float)image.getWidth());
        }
        else
        {
            height_after_scale = (float)height;
            width_after_scale = (float)image.getWidth() * (height_after_scale/(float)image.getHeight());
        }

        // The height of an image should never exceed the desired height of the carousel
        if(height_after_scale > height)
        {
            height_after_scale = height;
            width_after_scale = (float)image.getWidth() * (height_after_scale/(float)image.getHeight());
        }

        // Draw each image in the middle of each item
        Rect draw_size = new Rect(5 + (width-(int)width_after_scale)/2, // <-- From width
                (height-(int)height_after_scale)/2, // <-- From height
                (width-(int)width_after_scale)/2 + (int)width_after_scale - 5, // <-- Too width
                (height-(int)height_after_scale)/2 + (int)height_after_scale); // <-- Too height
        canvas.drawBitmap(image, null, draw_size, canvas_paint);
    }

    /** Define the size of the view
     * @param widthMeasureSpec The width parameter of the item.
     * @param heightMeasureSpec The height parameter of the item.
      */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // With a large enough number, dimensions (width) will scale down to parent-size instead...
        int desiredWidth = Integer.MAX_VALUE;
        int desiredHeight = 350;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = widthSize/8; // <-- Every item should be maximum sized as a square

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }
}
