package me.jfenn.feedage.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import me.jfenn.feedage.R;

public class ProgressLineView extends View {

    private Paint backgroundPaint;
    private Paint linePaint;

    private float progress;
    private float drawnProgress;

    public ProgressLineView(Context context) {
        this(context, null);
    }

    public ProgressLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(Color.DKGRAY);
        linePaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
    }

    public void update(float progress) {
        this.progress = progress;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drawnProgress != progress)
            drawnProgress = ((drawnProgress * 4) + progress) / 5;

        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
        canvas.drawRect(0, 0, canvas.getWidth() * drawnProgress, canvas.getHeight(), linePaint);

        if ((drawnProgress - progress) * canvas.getWidth() != 0)
            postInvalidate();
    }
}
