package com.example.game.view.chess.items;

import android.content.Context;
import android.util.Log;
import com.example.game.view.chess.contanst.GlobalConstant;
import com.example.game.view.chess.ChessItem;

import java.util.ArrayList;
import java.util.Iterator;
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
    protected List<Integer[]> getDownInfoXYs() {
        List<Integer[]> indexs = new ArrayList<>(GlobalConstant.ItemNameEnum.SHI.getMaxIndexCount());
        indexs.add(new Integer[]{cellX+1,cellY+1});
        indexs.add(new Integer[]{cellX+1,cellY-1});
        indexs.add(new Integer[]{cellX-1,cellY+1});
        indexs.add(new Integer[]{cellX-1,cellY-1});
        {
            Iterator<Integer[]> iterator = indexs.iterator();
            while (iterator.hasNext()) {
                Integer[] index = iterator.next();
                int x = index[0];
                int y = index[1];
                if (x < getColor().getShuaiMinX() || x > getColor().getShuaiMaxX() || y < getColor().getShuaiMinY() || y > getColor().getShuaiMaxY()) {
                    iterator.remove();
                }
            }
        }
        for (ChessItem chessItem : GlobalConstant.containerLaout.getChessItems()) {
            if(chessItem.getColor().getType() != getColor().getType()){
                continue;
            }
            Iterator<Integer[]> iterator = indexs.iterator();
            while (iterator.hasNext()){
                Integer[] index = iterator.next();
                if(chessItem.getCellX() == index[0] && chessItem.getCellY() == index[1]){
                    iterator.remove();
                }
            }
        }
        return indexs;
    }
}
