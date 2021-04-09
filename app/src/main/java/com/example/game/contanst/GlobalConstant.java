package com.example.game.contanst;

import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import com.example.game.view.ChessItem;
import com.example.game.view.ContainerLaout;
import com.example.game.view.DownInfoView;

import java.util.ArrayList;
import java.util.List;

public class GlobalConstant {
    private GlobalConstant(){

    }

    private static final List<ItemNameEnum> initNames = new ArrayList<ItemNameEnum>(16) {
        {
            add(ItemNameEnum.CHE);
            add(ItemNameEnum.MA);
            add(ItemNameEnum.XIANG);
            add(ItemNameEnum.SHI);
            add(ItemNameEnum.SHUAI);
            add(ItemNameEnum.SHI);
            add(ItemNameEnum.XIANG);
            add(ItemNameEnum.MA);
            add(ItemNameEnum.CHE);
            add(ItemNameEnum.PAO);
            add(ItemNameEnum.PAO);
            add(ItemNameEnum.BING);
            add(ItemNameEnum.BING);
            add(ItemNameEnum.BING);
            add(ItemNameEnum.BING);
            add(ItemNameEnum.BING);
        }
    };

    public static boolean hasUpItem = false;
    public static final Object handLock = new Object();
    public static ContainerLaout containerLaout;
    public static ChessItem tmp;

    /**
     * 初始化批量添加棋子
     */
    public static void batchAddItem(){
        for (int i = 1; i <= 32; i++) {
            int colorType = i / 17;
            int nameIndex = i % 16;
            int cellX = 0;
            int cellY = 0;
            if (nameIndex < 9) {
                cellX = nameIndex;
                cellY = colorType == 0 ? 0 : 9;
            } else if (nameIndex > 10) {
                cellX = (nameIndex % 5)*2;
                cellY = colorType == 0 ? 3 : 6;
            } else {
                switch (nameIndex) {
                    case 9:
                        cellX = 1;
                        break;
                    case 10:
                        cellX = 7;
                        break;
                }
                cellY = colorType == 0?2:7;
            }
            addItem(initNames.get(nameIndex).getName(), cellX, cellY, colorType == 0 ? Color.RED : Color.BLACK);
        }
    }

    /**
     * 添加棋子
     * @param name
     * @param cellX     从0开始，棋格中的位置-x
     * @param cellY     从0开始，棋格中的位置-y
     * @param color
     */
    public static void addItem(String name, int cellX, int cellY, int color) {
        synchronized (handLock) {
            if (containerLaout != null) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(containerLaout.getWidth() / 9, containerLaout.getHeight() / 10);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                layoutParams.setMargins(0, (containerLaout.getHeight() / 10) * cellY, (containerLaout.getWidth() / 9) * (8 - cellX), 0);
                containerLaout.addView(new ChessItem(containerLaout.getContext(), color, name,cellX,cellY), layoutParams);
            }
        }
    }

    /**
     * 重新添加已经有的棋子
     * @param itemView
     * @param cellX
     * @param cellY
     */
    public static void reAddItemView(ChessItem itemView,int cellX,int cellY){
        synchronized (handLock) {
            if (containerLaout != null) {
                //移除举起的棋子
                containerLaout.removeView(itemView);
                //移除位置上原有的棋子
                containerLaout.removeChessItem(cellX, cellY);
                //举起的棋子放入这个位置
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(containerLaout.getWidth() / 9, containerLaout.getHeight() / 10);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                layoutParams.setMargins(0, (containerLaout.getHeight() / 10) * cellY, (containerLaout.getWidth() / 9) * (8 - cellX), 0);
                containerLaout.addView(new ChessItem(containerLaout.getContext(), itemView,cellX,cellY), layoutParams);
            }
        }
    }
    /**
     * 提示点添加
     * @param cellX
     * @param cellY
     */
    public static void addDownInfo(int cellX, int cellY){
        synchronized (handLock) {
            if (containerLaout != null) {
                int cellW = containerLaout.getWidth() / 9;
                int cellH = containerLaout.getHeight() / 10;
                int w = cellW/2;
                int h = cellH/2;
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(w, h);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
                layoutParams.setMargins(0, (containerLaout.getHeight() / 10) * cellY+(cellH-h)/2 , (containerLaout.getWidth() / 9) * (8 - cellX)+(cellW-w)/2, 0);
                containerLaout.addView(new DownInfoView(containerLaout.getContext(),cellX,cellY), layoutParams);
            }
        }
    }

    /**
     * 默认点击触发方法 - 常量设置、棋子状态重置、移除提示点
     */
    public static void clickToDefaultChange(){
        synchronized (GlobalConstant.handLock) {
            if (GlobalConstant.hasUpItem) {
                GlobalConstant.hasUpItem = false;
                if(GlobalConstant.tmp != null){
                    tmp.reset();
                }
            }
            //移除提示点
            if(containerLaout != null){
                containerLaout.removeDownInfoViews();
            }
        }
    }
    public enum ItemNameEnum{
        CHE("車"),MA("马"),XIANG("象"),SHI("士"),SHUAI("帅"),PAO("炮"),BING("兵");
        private String name;
        ItemNameEnum(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
