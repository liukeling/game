package com.example.game.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.Nullable;

public class MyView extends View {

    private final Paint paint = new Paint();
    private float cellWidth = 0;
    private float cellHeight = 0;
    public MyView(Context context) {
        super(context);
        initPaint();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }
    private void initPaint(){
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        drawBoard(canvas);
    }

    /**
     * 画棋盘
     * @param canvas
     */
    private void drawBoard(Canvas canvas){

        if(cellWidth == 0 || cellHeight == 0){
            float width = getWidth();
            float height = getHeight();
            cellWidth = width/9;
            cellHeight = height/10;
        }
        canvas.drawLine(cellWidth/2f,cellHeight/2f,getWidth()-cellWidth/2f,cellHeight/2f,paint);
        canvas.drawLine(cellWidth/2f,cellHeight/2f,cellWidth/2f,getHeight()-cellHeight/2f,paint);
        canvas.drawLine(cellWidth/2f,getHeight()-cellHeight/2f,getWidth()-cellWidth/2f,getHeight()-cellHeight/2f,paint);
        canvas.drawLine(getWidth()-cellWidth/2f,cellHeight/2f,getWidth()-cellWidth/2f,getHeight()-cellHeight/2f,paint);
        for (float i = 1; i <= 8; i = i + 1f){
            //横
            canvas.drawLine(cellWidth/2,cellHeight*(i+0.5f), getWidth()-cellWidth/2f,cellHeight*(i+0.5f),paint);
            if(i == 9f){
                break;
            }
            //竖
            canvas.drawLine(cellWidth*(i+0.5f),cellHeight/2f,cellWidth*(i+0.5f),(4f+0.5f)*cellHeight,paint);
            canvas.drawLine(cellWidth*(i+0.5f),(5f+0.5f)*cellHeight,cellWidth*(i+0.5f),getHeight()-(cellHeight/2f),paint);
        }
        //汉字
        float textSize = cellWidth*2f/3f;
        float heigthDiff = cellHeight - textSize;
        paint.setTextSize(textSize);
        canvas.drawText("楚河",cellWidth/2+cellWidth*1.3f,(5f+0.5f)*cellHeight-heigthDiff/2,paint);
        canvas.drawText("汉界",getWidth()-cellWidth*1.8f-textSize*2f,(5f+0.5f)*cellHeight-heigthDiff/2,paint);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

}
