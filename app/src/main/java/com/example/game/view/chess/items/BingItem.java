package com.example.game.view.chess.items;

import android.content.Context;
import android.util.Log;
import com.example.game.view.chess.contanst.GlobalConstant;
import com.example.game.view.chess.ChessItem;

import java.util.ArrayList;
import java.util.Iterator;
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
    protected List<Integer[]> getDownInfoXYs() {
        List<Integer[]> indexs = new ArrayList<>(GlobalConstant.ItemNameEnum.BING.getMaxIndexCount());
        int type = getColor().getType();
        if((type == 0 && cellY > 4) || (type == 1 && cellY < 5)) {
            indexs.add(new Integer[]{cellX + 1, cellY});
            indexs.add(new Integer[]{cellX - 1, cellY});
        }
        indexs.add(new Integer[]{cellX,type == 0?cellY+1:cellY-1});

        List<ChessItem> items = GlobalConstant.containerLaout.getChessItems();
        synchronized (items){
            for (ChessItem item : items) {
                if((item.getCellY() == cellY && Math.abs(item.getCellX() - cellX) == 1)||
                        (item.getCellX() == cellX && Math.abs(item.getCellY() - cellY) == 1)){
                    Iterator<Integer[]> iterator = indexs.iterator();
                    boolean remove = type == item.getColor().getType();
                    while (iterator.hasNext()){
                        Integer[] index = iterator.next();
                        if(item.getCellX() == index[0] && item.getCellY() == index[1] && remove){
                            iterator.remove();
                        }
                    }
                }
            }
        }
        return indexs;
    }

}
