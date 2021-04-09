package com.example.game.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.game.contanst.GlobalConstant;

/**
 * 棋子
 */
public class ChessItem extends BaseView {
    private int color;
    private String name;
    private static final int BASE_BG_COLOR = Color.parseColor("#F4A460");
    private int bgColor = BASE_BG_COLOR;

    public ChessItem(Context context, int color, String name,int cellX,int cellY) {
        super(context);
        this.bgColor = bgColor;
        this.color = color;
        this.name = name;
        this.cellX = cellX;
        this.cellY = cellY;
    }

    public ChessItem(Context context, ChessItem chessItem,int cellX,int cellY) {
        super(context);
        this.bgColor = chessItem.bgColor;
        this.color = chessItem.color;
        this.name = chessItem.name;
        this.cellX = cellX;
        this.cellY = cellY;
    }

    @Override
    protected void initPaint() {
        paint.setStrokeWidth(5);
    }

    @Override
    protected void drawBoard(final Canvas canvas) {
        calAndDraw(canvas, color, bgColor);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public void setOnClickListener(@Nullable final OnClickListener l) {
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronized (GlobalConstant.handLock) {
                    if(GlobalConstant.tmp != ChessItem.this) {
                        GlobalConstant.clickToDefaultChange();
                    }
                    if (!GlobalConstant.hasUpItem) {
                        if (GlobalConstant.containerLaout != null) {
                            if (bgColor == BASE_BG_COLOR) {
                                //todo 提示点添加
                                GlobalConstant.addDownInfo(4,4);
                                GlobalConstant.addDownInfo(2,3);
                                //拿起棋子 - 颜色变动
                                GlobalConstant.hasUpItem = true;
                                bgColor = Color.GREEN;
                                GlobalConstant.tmp = ChessItem.this;
                            } else {
                                bgColor = BASE_BG_COLOR;
                            }
                            v.invalidate();
                        }
                    }
                }
                l.onClick(ChessItem.this);
            }
        };
        super.setOnClickListener(listener);
    }

    private void calAndDraw(Canvas canvas, int color, int bgColor) {
        paint.setColor(bgColor);
        canvas.drawArc(0, (getHeight() - getWidth()) / 2, getWidth(), getHeight() - (getHeight() - getWidth()) / 2, 0, 360, true, paint);
        paint.setColor(color);
        canvas.drawArc(getWidth() * 3f / 50f, (getHeight() - getWidth()) / 2 + getWidth() * 3f / 50f, getWidth() - getWidth() * 3f / 50f, getHeight() - (getHeight() - getWidth()) / 2 - getWidth() * 3f / 50f, 0, 360, true, paint);
        paint.setColor(bgColor);
        canvas.drawArc(getWidth() * 4f / 50f, (getHeight() - getWidth()) / 2 + getWidth() * 4f / 50f, getWidth() - getWidth() * 4f / 50f, getHeight() - (getHeight() - getWidth()) / 2 - getWidth() * 4f / 50f, 0, 360, true, paint);
        paint.setColor(color);
        paint.setTextSize(getWidth() * 2f / 3f);
        canvas.drawText(name, getWidth() / 6f, getHeight() - (getHeight() - getWidth()) / 2 - getWidth() * 7f / 30f, paint);
    }

    @Override
    public void reset() {
        bgColor = BASE_BG_COLOR;
        invalidate();
    }
}
