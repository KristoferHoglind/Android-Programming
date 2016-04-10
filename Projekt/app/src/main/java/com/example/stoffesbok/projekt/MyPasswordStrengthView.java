package com.example.stoffesbok.projekt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;

public class MyPasswordStrengthView extends PasswordStrengthView{

    // Private variables

    /**
     * The context of the view component.
     */
    private Context context;

    /**
     * Paint object to paint the color bar depending on the strength of the password.
     */
    private Paint strength_paint;

    /**
     * The underlying background color for the password strength bar.
     */
    private Paint gray_strength_paint;

    /**
     * The definition of text properties.
     */
    private Paint text_paint;

    /**
     * Password strength score. Higher is stronger.
     */
    private static int score = 0;

    /**
     * Maximum score of the password.
     */
    private int max_score;

    /**
     * Width parameter of this component.
     */
    private int width;

    /**
     * Height parameter of this component.
     */
    private int height;

    /**
     * The default constructor for the PasswordStrengthView.
     * <p>
     * The PasswordStrengthView is a visualization bar for the password input.
     *
     * @param _context a context is needed from the parent module.
     * @return a password strength view that is needed to visualize the strength of the password.
     * @see PasswordStrengthView, PasswordStrengthAlgorithm, PasswordAnalyzer
     */
    public MyPasswordStrengthView(Context _context)
    {
        super(_context);
        context = _context;
        strength_paint = new Paint();
        gray_strength_paint = new Paint();
        text_paint = new Paint();
    }

    @Override
    public void setMaxScore(int _max_score)
    {
        max_score = _max_score;
    }

    @Override
    public int getMaxScore()
    {
        return max_score;
    }

    @Override
    public void setScore(int _score)
    {
        score = _score;
    }

    /**
     * The overrided draw-function which draws the visualization (and text) of the password strength.
     * @param canvas the canvas which it draws to.
     * @see View
     */
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        gray_strength_paint.setColor(Color.argb(255, 220, 220, 220));

        //System.out.println("Max-score view = " + max_score);
        // Draw when the password is empty or doesn't fulfill any arguments
        if(score == 0)
        {
            strength_paint.setColor(Color.argb(255, 255, 0, 0));

            // For lollipop or higher; use rounded corners (they are cooler)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                canvas.drawRoundRect(20, 40, width-20, height, 13, 13, gray_strength_paint);
                canvas.drawRoundRect(20, 40, (width-20)/(max_score * 2), height, 13, 13, strength_paint);
            }
            else // ... use the regular rectangels
            {
                canvas.drawRect(20, 40, width-20, height, gray_strength_paint);
                canvas.drawRect(20, 40, (width-20)/(max_score * 2), height, strength_paint);
            }
        }else {
            // Draw the strength meter depending on the amount of fulfilled arguments
            strength_paint.setColor(Color.argb(255, (255 - (255 / max_score) * score), (255 / max_score) * score, 0));
            //System.out.println("R: " + (255 - (255 / max_score) * score));
            //System.out.println("G: " + (255 / max_score) * score);
            //System.out.println("B: " + 0);

            // For lollipop or higher; use rounded corners (they are cooler)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.drawRoundRect(20, 40, width - 20, height, 13, 13, gray_strength_paint);
                canvas.drawRoundRect(20, 40, ((width - 20) / max_score) * score, height, 13, 13, strength_paint);
            } else // ... use the regular rectangels
            {
                canvas.drawRect(20, 40, width - 20, height, gray_strength_paint);
                canvas.drawRect(20, 40, ((width - 20) / max_score) * score, height, strength_paint);
            }
        }

        // If the width is too low. Don't draw any text in the bar at all.
        if(width > 600)
        {
            // The text in the strength bar
            text_paint.setColor(Color.BLACK);
            text_paint.setTextSize(40);

            String password_strength_string = "";
            if(score == 0)
                password_strength_string = "Very weak";
            else if(0 < score && score < max_score/2)
                password_strength_string = "Weak";
            else if(score == max_score/2)
                password_strength_string = "Moderate";
            else if(max_score/2 < score && score < max_score)
                password_strength_string = "Strong";
            else if(score == max_score)
                password_strength_string = "Very strong";

            canvas.drawText("Password strength: " + password_strength_string, 70, 95, text_paint);
        }

        // We need to redraw this view on text-change
        invalidate();
    }

    /**
     * Calculate the width/height of this view.
     * Found dynamic solution here: http://stackoverflow.com/questions/12266899/onmeasure-custom-view-explanation.
     * @param widthMeasureSpec the width of this view.
     * @param heightMeasureSpec the height of this view.
     * @see View
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // With a large enough number, dimensions (width) will scale down to parent-size instead...
        int desiredWidth = Integer.MAX_VALUE;
        int desiredHeight = 120;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

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
