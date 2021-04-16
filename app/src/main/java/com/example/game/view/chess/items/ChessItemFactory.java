package com.example.game.view.chess.items;

import android.content.Context;
import com.example.game.view.chess.contanst.GlobalConstant;
import com.example.game.view.chess.ChessItem;

public class ChessItemFactory {
    private ChessItemFactory(){

    }
    public static ChessItem getNewInstance(GlobalConstant.ItemNameEnum name, Context context, GlobalConstant.ItemColorEnum colorEnum, int cellX, int cellY){
        switch (name){
            case CHE:
                return new CheItem(context,colorEnum,cellX,cellY);
            case MA:
                return new MaItem(context,colorEnum,cellX,cellY);
            case XIANG:
                return new XiangItem(context,colorEnum,cellX,cellY);
            case SHI:
                return new ShiItem(context,colorEnum,cellX,cellY);
            case SHUAI:
                return new ShuaiItem(context,colorEnum,cellX,cellY);
            case PAO:
                return new PaoItem(context,colorEnum,cellX,cellY);
            case BING:
                return new BingItem(context,colorEnum,cellX,cellY);
        }
        return null;
    }
}
