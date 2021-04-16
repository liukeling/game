package com.example.game.view.chess;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.game.view.chess.contanst.GlobalConstant;

import java.util.*;

/**
 * 棋局布局
 */
public class ContainerLaout extends RelativeLayout {
    private List<DownInfoView> downViews = new ArrayList<>(10);
    private List<ChessItem> chessItems = new ArrayList<>(32);
    private ChessView chessView;

    public ContainerLaout(Context context) {
        super(context);
    }

    public ContainerLaout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContainerLaout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ContainerLaout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        synchronized (GlobalConstant.handLock) {
            if(chessItems.size() == 0) {
                if (GlobalConstant.containerLaout == null) {
                    GlobalConstant.containerLaout = this;
                }
                if (chessView != null) {
                    GlobalConstant.batchAddItem(chessView);
                }
            }
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if(child instanceof DownInfoView) {
            synchronized (downViews) {
                downViews.add((DownInfoView) child);
            }
        }
        if(child instanceof ChessItem){
            synchronized (chessItems){
                chessItems.add((ChessItem) child);
            }
        }
        if(child instanceof ChessView){
            this.chessView = (ChessView) child;
        }
    }

    public void removeDownInfoViews(){
        synchronized (downViews){
            for (View view : downViews) {
                removeView(view);
            }
            downViews.clear();
        }
    }

    public void removeChessItem(int cellX, int cellY){
        synchronized (chessItems){
            Iterator<ChessItem> iterator = chessItems.iterator();
            while(iterator.hasNext()){
                ChessItem chessItem = iterator.next();
                if(cellX == chessItem.getCellX() && cellY == chessItem.getCellY()){
                    removeView(chessItem);
                    iterator.remove();
                    return;
                }
            }
        }
    }

    public List<ChessItem> getChessItems() {
        return chessItems;
    }
}
