package com.example.game.view.chess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public abstract class BaseView extends View {
    //画笔
    protected final Paint paint = new Paint();
    protected int cellX;
    protected int cellY;

    public BaseView(Context context) {
        super(context);
        initPaint();
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBoard(canvas);
    }

    /**
     * 画笔初始
     */
    protected abstract void initPaint();
    /**
     * 绘画
     * @param canvas
     */
    protected abstract void drawBoard(Canvas canvas);

    /**
     * 还原
     */
    protected void reset(){

    }

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return cellY;
    }
}
