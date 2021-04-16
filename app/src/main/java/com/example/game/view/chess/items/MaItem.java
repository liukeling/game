package com.example.game.view.chess.items;

import android.content.Context;
import android.util.Log;
import com.example.game.view.chess.contanst.GlobalConstant;
import com.example.game.view.chess.ChessItem;

import java.util.ArrayList;
import java.util.Iterator;
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
    protected List<Integer[]> getDownInfoXYs() {
        List<Integer[]> indexs = new ArrayList<>(GlobalConstant.ItemNameEnum.MA.getMaxIndexCount());
        Integer[] bound = getBound();
//        List<Integer[]> notPoint = getNotPoint();
        for (int i = 0, count = 3; i < GlobalConstant.ItemNameEnum.MA.getMaxIndexCount(); i++) {
            //x\y加减的数量
            int xOryAddOrSub = (i % 2) + 1;//1,2
            int otherAddOrSub = count - xOryAddOrSub;

            int xyAddSubType = i / 2;//0,1,2,3

            int x = cellX;
            int y = cellY;
            boolean notAdd = false;
            switch (xyAddSubType) {
                case 0:
                    //右上
                    x = x + xOryAddOrSub;
                    y = y - otherAddOrSub;
                    notAdd = (xOryAddOrSub == 2 && bound[1] != -1) || (xOryAddOrSub == 1 && bound[2] != -1);
                    break;
                case 1:
                    //左下
                    x = x - xOryAddOrSub;
                    y = y + otherAddOrSub;
                    notAdd = (xOryAddOrSub == 2 && bound[0] != -1) || (xOryAddOrSub == 1 && bound[3] != -1);
                    break;
                case 2:
                    //右下
                    y = y + xOryAddOrSub;
                    x = x + otherAddOrSub;
                    notAdd = (xOryAddOrSub == 2 && bound[3] != -1) || (xOryAddOrSub == 1 && bound[1] != -1);
                    break;
                case 3:
                    //左上
                    y = y - xOryAddOrSub;
                    x = x - otherAddOrSub;
                    notAdd = (xOryAddOrSub == 2 && bound[2] != -1) || (xOryAddOrSub == 1 && bound[0] != -1);
                    break;
            }
            if(!notAdd) {
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
    //撇足点
    private Integer[] getBound() {
        List<ChessItem> items = GlobalConstant.containerLaout.getChessItems();
        int left = -1;
        int right = -1;
        int up = -1;
        int down = -1;
        synchronized (items) {
            for (ChessItem item : items) {

                if (item == this) {
                    continue;
                }
                if(item.getCellY() == cellY && Math.abs(item.getCellX() - cellX) == 1){
                    if(item.getCellX() - cellX == -1) {
                        left = item.getCellX();
                    }else{
                        right = item.getCellX();
                    }
                }
                if(item.getCellX() == cellX && Math.abs(item.getCellY() - cellY) == 1){
                    if(item.getCellY() - cellY == -1) {
                        up = item.getCellY();
                    }else{
                        down = item.getCellY();
                    }
                }
            }

            Integer[] bound = new Integer[4];
            bound[0] = left;
            bound[1] = right;
            bound[2] = up;
            bound[3] = down;
            return bound;
        }
    }
}
