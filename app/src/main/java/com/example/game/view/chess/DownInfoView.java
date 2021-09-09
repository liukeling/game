package com.example.game.view.chess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.game.view.chess.contanst.GlobalConstant;

/**
 * 提示点
 */
public class DownInfoView extends BaseView {
    private int color = Color.WHITE;
    private int bgColor = Color.DKGRAY;
    private int cellX;
    private int cellY;
    public DownInfoView(Context context,int cellX,int cellY) {
        super(context);
        this.cellX = cellX;
        this.cellY = cellY;
    }

    @Override
    protected void initPaint() {
        paint.setStrokeWidth(5);
    }

    @Override
    protected void drawBoard(final Canvas canvas) {
        calAndDraw(canvas,color,bgColor);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {}
        });
    }

    @Override
    public void setOnClickListener(@Nullable final OnClickListener l) {
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalConstant.clickToDefaultChange();
                synchronized (GlobalConstant.handLock){
                    if(GlobalConstant.tmp != null &&( GlobalConstant.tmp instanceof ChessItem)){
                        //移动棋子到这里
                        GlobalConstant.reAddItemView((ChessItem)GlobalConstant.tmp,cellX,cellY);
                        switch (GlobalConstant.colorFlag){
                            case RED:
                                GlobalConstant.colorFlag = GlobalConstant.ItemColorEnum.BLACK;
                                break;
                            case RED_:
                                GlobalConstant.colorFlag = GlobalConstant.ItemColorEnum.BLACK_;
                                break;
                            case BLACK:
                                GlobalConstant.colorFlag = GlobalConstant.ItemColorEnum.RED;
                                break;
                            case BLACK_:
                                GlobalConstant.colorFlag = GlobalConstant.ItemColorEnum.RED_;
                                break;
                        }
                    }
                }
                l.onClick(DownInfoView.this);
            }
        };
        super.setOnClickListener(listener);
    }

    private void calAndDraw(Canvas canvas,int color,int bgColor){
        paint.setColor(bgColor);
        canvas.drawArc(0,(getHeight()-getWidth())/2,getWidth(),getHeight()-(getHeight()-getWidth())/2,0,360,true,paint);
        paint.setColor(color);
        canvas.drawArc(getWidth()/6f,(getHeight()-getWidth())/2+getWidth()/6,getWidth()*5f/6f,getHeight()-(getHeight()-getWidth())/2-getWidth()/6f,0,360,true,paint);
        paint.setColor(bgColor);
        canvas.drawArc(getWidth()/3f,(getHeight()-getWidth())/2+getWidth()/3f,getWidth()*4f/6f,getHeight()-(getHeight()-getWidth())/2-getWidth()/3f,0,360,true,paint);
    }

}
