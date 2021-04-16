package com.example.game.view.chess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.game.view.chess.contanst.GlobalConstant;

import java.util.List;

/**
 * 棋子
 */
public abstract class ChessItem extends BaseView {
    protected GlobalConstant.ItemColorEnum color;
    private GlobalConstant.ItemNameEnum name;
    private static final int BASE_BG_COLOR = Color.parseColor("#F4A460");
    private int bgColor = BASE_BG_COLOR;

    public ChessItem(Context context, GlobalConstant.ItemColorEnum color, GlobalConstant.ItemNameEnum name, int cellX, int cellY) {
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
        calAndDraw(canvas, color.getColor(), bgColor);
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
                                //提示点
                                List<Integer[]> downInfoXYs = getDownInfoXYs();
                                for (Integer[] downInfoXY : downInfoXYs) {
                                    GlobalConstant.addDownInfo(downInfoXY[0],downInfoXY[1]);
                                }
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
        canvas.drawText(name.getName(), getWidth() / 6f, getHeight() - (getHeight() - getWidth()) / 2 - getWidth() * 7f / 30f, paint);
    }

    @Override
    public void reset() {
        bgColor = BASE_BG_COLOR;
        invalidate();
    }

    public GlobalConstant.ItemNameEnum getName() {
        return name;
    }

    public GlobalConstant.ItemColorEnum getColor() {
        return color;
    }

    /**
     * 获取提示点坐标
     * @return
     */
    protected abstract List<Integer[]> getDownInfoXYs();
}
