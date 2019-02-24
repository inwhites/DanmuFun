package com.example.inwhites.danmufun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.style.DynamicDrawableSpan;


public class CustomEmojDanmuSpan extends DynamicDrawableSpan {

    public static int alpha = 255;
    private Drawable mDrawable;
    private Context mContext;
    float textSize;
    float currentSize;


    public CustomEmojDanmuSpan(Context context, Bitmap b) {
        this(context, b, 0);
    }

    public CustomEmojDanmuSpan(Context context, Bitmap b, int verticalAlignment) {
        super(verticalAlignment);
        this.textSize = 36;
        this.currentSize = this.textSize;
        this.mContext = context;
        this.mDrawable = context != null ? new BitmapDrawable(context.getResources(), b) : new BitmapDrawable(b);
        int width = this.mDrawable.getIntrinsicWidth();
        int height = this.mDrawable.getIntrinsicHeight();
        this.mDrawable.setBounds(0, 0, width > 0 ? width : 0, height > 0 ? height : 0);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        Drawable b = this.getDrawable();
        b.setAlpha(alpha);
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int transY = (y + fm.descent + y + fm.ascent) / 2 - b.getBounds().bottom / 2;
        canvas.save();
        canvas.translate(x, (float) transY);
        b.draw(canvas);
        canvas.restore();
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fontMetricsInt) {
        Drawable drawable = this.getDrawable();
        int fontHeight;
        if (paint.getTextSize() != this.currentSize) {
            this.currentSize = paint.getTextSize();
            float scale = this.currentSize / this.textSize;
            int width = (int) ((float) this.mDrawable.getIntrinsicWidth() * scale);
            fontHeight = (int) ((float) this.mDrawable.getIntrinsicHeight() * scale);
            this.mDrawable.setBounds(0, 0, width, fontHeight);
        }

        Rect rect = drawable.getBounds();
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fmPaint = paint.getFontMetricsInt();
            fontHeight = fmPaint.bottom - fmPaint.top;
            int drHeight = rect.bottom - rect.top;
            int top = drHeight / 2 - fontHeight / 4;
            int bottom = drHeight / 2 + fontHeight / 4;
            fontMetricsInt.ascent = -bottom;
            fontMetricsInt.top = -bottom;
            fontMetricsInt.bottom = top;
            fontMetricsInt.descent = top;
        }

        return rect.right;
    }

    @Override
    public Drawable getDrawable() {
        Drawable drawable = null;
        if (this.mDrawable != null) {
            drawable = this.mDrawable;
        } else {
            this.mDrawable = new BitmapDrawable();
        }

        return drawable;
    }


}
