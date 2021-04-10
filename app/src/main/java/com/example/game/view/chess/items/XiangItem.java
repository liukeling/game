package com.example.game.view.chess.items;

import android.content.Context;
import android.util.Log;
import com.example.game.contanst.GlobalConstant;
import com.example.game.view.ChessItem;

import java.util.ArrayList;
import java.util.List;

public class XiangItem extends ChessItem{
    public XiangItem(Context context, GlobalConstant.ItemColorEnum color, int cellX, int cellY) {
        super(context, color, GlobalConstant.ItemNameEnum.XIANG, cellX, cellY);
    }

    public XiangItem(Context context, ChessItem chessItem, int cellX, int cellY) {
        super(context, chessItem.getColor(), GlobalConstant.ItemNameEnum.XIANG, cellX, cellY);
        if(!(chessItem instanceof XiangItem)){
            Log.w("che item tag","chess item is:"+chessItem.getName().getName());
        }
    }

    @Override
    public List<ChessItem> getBoundary(List<ChessItem> allItems) {
        return null;
    }

    @Override
    protected List<Integer[]> getDownInfoXYs() {
        List<Integer[]> indexs = new ArrayList<>(GlobalConstant.ItemNameEnum.XIANG.getMaxIndexCount());
        indexs.add(new Integer[]{cellX+2,cellY+2});
        indexs.add(new Integer[]{cellX+2,cellY-2});
        indexs.add(new Integer[]{cellX-2,cellY+2});
        indexs.add(new Integer[]{cellX-2,cellY-2});
        return indexs;
    }
}
