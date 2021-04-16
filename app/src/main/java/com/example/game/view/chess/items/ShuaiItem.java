package com.example.game.view.chess.items;

import android.content.Context;
import android.util.Log;
import com.example.game.view.chess.contanst.GlobalConstant;
import com.example.game.view.chess.ChessItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShuaiItem extends ChessItem {
    public ShuaiItem(Context context, GlobalConstant.ItemColorEnum color, int cellX, int cellY) {
        super(context, color, GlobalConstant.ItemNameEnum.SHUAI, cellX, cellY);
    }

    public ShuaiItem(Context context, ChessItem chessItem, int cellX, int cellY) {
        super(context, chessItem.getColor(), GlobalConstant.ItemNameEnum.SHUAI, cellX, cellY);
        if(!(chessItem instanceof ShuaiItem)){
            Log.w("che item tag","chess item is:"+chessItem.getName().getName());
        }
    }

    @Override
    protected List<Integer[]> getDownInfoXYs() {
        List<Integer[]> indexs = new ArrayList<>(GlobalConstant.ItemNameEnum.SHUAI.getMaxIndexCount());
        for (int i = 0; i < GlobalConstant.ItemNameEnum.SHUAI.getMaxIndexCount(); i ++){
            int x = cellX;
            int y = cellY;
            switch (i) {
                case 0:
                    x = x + 1;
                    break;
                case 1:
                    x = x - 1;
                    break;
                case 2:
                    y = y + 1;
                    break;
                case 3:
                    y = y - 1;
                    break;
            }
            if(x >= getColor().getShuaiMinX() && x <= getColor().getShuaiMaxX() && y >= getColor().getShuaiMinY() && y <= getColor().getShuaiMaxY()) {
                indexs.add(new Integer[]{x, y});
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
