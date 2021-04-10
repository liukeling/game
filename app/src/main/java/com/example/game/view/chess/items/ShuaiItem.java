package com.example.game.view.chess.items;

import android.content.Context;
import android.util.Log;
import com.example.game.contanst.GlobalConstant;
import com.example.game.view.ChessItem;

import java.util.ArrayList;
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
    public List<ChessItem> getBoundary(List<ChessItem> allItems) {
        return null;
    }

    @Override
    protected List<Integer[]> getDownInfoXYs() {
        List<Integer[]> indexs = new ArrayList<>(GlobalConstant.ItemNameEnum.SHUAI.getMaxIndexCount());
        for (int i = 0; i < GlobalConstant.ItemNameEnum.SHUAI.getMaxIndexCount(); i ++){
            int xOryAddOrSub = i%2;//0,1
            int otherAddOrSub = 1;
            int xyAddSubType = i/2;//0,1,2,3
            int x = cellX;
            int y = cellY;
            switch (xyAddSubType) {
                case 0:
                    x = x + xOryAddOrSub;
                    y = y - otherAddOrSub;
                    break;
                case 1:
                    x = x - xOryAddOrSub;
                    y = y + otherAddOrSub;
                    break;
                case 2:
                    y = y + xOryAddOrSub;
                    x = x + otherAddOrSub;
                    break;
                case 3:
                    y = y - xOryAddOrSub;
                    x = x - otherAddOrSub;
                    break;
            }
            indexs.add(new Integer[]{x, y});
        }
        return indexs;
    }
}
