package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.game.activity.chess.ChessActivity;
import com.example.game.view.chess.ChessView;
import com.example.game.view.chess.contanst.GlobalConstant;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        synchronized (GlobalConstant.handLock) {
            GlobalConstant.containerLaout = findViewById(R.id.broadMain);
            GlobalConstant.containerLaout.addView(new ChessView(GlobalConstant.containerLaout.getContext(),1), new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        }
    }
}
