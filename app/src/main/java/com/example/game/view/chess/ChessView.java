package com.example.game.view.chess;

import android.content.Context;
import android.graphics.*;
import android.view.View;
import androidx.annotation.Nullable;
import com.example.game.view.chess.contanst.GlobalConstant;

/**
 * 棋盘
 */
public class ChessView extends BaseView {

    private float cellWidth = 0;
    private float cellHeight = 0;
    private int containerType = 0;
    public ChessView(Context context, int containerType) {
        super(context);
        this.containerType = containerType;
        GlobalConstant.colorFlag = containerType == 0 ? GlobalConstant.ItemColorEnum.RED : GlobalConstant.ItemColorEnum.RED_;
    }

    @Override
    protected void initPaint(){
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void drawBoard(Canvas canvas){

        if(cellWidth == 0 || cellHeight == 0){
            float width = getWidth();
            float height = getHeight();
            cellWidth = width/9f;
            cellHeight = height/10f;
        }
        canvas.drawLine(cellWidth/2f,cellHeight/2f,getWidth()-cellWidth/2f,cellHeight/2f,paint);
        canvas.drawLine(cellWidth/2f,cellHeight/2f,cellWidth/2f,getHeight()-cellHeight/2f,paint);
        canvas.drawLine(cellWidth/2f,getHeight()-cellHeight/2f,getWidth()-cellWidth/2f,getHeight()-cellHeight/2f,paint);
        canvas.drawLine(getWidth()-cellWidth/2f,cellHeight/2f,getWidth()-cellWidth/2f,getHeight()-cellHeight/2f,paint);
        for (float i = 1; i <= 8; i = i + 1){
            //横
            canvas.drawLine(cellWidth/2,cellHeight*(i+0.5f), getWidth()-cellWidth/2f,cellHeight*(i+0.5f),paint);
            if(i == 8){
                break;
            }
            //竖
            canvas.drawLine(cellWidth*(i+0.5f),cellHeight/2f,cellWidth*(i+0.5f),(4f+0.5f)*cellHeight,paint);
            canvas.drawLine(cellWidth*(i+0.5f),(5f+0.5f)*cellHeight,cellWidth*(i+0.5f),getHeight()-(cellHeight/2f),paint);
        }
        //斜杠
        canvas.drawLine(cellWidth*3.5f,cellHeight/2f,cellWidth*5.5f,cellHeight*2.5f,paint);
        canvas.drawLine(cellWidth*5.5f,cellHeight/2f,cellWidth*3.5f,cellHeight*2.5f,paint);
        canvas.drawLine(cellWidth*3.5f,getHeight() - cellHeight/2f,cellWidth*5.5f,getHeight()-cellHeight*2.5f,paint);
        canvas.drawLine(cellWidth*5.5f,getHeight() - cellHeight/2f,cellWidth*3.5f,getHeight()-cellHeight*2.5f,paint);
        //汉字
        float textSize = cellWidth*2f/3f;
        float heigthDiff = cellHeight - textSize;
        float y = (5f+0.5f)*cellHeight-heigthDiff/2f;
        paint.setTextSize(textSize);
        canvas.drawText("楚河",cellWidth/2f+cellWidth*1.3f, y,paint);
        canvas.drawText("汉界",getWidth()-cellWidth*1.8f-textSize*2f,y,paint);
    }

    @Override
    public void setOnClickListener(@Nullable final OnClickListener l) {
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalConstant.clickToDefaultChange();
                l.onClick(v);
            }
        };
        super.setOnClickListener(onClickListener);
    }

    public int getContainerType() {
        return containerType;
    }
}
