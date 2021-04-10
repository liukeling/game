package com.example.game.view.chess.items;

import android.content.Context;
import android.util.Log;
import com.example.game.contanst.GlobalConstant;
import com.example.game.view.ChessItem;

import java.util.ArrayList;
import java.util.List;

public class BingItem extends ChessItem{
    public BingItem(Context context, GlobalConstant.ItemColorEnum color, int cellX, int cellY) {
        super(context, color, GlobalConstant.ItemNameEnum.BING, cellX, cellY);
    }

    public BingItem(Context context, ChessItem chessItem, int cellX, int cellY) {
        super(context, chessItem.getColor(), GlobalConstant.ItemNameEnum.BING, cellX, cellY);
        if(!(chessItem instanceof BingItem)){
            Log.w("che item tag","chess item is:"+chessItem.getName().getName());
        }

    }

    @Override
    public List<ChessItem> getBoundary(List<ChessItem> allItems) {
        return null;
    }

    @Override
    protected List<Integer[]> getDownInfoXYs() {
        List<Integer[]> indexs = new ArrayList<>(GlobalConstant.ItemNameEnum.BING.getMaxIndexCount());
        indexs.add(new Integer[]{cellX+1,cellY});
        indexs.add(new Integer[]{cellX-1,cellY});
        indexs.add(new Integer[]{cellX,getColor().getType() == 0?cellY+1:cellY-1});

        //边界
        List<ChessItem> boundary = GlobalConstant.containerLaout.getBoundary(this);

        return indexs;
    }

}
