package com.example.game.view.chess.items;

import android.content.Context;
import android.util.Log;
import com.example.game.view.chess.contanst.GlobalConstant;
import com.example.game.view.chess.ChessItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XiangItem extends ChessItem {
    public XiangItem(Context context, GlobalConstant.ItemColorEnum color, int cellX, int cellY) {
        super(context, color, GlobalConstant.ItemNameEnum.XIANG, cellX, cellY);
    }

    public XiangItem(Context context, ChessItem chessItem, int cellX, int cellY) {
        super(context, chessItem.getColor(), GlobalConstant.ItemNameEnum.XIANG, cellX, cellY);
        if (!(chessItem instanceof XiangItem)) {
            Log.w("che item tag", "chess item is:" + chessItem.getName().getName());
        }
    }

    @Override
    protected List<Integer[]> getDownInfoXYs() {
        List<Integer[]> indexs = new ArrayList<>(GlobalConstant.ItemNameEnum.XIANG.getMaxIndexCount());
        indexs.add(new Integer[]{cellX + 2, cellY + 2});
        indexs.add(new Integer[]{cellX + 2, cellY - 2});
        indexs.add(new Integer[]{cellX - 2, cellY + 2});
        indexs.add(new Integer[]{cellX - 2, cellY - 2});
        //不能过河
        {
            Iterator<Integer[]> iterator = indexs.iterator();
            while (iterator.hasNext()) {
                Integer[] point = iterator.next();
                if ((getColor().getType() == 0 && point[1] > 4) ||
                        (getColor().getType() == 1 && point[1] < 5)) {
                    iterator.remove();
                }
            }
        }
        List<ChessItem> chessItems = GlobalConstant.containerLaout.getChessItems();
        synchronized (chessItems) {
            for (ChessItem chessItem : chessItems) {
                //禁足点
                boolean isNotPoint = Math.abs(chessItem.getCellX() - cellX) == 2 && Math.abs(chessItem.getCellY() - cellY) == 2;
                isNotPoint = isNotPoint && chessItem.getColor().getType() == getColor().getType();
                //撇足点
                boolean isBoundPoint = Math.abs(chessItem.getCellX() - cellX) == 1 && Math.abs(chessItem.getCellY() - cellY) == 1;
                if (isBoundPoint || isNotPoint) {
                    Iterator<Integer[]> iterator = indexs.iterator();
                    while (iterator.hasNext()) {
                        Integer[] point = iterator.next();
                        if ((isNotPoint && point[0] == chessItem.getCellX() && point[1] == chessItem.getCellY()) ||
                                (isBoundPoint && (point[0] + cellX) / 2 == chessItem.getCellX() && (point[1] + cellY) / 2 == chessItem.getCellY())) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
        return indexs;
    }
}
