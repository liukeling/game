package com.example.game.view.chess.items;

import android.content.Context;
import android.util.Log;
import com.example.game.contanst.GlobalConstant;
import com.example.game.view.ChessItem;

import java.util.ArrayList;
import java.util.List;

public class PaoItem extends ChessItem {
    public PaoItem(Context context, GlobalConstant.ItemColorEnum color, int cellX, int cellY) {
        super(context, color, GlobalConstant.ItemNameEnum.PAO, cellX, cellY);
    }

    public PaoItem(Context context, ChessItem chessItem, int cellX, int cellY) {
        super(context, chessItem.getColor(), GlobalConstant.ItemNameEnum.PAO, cellX, cellY);
        if(!(chessItem instanceof PaoItem)){
            Log.w("che item tag","chess item is:"+chessItem.getName().getName());
        }
    }

    @Override
    public List<ChessItem> getBoundary(List<ChessItem> allItems) {
        return null;
    }

    @Override
    protected List<Integer[]> getDownInfoXYs() {
        List<Integer[]> indexs = new ArrayList<>(GlobalConstant.ItemNameEnum.PAO.getMaxIndexCount());
        for (int i = 0, offset = 0; i < GlobalConstant.ItemNameEnum.PAO.getMaxIndexCount(); i++) {
            int xOrYIndex = i < 8 ? i : (i - 8);
            int xYType = i / 8 == 0 ? 0 : 1;
            if (i == 8) {
                offset = 0;
            }
            if (xYType == 0) {
                //x坐标
                if (xOrYIndex == cellX) {
                    offset++;
                }
                indexs.add(new Integer[]{xOrYIndex + offset, cellY});
            } else {
                if (xOrYIndex == cellY) {
                    offset++;
                }
                indexs.add(new Integer[]{cellX, xOrYIndex + offset});
            }

        }
        return indexs;
    }
}
