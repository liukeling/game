package com.example.game.view.chess.items;

import android.content.Context;
import android.util.Log;
import com.example.game.view.chess.contanst.GlobalConstant;
import com.example.game.view.chess.ChessItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liukeling
 */
public class CheItem extends ChessItem {
    public CheItem(Context context, GlobalConstant.ItemColorEnum color, int cellX, int cellY) {
        super(context, color, GlobalConstant.ItemNameEnum.CHE, cellX, cellY);
    }

    public CheItem(Context context, ChessItem chessItem, int cellX, int cellY) {
        super(context, chessItem.getColor(), GlobalConstant.ItemNameEnum.CHE, cellX, cellY);
        if (!(chessItem instanceof CheItem)) {
            Log.w("che item tag", "chess item is:" + chessItem.getName().getName());
        }

    }

    @Override
    protected List<Integer[]> getDownInfoXYs() {
        List<Integer[]> indexs = new ArrayList<>(GlobalConstant.ItemNameEnum.CHE.getMaxIndexCount());
        Integer[] bound = getBound();
        Log.i("testbound", "bound info==========================");
        for (int i = 0; i < bound.length; i++) {
            Log.i("testbound", "bound["+i+"]:"+bound[i]);
        }
        Log.i("testbound", "bound info==========================");
        for (int i = 0, offset = 0; i < GlobalConstant.ItemNameEnum.CHE.getMaxIndexCount(); i++) {
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
                int x = xOrYIndex + offset;
                if(x >= bound[0] && x <= bound[1]) {
                    indexs.add(new Integer[]{x, cellY});
                }
            } else {
                if (xOrYIndex == cellY) {
                    offset++;
                }
                int y = xOrYIndex + offset;
                if(y >= bound[2] && y <= bound[3]) {
                    indexs.add(new Integer[]{cellX, y});
                }
            }

        }
        return indexs;
    }

    private Integer[] getBound() {
        List<ChessItem> items = GlobalConstant.containerLaout.getChessItems();
        int minX = 0;
        int maxX = 8;
        int minY = 0;
        int maxY = 9;
        synchronized (items) {
            for (ChessItem item : items) {

                if (item == this) {
                    continue;
                }
                boolean canReplace = item.getColor().getType() != color.getType();
                if (item.getCellX() == cellX) {
                    if (item.getCellY() < cellY) {
                        int curY = canReplace ? item.getCellY() : item.getCellY() + 1;
                        minY = Math.max(minY, curY);
                    }
                    if (item.getCellY() > cellY) {
                        int curY = canReplace ? item.getCellY() : item.getCellY() - 1;
                        maxY = Math.min(maxY, curY);
                    }
                } else if (item.getCellY() == cellY) {
                    if (item.getCellX() < cellX) {
                        int curX = canReplace ? item.getCellX() : item.getCellX() + 1;
                        minX = Math.max(minX, curX);
                    }
                    if (item.getCellX() > cellX) {
                        int curX = canReplace ? item.getCellX() : item.getCellX() - 1;
                        maxX = Math.min(maxX, curX);
                    }
                }
            }

            Integer[] bound = new Integer[4];
            bound[0] = minX;
            bound[1] = maxX;
            bound[2] = minY;
            bound[3] = maxY;
            return bound;
        }
    }
}
