package com.example.game.view.chess.items;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.example.game.view.chess.contanst.GlobalConstant;
import com.example.game.view.chess.ChessItem;

import java.util.ArrayList;
import java.util.List;

public class PaoItem extends ChessItem {
    private static final String TAG = "pao item tag";
    public PaoItem(Context context, GlobalConstant.ItemColorEnum color, int cellX, int cellY) {
        super(context, color, GlobalConstant.ItemNameEnum.PAO, cellX, cellY);
    }

    public PaoItem(Context context, ChessItem chessItem, int cellX, int cellY) {
        super(context, chessItem.getColor(), GlobalConstant.ItemNameEnum.PAO, cellX, cellY);
        if (!(chessItem instanceof PaoItem)) {
            Log.w(TAG, "chess item is:" + chessItem.getName().getName());
        }
    }

    @Override
    protected List<Integer[]> getDownInfoXYs() {
        List<Integer[]> indexs = new ArrayList<>(GlobalConstant.ItemNameEnum.PAO.getMaxIndexCount());
        Integer[] paoIndex = getPaoIndex();
        for (int i = 0, offset = 0; i < GlobalConstant.ItemNameEnum.PAO.getMaxIndexCount(); i++) {
            int xOrYIndex = i < 8 ? i : (i - 8);
            int xYType = i / 8 == 0 ? 0 : 1;
            if (i == 8) {
                offset = 0;
            }
            int x = cellX, y = cellY;
            if (xYType == 0) {
                //x坐标
                if (xOrYIndex == cellX) {
                    offset++;
                }
                x = xOrYIndex + offset;
            } else {
                if (xOrYIndex == cellY) {
                    offset++;
                }
                y = xOrYIndex + offset;
            }
            if (y != paoIndex[0] && y != paoIndex[1] && x != paoIndex[2] && x != paoIndex[3]) {
                if (y > paoIndex[0] && (paoIndex[1] == -1 || y < paoIndex[1])
                        && x > paoIndex[2] && (paoIndex[3] == -1 || x < paoIndex[3])) {
                    indexs.add(new Integer[]{x, y});
                } else {

                    if ((paoIndex[4] != -1 && y == paoIndex[4]) ||
                            (paoIndex[5] != -1 && y == paoIndex[5]) ||
                            (paoIndex[6] != -1 && x == paoIndex[6]) ||
                            (paoIndex[7] != -1 && x == paoIndex[7])) {
                        indexs.add(new Integer[]{x, y});
                    }
                }
            }
        }
        return indexs;
    }

    private Integer[] getPaoIndex() {

        List<ChessItem> items = GlobalConstant.containerLaout.getChessItems();
        Integer[] paoIndex = new Integer[]{-1, -1, -1, -1, -1, -1, -1, -1};
        ChessItem[] replaceItems = new ChessItem[Math.min(17, items.size())];
        int addReplaceIndex = 0;
        synchronized (items) {
            for (ChessItem item : items) {
                if (item == this) {
                    continue;
                }
                boolean twoReplace = false;
                int indexType = -1;
                if (item.getCellX() == cellX) {
                    if (item.getCellY() < cellY) {
                        //上
                        indexType = 0;
                    } else if (item.getCellY() > cellY) {
                        //下
                        indexType = 1;
                    }
                }
                if (item.getCellY() == cellY) {
                    if (item.getCellX() < cellX) {
                        //左
                        indexType = 2;
                    } else if (item.getCellX() > cellX) {
                        //右
                        indexType = 3;
                    }
                }

                if (indexType != -1) {
                    replace(paoIndex, indexType, indexType+4, indexType/2 == 0 ? item.getCellY() : item.getCellX(), indexType%2);
                    replaceItems[addReplaceIndex] = item;
                    addReplaceIndex ++;
                }
            }
        }
        for (int i = 0; i < addReplaceIndex; i ++){
            ChessItem item = replaceItems[i];
            if(item == this){
                continue;
            }
            int index = -1;
            if (item.getCellX() == cellX) {
                if(item.getCellY() == paoIndex[4]){
                    index = 4;
                }else if(item.getCellY() == paoIndex[5]){
                    index = 5;
                }
            }
            if (item.getCellY() == cellY) {
                if(item.getCellX() == paoIndex[6]){
                    index = 6;
                }else if(item.getCellX() > paoIndex[7]){
                    index = 7;
                }
            }
            if(index != -1){
                if(item.getColor().getType() == getColor().getType()){
                    paoIndex[index] = -1;
                }
            }
        }
        return paoIndex;
    }

    /**
     * @param data
     * @param bound1Index   当前范围在data中的索引
     * @param bound2Index   当前范围在data中的索引
     * @param replaceData   需要判断的坐标
     * @param type          0标识子在上或者左，1标识在下或者右
     */
    private void replace(Integer[] data, int bound1Index, int bound2Index, int replaceData, int type) {
        Integer tmp = data[bound1Index];

        if (data[bound2Index].intValue() == -1 && data[bound1Index].intValue() != -1) {
            data[bound2Index] = data[bound1Index];
        }
        data[bound1Index] = type == 0 ? Math.max(data[bound1Index], replaceData) :
                (data[bound1Index] == -1 ? replaceData : Math.min(data[bound1Index], replaceData));
        if (data[bound1Index].intValue() != replaceData) {
            data[bound2Index] = type == 0 ? Math.max(data[bound2Index], replaceData) :
                    Math.min(data[bound2Index], replaceData);
            if (data[bound1Index].intValue() == data[bound2Index].intValue()) {
                data[bound2Index] = replaceData;
            }
        }else{
            data[bound2Index] = tmp;
        }
    }
}
