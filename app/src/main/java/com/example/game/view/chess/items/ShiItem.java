package com.example.game.view.chess.items;

import android.content.Context;
import android.util.Log;
import com.example.game.contanst.GlobalConstant;
import com.example.game.view.ChessItem;

import java.util.ArrayList;
import java.util.List;

public class ShiItem extends ChessItem {
    public ShiItem(Context context, GlobalConstant.ItemColorEnum color, int cellX, int cellY) {
        super(context, color, GlobalConstant.ItemNameEnum.SHI, cellX, cellY);
    }

    public ShiItem(Context context, ChessItem chessItem, int cellX, int cellY) {
        super(context, chessItem.getColor(), GlobalConstant.ItemNameEnum.SHI, cellX, cellY);
        if(!(chessItem instanceof ShiItem)){
            Log.w("che item tag","chess item is:"+chessItem.getName().getName());
        }
    }

    @Override
    public List<ChessItem> getBoundary(List<ChessItem> allItems) {
        return null;
    }

    @Override
    protected List<Integer[]> getDownInfoXYs() {
        List<Integer[]> indexs = new ArrayList<>(GlobalConstant.ItemNameEnum.SHI.getMaxIndexCount());
        indexs.add(new Integer[]{cellX+1,cellY+1});
        indexs.add(new Integer[]{cellX+1,cellY-1});
        indexs.add(new Integer[]{cellX-1,cellY+1});
        indexs.add(new Integer[]{cellX-1,cellY-1});
        return indexs;
    }
}
