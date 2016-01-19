package com.example.interview.hailininterviewapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class DemoView extends View {
    private List<FuriganaUnit> furiganaText;

    private float mTextSize = 24;
    private float mIpaSize = 18;

    private TextPaint mTextPaint;
    private TextPaint mIpaPaint;

    private int nextLine = 0;
    private float nextX = 0;

    private Paint.FontMetrics ipaFontMetrics;
    private Paint.FontMetrics textFontMetrics;

    public DemoView(Context context) {
        super(context);
        init(null, 0);
    }

    public DemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DemoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.DemoView, defStyle, 0);

        mTextSize = a.getDimension(R.styleable.DemoView_textSize, mTextSize);
        mIpaSize = a.getDimension(R.styleable.DemoView_ipaSize, mIpaSize);

        a.recycle();

        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setTextSize(mTextSize);
        textFontMetrics = mTextPaint.getFontMetrics();

        mIpaPaint = new TextPaint();
        mIpaPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mIpaPaint.setTextAlign(Paint.Align.LEFT);
        mIpaPaint.setTextSize(mIpaSize);
        ipaFontMetrics = mIpaPaint.getFontMetrics();
    }

    // Paint.fontMatrics

    @Override
    protected void onDraw(Canvas canvas) {
        if (furiganaText == null || furiganaText.isEmpty()) {
            return;
        }
        nextLine = 0;
        nextX = 0;

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

        for (int i = 0; i < furiganaText.size(); i++) {
            FuriganaUnit unit = furiganaText.get(i);

            float textWidth = mTextPaint.measureText(unit.text);
            float ipaWidth = mIpaPaint.measureText(unit.ipa);
            float requriedUnitWidth = Math.max(textWidth, ipaWidth);

            if (nextX + requriedUnitWidth > contentWidth) {
                nextLine++;
                nextX = 0F;
            }

            float thisX = nextX;
            nextX += requriedUnitWidth;

            float topBound = paddingTop + nextLine * (ipaFontMetrics.bottom - ipaFontMetrics.top +
                    textFontMetrics.bottom - textFontMetrics.top);

            if (unit.ipaVisible) {
                // ipa
                drawText(thisX, topBound, requriedUnitWidth, unit.ipa, mIpaPaint, canvas);
            }

            // text
            drawText(thisX, topBound + (ipaFontMetrics.bottom - ipaFontMetrics.top),
                    requriedUnitWidth, unit.text, mTextPaint, canvas);
        }

    }

    /**
     * Draw text in the middle of the totalWidth
     * @param leftBound
     * @param topBound
     * @param totalWidth
     * @param text
     * @param paint
     * @param canvas
     */
    private void drawText(float leftBound, float topBound, float totalWidth, String text, TextPaint paint, Canvas canvas) {
        float textWidth = paint.measureText(text);
        float startX = leftBound + (totalWidth - textWidth) / 2;
        canvas.drawText(text, startX, topBound - paint.getFontMetrics().top, paint);
    }

    public void setFuriganaText(List<FuriganaUnit> furiganaText) {
        this.furiganaText = furiganaText;
        postInvalidate();
    }
}
