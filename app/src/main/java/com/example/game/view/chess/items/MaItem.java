package com.example.game.view.chess.items;

import android.content.Context;
import android.util.Log;
import com.example.game.contanst.GlobalConstant;
import com.example.game.view.ChessItem;

import java.util.ArrayList;
import java.util.List;

public class MaItem extends ChessItem {
    public MaItem(Context context, GlobalConstant.ItemColorEnum color, int cellX, int cellY) {
        super(context, color, GlobalConstant.ItemNameEnum.MA, cellX, cellY);
    }

    public MaItem(Context context, ChessItem chessItem, int cellX, int cellY) {
        super(context, chessItem.getColor(), GlobalConstant.ItemNameEnum.MA, cellX, cellY);
        if(!(chessItem instanceof MaItem)){
            Log.w("che item tag","chess item is:"+chessItem.getName().getName());
        }
    }

    @Override
    public List<ChessItem> getBoundary(List<ChessItem> allItems) {
        return null;
    }

    @Override
    protected List<Integer[]> getDownInfoXYs() {
        List<Integer[]> indexs = new ArrayList<>(GlobalConstant.ItemNameEnum.MA.getMaxIndexCount());
        for (int i = 0, count = 3; i < GlobalConstant.ItemNameEnum.MA.getMaxIndexCount(); i++) {
            //x\y加减的数量
            int xOryAddOrSub = (i % 2) + 1;//1,2
            int otherAddOrSub = count - xOryAddOrSub;

            int xyAddSubType = i / 2;//0,1,2,3

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
